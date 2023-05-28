package com.pickle.services.parsers.yaml;

import com.pickle.models.ExpectedResponse;
import com.pickle.models.Headers;
import com.pickle.models.Request;
import com.pickle.models.TestCase;
import com.pickle.models.soap.SoapTestCase;
import com.pickle.services.parsers.FileParser;
import com.pickle.utility.enums.ProtocolType;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SOAPYamlEndpointParser extends YamlEndpointParser{
    public SOAPYamlEndpointParser(FileParser fileParser) {
        super(fileParser, new Yaml());
    }

    @Override
    public Map<UUID, ? extends TestCase>  createTestCase() {
        Map<UUID, SoapTestCase> soapTestCases = new HashMap<>();
        String soapProtocolType = ProtocolType.SOAP.getProtocolType();


        return soapTestCases;
    }

    @Override
    public TestCase getTestCase(Map.Entry<String, Object> operations, String requestName) {
        return null;
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
    public Request extractRequest(Map.Entry<String, Object> requestInfo) {
        return null;
    }

    @Override
    public ExpectedResponse extractExpectedResponse(Map.Entry<String, Object> requestInfo) {
        return null;
    }
}
