package com.pickle.models.rest;

import com.pickle.models.Request;
import org.springframework.http.HttpMethod;

import java.util.Map;
import java.util.Objects;

public class RESTRequest extends Request {

    private HttpRequestBuilder httpRequestBuilder;

    public RESTRequest(HttpRequestBuilder httpRequestBuilder) {
        super(httpRequestBuilder);
        this.httpRequestBuilder = httpRequestBuilder;
    }

    public RESTRequest(){
        ;
    }

    public static class HttpRequestBuilder extends Request.RequestBuilder{

        private RESTHeaders RESTHeaders;

        private Map<String, Object> params;

        private String body;

        private HttpMethod method;

        private String url;

        public HttpRequestBuilder(HttpMethod method, String url) {
            this.method = method;
            this.url = url;
        }

        public HttpRequestBuilder httpHeader(RESTHeaders RESTHeaders) {
            this.RESTHeaders = RESTHeaders;
            return this;
        }

        public HttpRequestBuilder params(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public HttpRequestBuilder body(String body) {
            this.body = body;
            return this;
        }

        public RESTRequest build() {
            return new RESTRequest(this);
        }

        @Override
        public String toString() {
            return "HttpRequestBuilder{" +
                    "httpHeader=" + RESTHeaders +
                    ", params=" + params +
                    ", body='" + body + '\'' +
                    ", method=" + method +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public RESTHeaders getHttpHeader() {
        return this.httpRequestBuilder.RESTHeaders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RESTRequest that)) return false;
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
