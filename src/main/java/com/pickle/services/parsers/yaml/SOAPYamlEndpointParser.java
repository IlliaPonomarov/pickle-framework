package com.pickle.services.parsers.yaml;

import com.pickle.models.ExpectedResponse;
import com.pickle.models.Headers;
import com.pickle.models.OperationTestCase;
import com.pickle.models.Request;
import com.pickle.models.rest.RESTExpectedResponse;
import com.pickle.models.rest.RESTOperationTestCase;
import com.pickle.models.soap.SOAPExpectedResponse;
import com.pickle.models.soap.SOAPOperationTestCase;
import com.pickle.models.soap.SOAPRequest;
import com.pickle.services.parsers.FileParser;
import com.pickle.utility.enums.ProtocolType;
import com.pickle.utility.enums.RequestValue;
import com.pickle.utility.enums.ExpectedResponseValues;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class SOAPYamlEndpointParser extends YamlEndpointParser{
    public SOAPYamlEndpointParser(FileParser fileParser) {
        super(fileParser, new Yaml());
    }

    @Override
    public Map<UUID, ? extends OperationTestCase>  createTestCase() {
        Map<UUID, SOAPOperationTestCase> soapTestCases = new HashMap<>();
        String soapProtocolType = ProtocolType.SOAP.getProtocolType();
        super.fieldsContent = super.parseFile();

        if (super.fieldsContent.containsKey(soapProtocolType)) {
            Map<String, Object> soapContent = (Map<String, Object>) super.fieldsContent.get(soapProtocolType);
            Stream<Map.Entry<String, Object>> operationsStream = soapContent.entrySet().stream();

            operationsStream.forEach(operations -> {
                String requestName = operations.getKey();
                SOAPOperationTestCase soapTestCase = (SOAPOperationTestCase) this.getOperationTestCase(operations, requestName);
                UUID randomUUID = UUID.randomUUID();

                soapTestCases.put(randomUUID, soapTestCase);
            });
        }

        return soapTestCases;
    }

    @Override
    public OperationTestCase getOperationTestCase(Map.Entry<String, Object> operations, String requestName) {
        SOAPRequest soapRequest = null;
        SOAPExpectedResponse soapExpectedResponse = null;

        String requestField = RequestValue.REQUEST.getValue();
        String expectedResponse = ExpectedResponseValues.EXPECTED_RESPONSE.getValue();

        final Map<String, Object> operationFields = (Map<String, Object>) operations.getValue();

        // Get the request from the yaml file and create the request object
        Optional<Map.Entry<String, Object>> requestFields = operationFields.entrySet().stream()
                .filter(requestInfo -> requestInfo.getKey().equals(requestField))
                .findFirst();

        // If the request is present, create the request object
        if (requestFields.isPresent())
            soapRequest = extractRequest(requestFields.get());

        // Get the expected response from the yaml file and create the expected response object
        Optional<Map.Entry<String, Object>> expectedResponseEntry = operationFields.entrySet().stream()
                .filter(requestInfo -> requestInfo.getKey().equals(expectedResponse))
                .findFirst();

        // If the expected response is present, create the expected response object
        if (expectedResponseEntry.isPresent())
            soapExpectedResponse = extractExpectedResponse(expectedResponseEntry.get());

        return new SOAPOperationTestCase(requestName, soapExpectedResponse, soapRequest);
    }

    @Override
    public Map<String, Object> parseFile() {
        return null;
    }

    @Override
    public ProtocolType extractProtocolType() {
        return null;
    }

    @Override
    public Headers extractHeaders(Map<String, Object> headers) {
        return null;
    }


    @Override
    public SOAPRequest extractRequest(Map.Entry<String, Object> requestInfo) {
        return null;
    }

    @Override
    public SOAPExpectedResponse extractExpectedResponse(Map.Entry<String, Object> requestInfo) {
        return null;
    }
}
