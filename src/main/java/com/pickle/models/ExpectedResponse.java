package com.pickle.models;

public abstract class ExpectedResponse {


    private Headers httpHeaders;

    private String body;

    public ExpectedResponse(String body, Headers httpHeaders) {
        this.body = body;
        this.httpHeaders = httpHeaders;
    }

    public String getBody() {
        return body;
    }

    public Headers getHttpHeaders() {
        return httpHeaders;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setHttpHeaders(Headers httpHeaders) {
        this.httpHeaders = httpHeaders;
    }
}
