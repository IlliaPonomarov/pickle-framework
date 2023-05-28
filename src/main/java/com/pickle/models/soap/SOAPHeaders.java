package com.pickle.models.soap;

import com.pickle.models.Headers;
import com.pickle.models.rest.HttpHeaders;

public class SOAPHeaders extends Headers {
    private SOAPHeaders (SOAPHeaderBuilder builder) {
        super(builder);
    }
    public static class SOAPHeaderBuilder extends Headers.HeadersBuilder {
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

        public SOAPHeaderBuilder( ) {

        }

        public SOAPHeaders.SOAPHeaderBuilder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public SOAPHeaders.SOAPHeaderBuilder contentLength(String contentLength) {
            this.contentLength = contentLength;
            return this;
        }

        public SOAPHeaders.SOAPHeaderBuilder contentEncoding(String contentEncoding) {
            this.contentEncoding = contentEncoding;
            return this;
        }

        public SOAPHeaders.SOAPHeaderBuilder contentLanguage(String contentLanguage) {
            this.contentLanguage = contentLanguage;
            return this;
        }

        public SOAPHeaders.SOAPHeaderBuilder contentLocation(String contentLocation) {
            this.contentLocation = contentLocation;
            return this;
        }

        public SOAPHeaders.SOAPHeaderBuilder contentMD5(String contentMD5) {
            this.contentMD5 = contentMD5;
            return this;
        }

        public SOAPHeaders.SOAPHeaderBuilder authorization(String authorization) {
            this.authorization = authorization;
            return this;
        }

        public SOAPHeaders.SOAPHeaderBuilder accept(String accept) {
            this.accept = accept;
            return this;
        }

        public SOAPHeaders.SOAPHeaderBuilder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public SOAPHeaders.SOAPHeaderBuilder cookie(String cookie) {
            this.cookie = cookie;
            return this;
        }

        public SOAPHeaders.SOAPHeaderBuilder cacheControl(String cacheControl) {
            this.cacheControl = cacheControl;
            return this;
        }

        public SOAPHeaders build() {
            return new SOAPHeaders(this);
        }

        public SOAPHeaders.SOAPHeaderBuilder xRequestedWith(String xRequestedWith) {
            this.xRequestedWith = xRequestedWith;
            return this;
        }
    }
}
