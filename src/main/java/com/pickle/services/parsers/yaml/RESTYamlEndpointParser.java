package com.pickle.services.parsers.yaml;

import com.pickle.models.OperationTestCase;
import com.pickle.models.rest.*;
import com.pickle.services.parsers.FileParser;
import com.pickle.utility.enums.HeadersValue;
import com.pickle.utility.enums.ProtocolType;
import com.pickle.utility.enums.RestExpectedResponseValues;
import com.pickle.utility.enums.RestRequestValue;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.yaml.snakeyaml.Yaml;

import java.util.*;

import java.io.InputStream;
import java.util.stream.Stream;

public class RESTYamlEndpointParser extends YamlEndpointParser {
    private InputStream inputStream;

    private Map<String, Object> fieldsContent;

    public RESTYamlEndpointParser(FileParser fileParser) {
        super(fileParser, new Yaml());
    }


    @Override
    public ProtocolType extractProtocolType() {
        return null;
    }

    /**
     * Create a rest test case
     * @return
     */

    public Map<UUID, ? extends OperationTestCase> createTestCase() {
        Map<UUID, RESTOperationTestCase> restTestOperations = new HashMap<>();
        String restProtocolType = ProtocolType.REST.getProtocolType();

        this.fieldsContent = super.parseFile();

        if (this.fieldsContent.containsKey(restProtocolType)) {
            Map<String, Object> rest = (Map<String, Object>) fieldsContent.get(restProtocolType);
            Stream<Map.Entry<String, Object>> stream = rest.entrySet().stream();

            stream.forEach(operation -> {
                String operationName = operation.getKey();
                RESTOperationTestCase restTestCase = (RESTOperationTestCase) getOperationTestCase(operation, operationName);
                UUID randomUUID = UUID.randomUUID();

                restTestOperations.put(randomUUID, restTestCase);
            });

        }

        return restTestOperations;
    }

    /**
     * Get the test case from the yaml file
     * @param operations
     * @param requestName
     * @return
     */

    public RESTOperationTestCase getOperationTestCase(Map.Entry<String, Object> operations, String requestName) {
        RESTRequest RESTRequest = new RESTRequest();
        RESTExpectedResponse RESTExpectedResponse = null;

        String request = RestRequestValue.REQUEST.getValue();
        String expectedResponse = RestExpectedResponseValues.EXPECTED_RESPONSE.getValue();

        final Map<String, Object> operationFields = (Map<String, Object>) operations.getValue();

        // Get the request from the yaml file and create the request object
        Optional<Map.Entry<String, Object>> requestFields = operationFields.entrySet().stream()
                .filter(requestInfo -> requestInfo.getKey().equals(request))
                .findFirst();

        // If the request is present, create the request object
        if (requestFields.isPresent())
            RESTRequest = extractRequest(requestFields.get());

        // Get the expected response from the yaml file and create the expected response object
        Optional<Map.Entry<String, Object>> expectedResponseEntry = operationFields.entrySet().stream()
                .filter(requestInfo -> requestInfo.getKey().equals(expectedResponse))
                .findFirst();

        // If the expected response is present, create the expected response object
        if (expectedResponseEntry.isPresent())
            RESTExpectedResponse = extractExpectedResponse(expectedResponseEntry.get());

        return new RESTOperationTestCase(RESTRequest, RESTExpectedResponse, requestName);
    }

    /**
     * Get the expected response from the yaml file
     * @param requestInfo
     * @return
     */

    @Override
    public RESTRequest extractRequest(Map.Entry<String, Object> requestInfo) {
        String headersValue = RestRequestValue.HEADERS.getValue();

        Map<String, Object> requestData = (Map<String, Object>) requestInfo.getValue();
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> headersData = Optional.ofNullable((Map<String, Object>) requestData.get(headersValue))
                .orElse(new HashMap<>());

        // Extract the request fields from the yaml file
        RESTHeaders RESTHeaders = null;

        if (headersData.size() > 0)
            RESTHeaders = extractHeaders(headersData);

        String url = getOptionalFieldValuesByKey(requestData, RestRequestValue.URL.getValue()).map(String::toString)
                .orElseThrow( () -> new IllegalArgumentException("URL not found") );

        HttpMethod method = getMandatoryFieldValuesByKey( requestData, RestRequestValue.METHOD.getValue() )
                .map(HttpMethod::valueOf)
                .orElseThrow( () -> new IllegalArgumentException("Method not found") );

        String body = getOptionalFieldValuesByKey(requestData, RestRequestValue.BODY.getValue()).map(String::toString).orElse("");

        params = ((Map<?, ?>) requestInfo.getValue()).get(RestRequestValue.PARAMS.getValue()) != null ?
                (Map<String, Object>) ((Map<?, ?>) requestInfo.getValue()).get("params") : new HashMap<>();

        return new RESTRequest.HttpRequestBuilder(method, url)
                .params(params)
                .httpHeader(RESTHeaders)
                .body(body)
                .build();
    }

