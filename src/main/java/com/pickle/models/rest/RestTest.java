package com.pickle.models.rest;

import com.pickle.models.Test;
import com.pickle.models.OperationTestCase;

import java.util.Map;
public class RestTest implements Test {

    private  Map<String, RestOperationTestCase> restRequests;

    public Map<String, RestOperationTestCase> getRest() {
        return restRequests;
    }

    public void setRestRequests(Map<String, RestOperationTestCase> restRequests) {
        this.restRequests = restRequests;
    }

    @Override
    public Map<String, ? extends OperationTestCase> getTestCases() {
        return restRequests;
    }
}
