package com.pickle.models.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


import java.util.Objects;

public class HttpExpectedResponse {

    private HttpStatusCode httpStatus;

    private HttpHeaders httpHeaders;

    private String body;

    public HttpExpectedResponse(HttpStatusCode httpStatus, HttpHeaders httpHeaders, String body) {

        this.httpStatus = httpStatus;
        this.httpHeaders = httpHeaders;
        this.body = body;
    }

    public HttpStatusCode getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatusCode httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpExpectedResponse that)) return false;
        return httpStatus == that.httpStatus && Objects.equals(httpHeaders, that.httpHeaders) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpStatus, httpHeaders, body);
    }

    @Override
    public String toString() {
        return "HttpExpectedResponse{" +
                "httpStatus=" + httpStatus +
                ", httpHeaders=" + httpHeaders +
                ", body='" + body + '\'' +
                '}';
    }
}
