package com.pickle.models.rest;

import com.pickle.models.TestCase;

import java.util.Map;
import java.util.Objects;

public class RestTestCase implements TestCase {

    private HttpRequest httpRequest;
    private HttpExpectedResponse httpExpectedResponse;

    private  String requestName;

    public RestTestCase() {
    }

    public RestTestCase(HttpRequest httpRequest, HttpExpectedResponse httpExpectedResponse, String requestName) {
        this.httpRequest = httpRequest;
        this.httpExpectedResponse = httpExpectedResponse;
        this.requestName = requestName;
    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

    public HttpExpectedResponse getHttpExpectedResponse() {
        return httpExpectedResponse;
    }

    public String getRequestName() {
        return requestName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestTestCase rest)) return false;
        return Objects.equals(httpRequest, rest.httpRequest) && Objects.equals(httpExpectedResponse, rest.httpExpectedResponse) && Objects.equals(requestName, rest.requestName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpRequest, httpExpectedResponse, requestName);
    }

    @Override
    public String toString() {
        return "Rest{" +
                "httpRequest=" + httpRequest +
                ", httpExpectedResponse=" + httpExpectedResponse +
                ", requestName='" + requestName + '\'' +
                '}';
    }

    @Override
    public Map<String, ? extends TestCase> getTestCases() {
        return null;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }
}
