package com.pickle.services.parsers.yaml;

import com.pickle.services.parsers.FileParser;
import com.pickle.utility.enums.ProtocolType;

import java.util.Map;

public class SOAPYamlEndpointParser extends YamlEndpointParser{
    public SOAPYamlEndpointParser(FileParser fileParser) {
        super(fileParser);
    }

    @Override
    public Map<String, Object> createTestCase() {
        return null;
    }

    @Override
    public ProtocolType extractProtocolType() {
        return null;
    }

    @Override
    public void extractHeaders() {

    }

    @Override
    public void extractRequest() {

    }

    @Override
    public void extractExcpevtedResponse() {

    }
}
