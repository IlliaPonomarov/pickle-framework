package com.pickle.services.communications;

import com.pickle.models.OperationTestCase;
import com.pickle.models.rest.RESTRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

public class RESTCommunication {

    private final RestTemplate restTemplate;
    private final Map<UUID, Map<UUID, ? extends OperationTestCase>> operations;

    public RESTCommunication(Map<UUID, Map<UUID, ? extends OperationTestCase>> operations) {
        this.restTemplate = new RestTemplate();
        this.operations = operations;
    }

    public void sendRequest() {
        System.out.println();
        for (Map.Entry<UUID, Map<UUID, ? extends OperationTestCase>> operations : operations.entrySet()) {
            for (Map.Entry<UUID, ? extends OperationTestCase> testCase : operations.getValue().entrySet()) {
                OperationTestCase operation = (OperationTestCase) testCase.getValue();
                String requestName = operation.getRequestName();
                RESTRequest restRequest1 = (RESTRequest) operation.getTestCases();

                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", "application/json");


            }
        }
    }


}
