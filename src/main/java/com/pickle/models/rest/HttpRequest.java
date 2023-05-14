package com.pickle.models.rest;

import org.springframework.http.HttpMethod;

import java.util.Map;
import java.util.Objects;

public class HttpRequest {

    private final HttpRequestBuilder httpRequestBuilder;

    private HttpRequest(HttpRequestBuilder httpRequestBuilder) {
        this.httpRequestBuilder = httpRequestBuilder;
    }

    public class HttpRequestBuilder {

        private HttpHeaders httpHeaders;

        private Map<String, String> params;

        private String body;

        private HttpMethod method;

        private String url;

        public HttpRequestBuilder(HttpMethod method, String url) {
            this.method = method;
            this.url = url;
        }

        public HttpRequestBuilder httpHeader(HttpHeaders httpHeaders) {
            this.httpHeaders = httpHeaders;
            return this;
        }

        public HttpRequestBuilder params(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public HttpRequestBuilder body(String body) {
            this.body = body;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }

        @Override
        public String toString() {
            return "HttpRequestBuilder{" +
                    "httpHeader=" + httpHeaders +
                    ", params=" + params +
                    ", body='" + body + '\'' +
                    ", method=" + method +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public HttpHeaders getHttpHeader() {
        return this.httpRequestBuilder.httpHeaders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpRequest that)) return false;
        return Objects.equals(httpRequestBuilder, that.httpRequestBuilder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpRequestBuilder);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "httpRequestBuilder=" + httpRequestBuilder +
                '}';
    }
}
