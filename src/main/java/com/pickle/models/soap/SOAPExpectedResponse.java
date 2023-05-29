package com.pickle.models.soap;

import com.pickle.models.ExpectedResponse;
import com.pickle.models.Headers;

import java.util.Objects;

public class SOAPExpectedResponse extends ExpectedResponse {

    private SOAPHeaders soapHeaders;
    private String soapStatus;

    public SOAPExpectedResponse(String body, SOAPHeaders soapHeaders, String soapStatus) {
        super(body, soapHeaders);
        this.soapHeaders = soapHeaders;
        this.soapStatus = soapStatus;
    }

    public SOAPHeaders getSoapHeaders() {
        return soapHeaders;
    }

    public void setSoapHeaders(SOAPHeaders soapHeaders) {
        this.soapHeaders = soapHeaders;
    }

    public String getSoapStatus() {
        return soapStatus;
    }

    public void setSoapStatus(String soapStatus) {
        this.soapStatus = soapStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SOAPExpectedResponse that)) return false;
        return Objects.equals(soapHeaders, that.soapHeaders) && Objects.equals(soapStatus, that.soapStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(soapHeaders, soapStatus);
    }

    @Override
    public String toString() {
        return "SOAPExpectedResponse{" +
                "soapHeaders=" + soapHeaders +
                ", soapStatus='" + soapStatus + '\'' +
                '}';
    }
}
