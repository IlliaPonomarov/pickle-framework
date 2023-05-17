package com.pickle.models;

public abstract class Request {

    private RequestBuilder requestBuilder;

    public Request(RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    public Request() {

    }

    public static class RequestBuilder {

        private String body;

        private Headers headers;

        private String url;

        public RequestBuilder body(String body, Headers headers, String url) {
            this.body = body;
            return this;
        }

        public RequestBuilder headers(Headers headers) {
            this.headers = headers;
            return this;
        }

        public RequestBuilder url(String url) {
            this.url = url;
            return this;
        }


        public Request build() {
            return new Request(this) {
            };
        }
    }
}
