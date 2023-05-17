package com.pickle.services.parsers.yaml;

import com.pickle.models.ExpectedResponse;
import com.pickle.models.Headers;
import com.pickle.models.Request;
import com.pickle.models.TestCase;
import com.pickle.services.parsers.FileParser;
import com.pickle.utility.enums.ProtocolType;

import java.util.Map;
import java.util.UUID;

public class SOAPYamlEndpointParser extends YamlEndpointParser{
    public SOAPYamlEndpointParser(FileParser fileParser) {
        super(fileParser);
    }

    @Override
    public Map<UUID, ? extends TestCase>  createTestCase() {
        return null;
    }

    @Override
    public TestCase getTestCase(Map.Entry<String, Object> operations, String requestName) {
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
    public Request extractRequest(Map.Entry<String, Object> requestInfo) {
        return null;
    }

    @Override
    public ExpectedResponse extractExpectedResponse(Map.Entry<String, Object> requestInfo) {
        return null;
    }
}
