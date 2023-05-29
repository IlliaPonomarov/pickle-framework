package com.pickle.models.rest;

import com.pickle.models.OperationTestCase;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class RESTOperationTestCase implements OperationTestCase {

    private final UUID id;
    private String requestName;
    private RESTRequest restRequest;
    private RESTExpectedResponse RESTExpectedResponse;

    public RESTOperationTestCase(RESTRequest restRequest, RESTExpectedResponse RESTExpectedResponse, String requestName) {
        this.id = UUID.randomUUID();
        this.restRequest = restRequest;
        this.RESTExpectedResponse = RESTExpectedResponse;
        this.requestName = requestName;
    }

    @Override
    public RESTOperationTestCase getTestCases() {
        return this;
    }

    public RESTRequest getHttpRequest() {
        return restRequest;
    }

    public RESTExpectedResponse getHttpExpectedResponse() {
        return RESTExpectedResponse;
    }

    public String getRequestName() {
        return requestName;
    }
    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public UUID getId() {
        return id;
    }

    public RESTRequest getRestRequest() {
        return restRequest;
    }

    public void setRestRequest(RESTRequest restRequest) {
        this.restRequest = restRequest;
    }

    public com.pickle.models.rest.RESTExpectedResponse getRESTExpectedResponse() {
        return RESTExpectedResponse;
    }

    public void setRESTExpectedResponse(com.pickle.models.rest.RESTExpectedResponse RESTExpectedResponse) {
        this.RESTExpectedResponse = RESTExpectedResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RESTOperationTestCase that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(requestName, that.requestName) && Objects.equals(restRequest, that.restRequest) && Objects.equals(RESTExpectedResponse, that.RESTExpectedResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestName, restRequest, RESTExpectedResponse);
    }

    @Override
    public String toString() {
        return "Rest{" +
                "httpRequest=" + restRequest +
                ", httpExpectedResponse=" + RESTExpectedResponse +
                ", requestName='" + requestName + '\'' +
                '}';
    }

}