    /**
     * Get the expected response from the yaml file
     * @param mapper
     * @param key
     * @return
     */

    private Optional<String> getOptionalFieldValuesByKey(Map<String, Object> mapper, String key) {
        return mapper.entrySet().stream()
                .filter(requestInfo -> requestInfo.getKey().equals(key))
                .findFirst()
                .map(entry -> entry.getValue().toString());
    }

    private Optional<String> getMandatoryFieldValuesByKey(Map<String, Object> mapper, String key) {
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
    public RESTHeaders extractHeaders(Map<String, Object> headers) {
        String accept = HeadersValue.ACCEPT.getValue();
        String contentLength = HeadersValue.CONTENT_LENGTH.getValue();
        String cookie = HeadersValue.COOKIE.getValue();
        String contentEncoding = HeadersValue.CONTENT_ENCODING.getValue();
        String contentLanguage = HeadersValue.CONTENT_LANGUAGE.getValue();
        String contentType = HeadersValue.CONTENT_TYPE.getValue();
        String userAgent = HeadersValue.USER_AGENT.getValue();
        String authorization = HeadersValue.AUTHORIZATION.getValue();
        String xRequestedWith = HeadersValue.X_REQUESTED_WITH.getValue();
        String contentLocation = HeadersValue.CONTENT_LOCATION.getValue();
        String contentMD5 = HeadersValue.CONTENT_MD5.getValue();
        String cacheControl = HeadersValue.CACHE_CONTROL.getValue();

        Map<String, String> headerValues = new HashMap<>(Map.ofEntries(
                Map.entry(accept, ""),
                Map.entry(contentLength, ""),
                Map.entry(cookie, ""),
                Map.entry(contentEncoding, ""),
                Map.entry(contentLanguage, ""),
                Map.entry(contentType, ""),
                Map.entry(userAgent, ""),
                Map.entry(authorization, ""),
                Map.entry(xRequestedWith, ""),
                Map.entry(contentLocation, ""),
                Map.entry(contentMD5, ""),
                Map.entry(cacheControl, "")
        ));

        headers.forEach((key, value) -> {
            if (headerValues.get(key) != null) {
                headerValues.put(key, value.toString());
            }
        });

        return new RESTHeaders.HttpHeaderBuilder()
                .accept(headerValues.get(accept))
                .contentLength(headerValues.get(contentLength))
                .cookie(headerValues.get(cookie))
                .contentEncoding(headerValues.get(contentEncoding))
                .contentLanguage(headerValues.get(contentLanguage))
                .contentType(headerValues.get(contentType))
                .userAgent(headerValues.get(userAgent))
                .authorization(headerValues.get(authorization))
                .xRequestedWith(headerValues.get(xRequestedWith))
                .contentLocation(headerValues.get(contentLocation))
                .contentMD5(headerValues.get(contentMD5))
                .cacheControl(headerValues.get(cacheControl))
                .build();
    }

    /**
     * Get the expected response
     * @param requestInfo
     * @return
     */
    public RESTExpectedResponse extractExpectedResponse(Map.Entry<String, Object> requestInfo) {
        String body = RestExpectedResponseValues.BODY.getValue();
        String statusCode = RestExpectedResponseValues.STATUS_CODE.getValue();
        String headers = RestExpectedResponseValues.HEADERS.getValue();

        // Get the expected response
        Map<String, Object> expectedResponseData = (Map<String, Object>) requestInfo.getValue();

        // Get the expected headers
        Map<String, Object> headersData = Optional.ofNullable(expectedResponseData.get(headers))
                .map(o -> (Map<String, Object>) o)
                .orElse(new HashMap<>());

        RESTHeaders expectedRESTHeaders = headersData.keySet().stream()
                .map(key -> extractHeaders(headersData)).findFirst().get();

        // Get the expected status code
        HttpStatusCode expectedHttpStatus = Optional.ofNullable(expectedResponseData.get(statusCode))
                .map(o -> HttpStatusCode.valueOf(Integer.parseInt(o.toString())))
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Missing '%s' in the expected response", statusCode))
                );

        // Get the expected body
        String expectedResponseBody = Optional.ofNullable(expectedResponseData.get(body))
                .map(Object::toString)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Missing '%s' in the expected response", body)
                ));

        return new RESTExpectedResponse(expectedHttpStatus, expectedRESTHeaders, expectedResponseBody);
    }

}
