package com.pickle.models;

public abstract class Headers {

    private HeadersBuilder headersBuilder;

    public Headers(HeadersBuilder headersBuilder) {
        this.headersBuilder = headersBuilder;
    }
    public static class HeadersBuilder {
        private String accept;

        private String contentType;

        private String authorization;

        private String cookie;

        private String userAgent;

        private String contentLength;

        private String contentMD5;

        private String contentEncoding;

        private String contentLanguage;

        private String contentLocation;

        private String cacheControl;

        private String xRequestedWith;

        public HeadersBuilder accept(String accept) {
            this.accept = accept;
            return this;
        }

        public HeadersBuilder contentType(String contentType) {
                    this.contentType = contentType;
                    return this;
                }

        public HeadersBuilder authorization(String authorization) {
                    this.authorization = authorization;
                    return this;
                }

        public HeadersBuilder cookie(String cookie) {
                    this.cookie = cookie;
                    return this;
                }

        public HeadersBuilder userAgent(String userAgent) {
                    this.userAgent = userAgent;
                    return this;
                }

        public HeadersBuilder contentLength(String contentLength) {
                    this.contentLength = contentLength;
                    return this;
                }

        public HeadersBuilder contentMD5(String contentMD5) {
                    this.contentMD5 = contentMD5;
                    return this;
                }

        public HeadersBuilder contentEncoding(String contentEncoding) {
                    this.contentEncoding = contentEncoding;
                    return this;
                }

        public HeadersBuilder contentLanguage(String contentLanguage) {
                    this.contentLanguage = contentLanguage;
                    return this;
                }

        public HeadersBuilder contentLocation(String contentLocation) {
                    this.contentLocation = contentLocation;
                    return this;
                }

        public HeadersBuilder cacheControl(String cacheControl) {
                    this.cacheControl = cacheControl;
                    return this;
                }

        public HeadersBuilder xRequestedWith(String xRequestedWith) {
                    this.xRequestedWith = xRequestedWith;
                    return this;
                }

        public Headers build() {
            return new Headers(this) {
            };
        }
    }


}
