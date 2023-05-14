package com.pickle.models.rest;

import java.util.Objects;

public class RestRequest {

    private final HttpRequest httpRequest;
    private final HttpExpectedResponse httpExpectedResponse;

    private final String requestName;

    private RestRequest(HttpRequest httpRequest, HttpExpectedResponse httpExpectedResponse, String requestName) {
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
        if (!(o instanceof RestRequest restRequest)) return false;
        return Objects.equals(httpRequest, restRequest.httpRequest) && Objects.equals(httpExpectedResponse, restRequest.httpExpectedResponse) && Objects.equals(requestName, restRequest.requestName);
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
}
