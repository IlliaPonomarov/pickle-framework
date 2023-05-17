package com.pickle.services.parsers.yaml;

import com.pickle.models.TestCase;
import com.pickle.models.rest.*;
import com.pickle.services.parsers.FileParser;
import com.pickle.utility.enums.ProtocolType;
import com.pickle.utility.enums.RestRequestValue;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.yaml.snakeyaml.Yaml;

import java.util.*;

import java.io.InputStream;
import java.util.stream.Collectors;

public class RESTYamlEndpointParser extends YamlEndpointParser {
    private Yaml yaml;
    private InputStream inputStream;

    private Map<String, Object> fieldsContent;

    public RESTYamlEndpointParser(FileParser fileParser) {
        super(fileParser);
        this.yaml = new Yaml();
    }

    public Map<String, Object> parseFile() {
        this.inputStream = super.getFileParser().getInputStream();
        Map<String, Object> fieldsContent = yaml.load(inputStream);
        return fieldsContent.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public ProtocolType extractProtocolType() {
        return null;
    }

    /**
     * Create a rest test case
     * @return
     */

    /**
     * TODO: Use Factory pattern to separate the creation of the test case to soap and rest
     * @return
     */
    public Map<UUID, ? extends TestCase> createTestCase() {
        Map<String, String> headers = new HashMap<>();
        Map<UUID, RestTestCase> testCases = new HashMap<>();
        this.fieldsContent = parseFile();

        if (this.fieldsContent.containsKey("rest")) {
            Map<String, Object> rest = (Map<String, Object>) fieldsContent.get("rest");

            // Get the requests names, example ( GET, POST, PUT, DELETE )
            for (Map.Entry<String, Object> operation : rest.entrySet()) {
                String operationName = operation.getKey();
                RestTestCase restTestCase = (RestTestCase) getTestCase(operation, operationName);

                testCases.put(UUID.randomUUID(), restTestCase);
            }
        }

        return testCases;
    }

    /**
     * Get the test case from the yaml file
     * @param operations
     * @param requestName
     * @return
     */

    public RestTestCase getTestCase(Map.Entry<String, Object> operations, String requestName) {
        HttpRequest httpRequest = new HttpRequest();
        HttpExpectedResponse httpExpectedResponse = null;
        final Map<String, Object> operationFields = (Map<String, Object>) operations.getValue();

        Optional<Map.Entry<String, Object>> requestFields = operationFields.entrySet().stream()
                .filter(requestInfo -> requestInfo.getKey().equals("request"))
                .findFirst();

        if (requestFields.isPresent())
            httpRequest = extractRequest(requestFields.get());

        Optional<Map.Entry<String, Object>> expectedResponseEntry = operationFields.entrySet().stream()
                .filter(requestInfo -> requestInfo.getKey().equals("expected-response"))
                .findFirst();

        if (expectedResponseEntry.isPresent())
            httpExpectedResponse = extractExpectedResponse(expectedResponseEntry.get());

        return new RestTestCase(httpRequest, httpExpectedResponse, requestName);
    }

    /**
     * Get the expected response from the yaml file
     * @param requestInfo
     * @return
     */

    @Override
    public HttpRequest extractRequest(Map.Entry<String, Object> requestInfo) {
        Map<String, Object> request = (Map<String, Object>) requestInfo.getValue();
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> headers = ((Map<?, ?>) requestInfo.getValue()).get("headers") != null ?
                (Map<String, Object>) ((Map<?, ?>) requestInfo.getValue()).get("headers") : new HashMap<>();

        HttpHeaders httpHeaders = extractHeaders(headers);

        String url = getOptionalFieldsValuesByKey(request, RestRequestValue.URL.getValue()).map(String::toString)
                .orElseThrow( () -> new IllegalArgumentException("URL not found") );

        HttpMethod method = getMandatoryFieldsValuesByKey( request, RestRequestValue.METHOD.getValue() )
                .map(HttpMethod::valueOf)
                .orElseThrow( () -> new IllegalArgumentException("Method not found") );

        String body = getOptionalFieldsValuesByKey(request, RestRequestValue.BODY.getValue()).map(String::toString).orElse("");

        params = ((Map<?, ?>) requestInfo.getValue()).get(RestRequestValue.PARAMS.getValue()) != null ?
                (Map<String, Object>) ((Map<?, ?>) requestInfo.getValue()).get("params") : new HashMap<>();

        return new HttpRequest.HttpRequestBuilder(method, url)
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

    private Optional<String> getOptionalFieldsValuesByKey(Map<String, Object> mapper, String key) {
        return mapper.entrySet().stream()
                .filter(requestInfo -> requestInfo.getKey().equals(key))
                .findFirst()
                .map(entry -> entry.getValue().toString());
    }

    private Optional<String> getMandatoryFieldsValuesByKey(Map<String, Object> mapper, String key) {
        return Optional.ofNullable(mapper.entrySet().stream()
                .filter(requestInfo -> requestInfo.getKey().equals(key))
                .findFirst()
                .map(entry -> entry.getValue().toString())
                .orElseThrow(() -> new IllegalArgumentException("Key or value not found: " + key)));
    }

    /**
     * Get the expected response from the yaml file
     * @param headers
     * @return
     */
    public HttpHeaders extractHeaders(Map<String, Object> headers) {

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
    public HttpExpectedResponse extractExpectedResponse(Map.Entry<String, Object> requestInfo) {

        // Get the expected response
        Map<String, Object> expectedResponse = (Map<String, Object>) requestInfo.getValue();

        // Get the expected headers
        Map<String, Object> headersMap = Optional.ofNullable(expectedResponse.get("headers"))
                .map(o -> (Map<String, Object>) o)
                .orElseThrow(() -> new IllegalArgumentException("Missing 'headers' in the expected response"));

        // Get the expected headers
        HttpHeaders expectedHttpHeaders = headersMap.keySet().stream()
                .map(key -> extractHeaders(headersMap)).findFirst().get();

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

}
