package com.pickle.models.rest;

import java.util.List;
public class Rest {

    private final List<RestRequest> restRequests;

    private Rest(List<RestRequest> restRequests) {
        this.restRequests = restRequests;
    }

    public List<RestRequest> getRestRequests() {
        return restRequests;
    }

    public void addRestRequest(RestRequest restRequest) {
        this.restRequests.add(restRequest);
    }


}
