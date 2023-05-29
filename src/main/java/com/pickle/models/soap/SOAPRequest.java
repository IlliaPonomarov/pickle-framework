package com.pickle.models.soap;

import com.pickle.models.Request;

public class SOAPRequest extends Request {

    private Request request;

    private SOAPRequest(SOAPRequestBuilder soapRequestBuilder) {
        super(soapRequestBuilder);
    }

    public static class SOAPRequestBuilder extends Request.RequestBuilder {
        private String body;
        private SOAPHeaders headers;
        private String url;

        public SOAPRequestBuilder body(String body, SOAPHeaders headers, String url) {
            this.body = body;
            return this;
        }

        public SOAPRequestBuilder headers(SOAPHeaders headers) {
            this.headers = headers;
            return this;
        }

        public SOAPRequestBuilder url(String url) {
            this.url = url;
            return this;
        }

        public SOAPRequest build() {
            return new SOAPRequest(this) {
            };
        }
    }
}
