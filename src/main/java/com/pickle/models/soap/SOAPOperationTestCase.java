package com.pickle.models.soap;

import com.pickle.models.OperationTestCase;

import java.util.Objects;
import java.util.UUID;

public class SOAPOperationTestCase implements OperationTestCase {

    private final UUID id;
    private String requestName;
    private SOAPHeaders soapHeaders;
    private SOAPRequest soapRequest;


    public SOAPOperationTestCase(String requestName, SOAPHeaders soapHeaders, SOAPRequest soapRequest) {
        this.id = UUID.randomUUID();
        this.requestName = requestName;
        this.soapHeaders = soapHeaders;
        this.soapRequest = soapRequest;
    }

    @Override
    public Object getTestCases() {
        return this;
    }

    public String getRequestName() {
        return requestName;
    }

    public UUID getId() {
        return id;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public SOAPHeaders getSoapHeaders() {
        return soapHeaders;
    }

    public void setSoapHeaders(SOAPHeaders soapHeaders) {
        this.soapHeaders = soapHeaders;
    }

    public SOAPRequest getSoapRequest() {
        return soapRequest;
    }

    public void setSoapRequest(SOAPRequest soapRequest) {
        this.soapRequest = soapRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SOAPOperationTestCase that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(requestName, that.requestName) && Objects.equals(soapHeaders, that.soapHeaders) && Objects.equals(soapRequest, that.soapRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestName, soapHeaders, soapRequest);
    }

    @Override
    public String toString() {
        return "SOAPOperationTestCase{" +
                "id=" + id +
                ", requestName='" + requestName + '\'' +
                ", soapHeaders=" + soapHeaders +
                ", soapRequest=" + soapRequest +
                '}';
    }
}
