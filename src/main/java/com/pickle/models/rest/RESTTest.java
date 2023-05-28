package com.pickle.models.rest;

import com.pickle.models.Test;
import com.pickle.models.OperationTestCase;

import java.util.Map;
public class RESTTest implements Test {

    private  Map<String, RESTOperationTestCase> restRequests;

    public Map<String, RESTOperationTestCase> getRest() {
        return restRequests;
    }

    public void setRestRequests(Map<String, RESTOperationTestCase> restRequests) {
        this.restRequests = restRequests;
    }

    @Override
    public Map<String, ? extends OperationTestCase> getTestCases() {
        return restRequests;
    }
}
