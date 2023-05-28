package com.pickle.models.rest;

import com.pickle.models.OperationTestCase;

import java.util.Map;
import java.util.Objects;

public class RESTOperationTestCase implements OperationTestCase {

    private RESTRequest RESTRequest;
    private RESTExpectedResponse RESTExpectedResponse;

    private  String requestName;

    public RESTOperationTestCase() {
    }

    public RESTOperationTestCase(RESTRequest RESTRequest, RESTExpectedResponse RESTExpectedResponse, String requestName) {
        this.RESTRequest = RESTRequest;
        this.RESTExpectedResponse = RESTExpectedResponse;
        this.requestName = requestName;
    }

    public RESTRequest getHttpRequest() {
        return RESTRequest;
    }

    public RESTExpectedResponse getHttpExpectedResponse() {
        return RESTExpectedResponse;
    }

    public String getRequestName() {
        return requestName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RESTOperationTestCase rest)) return false;
        return Objects.equals(RESTRequest, rest.RESTRequest) && Objects.equals(RESTExpectedResponse, rest.RESTExpectedResponse) && Objects.equals(requestName, rest.requestName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(RESTRequest, RESTExpectedResponse, requestName);
    }

    @Override
    public String toString() {
        return "Rest{" +
                "httpRequest=" + RESTRequest +
                ", httpExpectedResponse=" + RESTExpectedResponse +
                ", requestName='" + requestName + '\'' +
                '}';
    }

    @Override
    public Map<String, ? extends OperationTestCase> getTestCases() {
        return null;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }
}
