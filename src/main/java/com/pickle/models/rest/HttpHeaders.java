package com.pickle.models.rest;

public class HttpHeaders {
    private final HttpHeaderBuilder httpHeaderBuilder;

    private HttpHeaders(HttpHeaderBuilder httpHeaderBuilder) {
        this.httpHeaderBuilder = httpHeaderBuilder;
    }

    public class HttpHeaderBuilder {
        private String contentType;
        private String contentLength;
        private String contentEncoding;
        private String contentLanguage;
        private String contentLocation;
        private String contentMD5;
        private String authorization;

        private String accept;

        private String userAgent;

        private String cookie;

        private String cacheControl;

        private String xRequestedWith;

        public HttpHeaderBuilder( ) {

        }

        public HttpHeaderBuilder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public HttpHeaderBuilder contentLength(String contentLength) {
            this.contentLength = contentLength;
            return this;
        }

        public HttpHeaderBuilder contentEncoding(String contentEncoding) {
            this.contentEncoding = contentEncoding;
            return this;
        }

        public HttpHeaderBuilder contentLanguage(String contentLanguage) {
            this.contentLanguage = contentLanguage;
            return this;
        }

        public HttpHeaderBuilder contentLocation(String contentLocation) {
            this.contentLocation = contentLocation;
            return this;
        }

        public HttpHeaderBuilder contentMD5(String contentMD5) {
            this.contentMD5 = contentMD5;
            return this;
        }

        public HttpHeaderBuilder authorization(String authorization) {
            this.authorization = authorization;
            return this;
        }

        public HttpHeaderBuilder accept(String accept) {
            this.accept = accept;
            return this;
        }

        public HttpHeaderBuilder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public HttpHeaderBuilder cookie(String cookie) {
            this.cookie = cookie;
            return this;
        }

        public HttpHeaderBuilder cacheControl(String cacheControl) {
            this.cacheControl = cacheControl;
            return this;
        }

        public HttpHeaders build() {
            return new HttpHeaders(this);
        }
    }

}
