package com.pickle.models.rest;

import com.pickle.models.ExpectedResponse;
import org.springframework.http.HttpStatusCode;


import java.util.Objects;

public class RESTExpectedResponse extends ExpectedResponse {

    private HttpStatusCode httpStatus;

    private RESTHeaders RESTHeaders;

    private String body;

    public RESTExpectedResponse(HttpStatusCode httpStatus, RESTHeaders RESTHeaders, String body) {
        super(body, RESTHeaders);

        this.httpStatus = httpStatus;
        this.RESTHeaders = RESTHeaders;
        this.body = body;
    }

    public HttpStatusCode getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatusCode httpStatus) {
        this.httpStatus = httpStatus;
    }

    public RESTHeaders getHttpHeaders() {
        return RESTHeaders;
    }

    public void setHttpHeaders(RESTHeaders RESTHeaders) {
        this.RESTHeaders = RESTHeaders;
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
        if (!(o instanceof RESTExpectedResponse that)) return false;
        return httpStatus == that.httpStatus && Objects.equals(RESTHeaders, that.RESTHeaders) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpStatus, RESTHeaders, body);
    }

    @Override
    public String toString() {
        return "HttpExpectedResponse{" +
                "httpStatus=" + httpStatus +
                ", httpHeaders=" + RESTHeaders +
                ", body='" + body + '\'' +
                '}';
    }
}
