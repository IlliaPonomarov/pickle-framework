package com.pickle.services.parsers.yaml;

import com.pickle.models.rest.*;
import com.pickle.services.parsers.FileParser;
import com.pickle.services.parsers.Parser;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.yaml.snakeyaml.Yaml;

import java.util.*;

import java.io.InputStream;
import java.util.stream.Collectors;

public class YamlParser implements Parser {

    private FileParser fileParser;
    private Yaml yaml;
    private InputStream inputStream;

    private Map<String, Object> fieldsContent;

    public YamlParser() {
    }

    public YamlParser(FileParser fileParser) {
        this.fileParser = fileParser;
        this.yaml = new Yaml();
    }

    @Override
    public Map<String, Object> parseFile() {
        this.inputStream = fileParser.getInputStream();
        this.fieldsContent = yaml.load(inputStream);
        return fieldsContent.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Create a rest test case
     * @return
     */
    @Override
    public HttpHeaders createRestTestCase() {
        Map<String, String> headers = new HashMap<>();
        Map<UUID, RestTestCase> testCases = new HashMap<>();

        if (this.fieldsContent.containsKey("rest")) {
            Map<String, Object> rest = (Map<String, Object>) fieldsContent.get("rest");

            // Get the requests names, example ( GET, POST, PUT, DELETE )
            for (Map.Entry<String, Object> requests : rest.entrySet()) {
                String requestName = requests.getKey();
                RestTestCase restTestCase = getRestTestCase(requests, requestName);

                testCases.put(UUID.randomUUID(), restTestCase);
            }
        }

        return new HttpHeaders.HttpHeaderBuilder().accept(headers.get("Accept"))
                .contentType(headers.get("Content-Type"))
                .authorization(headers.get("Authorization"))
                .contentLanguage(headers.get("Content-Language"))
                .contentEncoding(headers.get("Content-Encoding"))
                .contentMD5(headers.get("Content-MD5"))
                .cacheControl(headers.get("Cache-Control"))
                .build();
    }

    /**
     * Get the test case from the yaml file
     * @param requests
     * @param requestName
     * @return
     */
    private RestTestCase getRestTestCase(Map.Entry<String, Object> requests, String requestName) {
        HttpRequest httpRequest = new HttpRequest();
        HttpExpectedResponse httpExpectedResponse = null;
        final Map<String, Object> requestValueMap = (Map<String, Object>) requests.getValue();

        Optional<Map.Entry<String, Object>> requestEntry = requestValueMap.entrySet().stream()
                .filter(requestInfo -> requestInfo.getKey().equals("request"))
                .findFirst();

        if (requestEntry.isPresent())
            httpRequest = getHttpRequest(requestEntry.get());

        Optional<Map.Entry<String, Object>> expectedResponseEntry = requestValueMap.entrySet().stream()
                .filter(requestInfo -> requestInfo.getKey().equals("expected-response"))
                .findFirst();

        if (expectedResponseEntry.isPresent())
            httpExpectedResponse = getHttpExpectedResponse(expectedResponseEntry.get());

        return new RestTestCase(httpRequest, httpExpectedResponse, requestName);
    }

    /**
     * Get the expected response from the yaml file
     * @param requestInfo
     * @return
     */

    private HttpRequest getHttpRequest(Map.Entry<String, Object> requestInfo) {
        Map<String, Object> request = (Map<String, Object>) requestInfo.getValue();
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> headers = ((Map<?, ?>) requestInfo.getValue()).get("headers") != null ?
                (Map<String, Object>) ((Map<?, ?>) requestInfo.getValue()).get("headers") : new HashMap<>();

        HttpHeaders httpHeaders = getHttpHeaders(headers);

        String url = getValueByKey(request, "url");
        String method = getValueByKey(request, "method");
        String body = getValueByKey(request, "body");
        params = ((Map<?, ?>) requestInfo.getValue()).get("params") != null ?
                (Map<String, Object>) ((Map<?, ?>) requestInfo.getValue()).get("params") : new HashMap<>();

        return new HttpRequest.HttpRequestBuilder(HttpMethod.valueOf(method), url)
                .params(params)
                .httpHeader(httpHeaders)
                .body(body)
                .build();
    }

    /**
     * Get the expected response from the yaml file
     * @param mapper
     * @param key
     * @return
     */

    private String getValueByKey(Map<String, Object> mapper, String key) {
        return mapper.entrySet().stream()
                .filter(requestInfo -> requestInfo.getKey().equals(key))
                .findFirst().get()
                .getValue()
                .toString();
    }

    /**
     * Get the expected response from the yaml file
     * @param headers
     * @return
     */
    private HttpHeaders getHttpHeaders(Map<String, Object> headers) {

        Map<String, String> headerValues = new HashMap<>(Map.ofEntries(
                Map.entry("Accept", ""),
                Map.entry("Content-Length", ""),
                Map.entry("Cookie", ""),
                Map.entry("Content-Encoding", ""),
                Map.entry("Content-Language", ""),
                Map.entry("Content-Type", ""),
                Map.entry("User-Agent", ""),
                Map.entry("Authorization", ""),
                Map.entry("X-Requested-With", ""),
                Map.entry("Content-Location", ""),
                Map.entry("Content-MD5", ""),
                Map.entry("Cache-Control", "")
        ));

        headers.forEach((key, value) -> {
            if (headerValues.get(key) != null) {
                headerValues.put(key, value.toString());
            }
        });

        return new HttpHeaders.HttpHeaderBuilder()
                .accept(headerValues.get("Accept"))
                .contentLength(headerValues.get("Content-Length"))
                .cookie(headerValues.get("Cookie"))
                .contentEncoding(headerValues.get("Content-Encoding"))
                .contentLanguage(headerValues.get("Content-Language"))
                .contentType(headerValues.get("Content-Type"))
                .userAgent(headerValues.get("User-Agent"))
                .authorization(headerValues.get("Authorization"))
                .xRequestedWith(headerValues.get("X-Requested-With"))
                .contentMD5(headerValues.get("Content-MD5"))
                .contentLocation(headerValues.get("Content-Location"))
                .cacheControl(headerValues.get("Cache-Control"))
                .build();
    }

    /**
     * Get the expected response
     * @param requestInfo
     * @return
     */
    private HttpExpectedResponse getHttpExpectedResponse(Map.Entry<String, Object> requestInfo) {

        // Get the expected response
        Map<String, Object> expectedResponse = (Map<String, Object>) requestInfo.getValue();

        // Get the expected headers
        Map<String, Object> headersMap = Optional.ofNullable(expectedResponse.get("headers"))
                .map(o -> (Map<String, Object>) o)
                .orElseThrow(() -> new IllegalArgumentException("Missing 'headers' in the expected response"));

        // Get the expected headers
        HttpHeaders expectedHttpHeaders = headersMap.keySet().stream()
                .map(key -> getHttpHeaders(headersMap)).findFirst().get();

        // Get the expected status code
        HttpStatusCode expectedHttpStatus = Optional.ofNullable(expectedResponse.get("status"))
                .map(o -> HttpStatusCode.valueOf(Integer.parseInt(o.toString())))
                .orElseThrow(() -> new IllegalArgumentException("Missing 'status' in the expected response"));

        // Get the expected body
        String expectedResponseBody = Optional.ofNullable(expectedResponse.get("body"))
                .map(Object::toString)
                .orElseThrow(() -> new IllegalArgumentException("Missing 'body' in the expected response"));

        return new HttpExpectedResponse(expectedHttpStatus, expectedHttpHeaders, expectedResponseBody);
    }



    @Override
    public String requestParser() {
        return null;
    }

    @Override
    public String expectedResponseParser() {
        return null;
    }
}
