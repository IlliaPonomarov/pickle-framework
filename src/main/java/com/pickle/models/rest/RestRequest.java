package com.pickle.models.rest;

import java.util.Objects;

public class Rest {

    private final HttpRequest httpRequest;
    private final HttpExpectedResponse httpExpectedResponse;

    private final String requestName;

    private Rest(HttpRequest httpRequest, HttpExpectedResponse httpExpectedResponse, String requestName) {
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
        if (!(o instanceof Rest rest)) return false;
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
}
