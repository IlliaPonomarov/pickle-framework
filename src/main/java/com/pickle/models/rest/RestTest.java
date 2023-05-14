package com.pickle.models.rest;

import com.pickle.models.Test;
import com.pickle.models.TestCase;

import java.util.Map;
public class RestTest implements Test {

    private  Map<String, RestTestCase> restRequests;

    public Map<String, RestTestCase> getRest() {
        return restRequests;
    }

    public void setRestRequests(Map<String, RestTestCase> restRequests) {
        this.restRequests = restRequests;
    }

    @Override
    public Map<String, ? extends TestCase> getTestCases() {
        return restRequests;
    }
}
