package com.pickle.models.soap;

import com.pickle.models.OperationTestCase;

import java.util.Objects;
import java.util.UUID;

public class SOAPOperationTestCase implements OperationTestCase {

    private final UUID id;
    private String requestName;
    private SOAPExpectedResponse soapExpectedResponse;
    private SOAPRequest soapRequest;


    public SOAPOperationTestCase(String requestName, SOAPExpectedResponse soapExpectedResponse, SOAPRequest soapRequest) {
        this.id = UUID.randomUUID();
        this.requestName = requestName;
        this.soapExpectedResponse = soapExpectedResponse;
        this.soapRequest = soapRequest;
    }

    @Override
    public OperationTestCase getTestCases() {
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

    public SOAPExpectedResponse getSoapExpectedResponse() {
        return soapExpectedResponse;
    }

    public void setSoapExpectedResponse(SOAPExpectedResponse soapExpectedResponse) {
        this.soapExpectedResponse = soapExpectedResponse;
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
        return Objects.equals(id, that.id) && Objects.equals(requestName, that.requestName) && Objects.equals(soapExpectedResponse, that.soapExpectedResponse) && Objects.equals(soapRequest, that.soapRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestName, soapExpectedResponse, soapRequest);
    }



}
