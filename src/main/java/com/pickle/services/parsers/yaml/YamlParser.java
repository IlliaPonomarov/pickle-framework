package com.pickle.services.parsers.yaml;

import com.pickle.models.rest.*;
import com.pickle.services.parsers.FileParser;
import com.pickle.services.parsers.Parser;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;

import java.io.InputStream;
import java.util.UUID;
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

    @Override
    public HttpHeaders createTestCase() {
        Map<String, String> headers = new HashMap<>();
        HttpHeaders httpHeaders = null;
        HttpHeaders expectedHttpHeaders = null;
        HttpRequest httpRequest = null;
        HttpExpectedResponse httpExpectedResponse = null;
        RestTestCase restTest;
        HttpStatusCode expectedHttpStatus = null;
        String expectedResponseBody = "";
        Map<UUID, RestTestCase> testCases = new HashMap<>();

        if (this.fieldsContent.containsKey("rest")) {
            Map<String, Object> rest = (Map<String, Object>) fieldsContent.get("rest");

            // Get the requests names, example ( GET, POST, PUT, DELETE )
            for (Map.Entry<String, Object> requests : rest.entrySet()) {
                String requestName = requests.getKey();
                // Get the request info, example ( request, expectedResponse )
                for (Map.Entry<String, Object> requestInfo : ((Map<String, Object>) requests.getValue()).entrySet()) {
                    RestTestCase restTestCase = new RestTestCase();
                    restTestCase = new RestTestCase();
                    restTestCase.setRequestName(requestName);
                    // Get the request headers
                    if (requestInfo.getKey().equals("request")) {
                        Map<String, Object> request = (Map<String, Object>) requestInfo.getValue();
                        String url = "";
                        String method = "";
                        String body = "";
                        Map<String, Object> params = new HashMap<>();
                        Map<String, Object> headersMap = new HashMap<>();
                        // Get the request headers
                        for (Map.Entry<String, Object> requestFields : request.entrySet()) {
                            String key = requestFields.getKey();

                            if (key.equals("headers")) {
                                headersMap = (Map<String, Object>) requestFields.getValue();
                                String accept = "";
                                String contentLength = "";
                                String cookie = "";
                                String contentEncoding = "";
                                String contentLanguage = "";
                                String contentType = "";
                                String userAgent = "";
                                String authorization = "";
                                String xRequestedWith = "";
                                String contentLocation = "";
                                String contentMD5 = "";
                                String cacheControl = "";

                                for (Map.Entry<String, Object> header : headersMap.entrySet()) {

                                    if (header.getKey().equals("Authorization")) {
                                        authorization = header.getValue().toString();
                                    }
                                    if (header.getKey().equals("Accept")) {
                                        accept = header.getValue().toString();
                                    }
                                    if (header.getKey().equals("Content-Length")) {
                                        contentLength = header.getValue().toString();
                                    }
                                    if (header.getKey().equals("Cookie")) {
                                        cookie = header.getValue().toString();
                                    }
                                    if (header.getKey().equals("Content-Encoding")) {
                                        contentEncoding = header.getValue().toString();
                                    }
                                    if (header.getKey().equals("contentLanguage")) {
                                        contentLanguage = header.getValue().toString();
                                    }
                                    if (header.getKey().equals("Content-Type")) {
                                        contentType = header.getValue().toString();
                                    }
                                    if (header.getKey().equals("User-Agent")) {
                                        userAgent = header.getValue().toString();
                                    }
                                    if (header.getKey().equals("X-Requested-With")) {
                                        xRequestedWith = header.getValue().toString();
                                    }
                                    if (header.getKey().equals("Content-Location")) {
                                        contentLocation = header.getValue().toString();
                                    }

                                    if (header.getKey().equals("Content-MD5")) {
                                        contentMD5 = header.getValue().toString();
                                    }

                                    if (header.getKey().equals("Cache-Control")) {
                                        cacheControl = header.getValue().toString();
                                    }
                                }

                                httpHeaders = new HttpHeaders.HttpHeaderBuilder().accept(accept)
                                        .contentLength(contentLength)
                                        .cookie(cookie)
                                        .contentEncoding(contentEncoding)
                                        .contentLanguage(contentLanguage)
                                        .contentType(contentType)
                                        .userAgent(userAgent)
                                        .authorization(authorization)
                                        .xRequestedWith(xRequestedWith)
                                        .contentMD5(contentMD5)
                                        .contentLocation(contentLocation)
                                        .cacheControl(cacheControl)
                                        .build();

                            }
                            if (key.equals("method")) {
                                method = requestFields.getValue().toString();
                            }
                            if (key.equals("url")) {
                                url = requestFields.getValue().toString();
                            }

                            if (key.equals("body")) {
                                body = requestFields.getValue().toString();
                            }

                            if (key.equals("params"))
                                params = (Map<String, Object>) requestFields.getValue();

                        }
                        httpRequest = new HttpRequest.HttpRequestBuilder(HttpMethod.valueOf(method), url)
                                .params(params)
                                .httpHeader(httpHeaders)
                                .body(body)
                                .build();
                    }
                    if (requestInfo.getKey().equals("expected-response")) {
                        Map<String, Object> expectedResponse = (Map<String, Object>) requestInfo.getValue();
                        Map<String, Object> headersMap = new HashMap<>();

                        if (expectedResponse.containsKey("headers")) {
                            headersMap = (Map<String, Object>) expectedResponse.get("headers");

                            for (Map.Entry<String, Object> header : headersMap.entrySet()) {
                                expectedHttpHeaders = new HttpHeaders.HttpHeaderBuilder().accept(header.getValue().toString())
                                        .contentLength(header.getValue().toString())
                                        .cookie(header.getValue().toString())
                                        .contentEncoding(header.getValue().toString())
                                        .contentLanguage(header.getValue().toString())
                                        .contentMD5(header.getValue().toString())
                                        .cacheControl(header.getValue().toString())
                                        .authorization(header.getValue().toString())
                                        .contentType(header.getValue().toString())
                                        .build();
                            }
                        }

                        if (expectedResponse.containsKey("status"))
                            expectedHttpStatus = HttpStatusCode.valueOf(Integer.parseInt(expectedResponse.get("status").toString()));

                        if (expectedResponse.containsKey("body"))
                            expectedResponseBody = expectedResponse.get("body").toString();

                        httpExpectedResponse = new HttpExpectedResponse(expectedHttpStatus, expectedHttpHeaders, expectedResponseBody);

                    }
                }
                testCases.put(UUID.randomUUID(), new RestTestCase(httpRequest, httpExpectedResponse, requestName));

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

    @Override
    public String requestParser() {
        return null;
    }

    @Override
    public String expectedResponseParser() {
        return null;
    }
}
