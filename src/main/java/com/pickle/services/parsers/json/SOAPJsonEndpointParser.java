package com.pickle.services.parsers.json;

import com.pickle.models.OperationTestCase;
import com.pickle.services.parsers.FileParser;

import java.util.Map;
import java.util.UUID;

public class SOAPJsonEndpointParser extends JsonEndpointParser{
    public SOAPJsonEndpointParser(FileParser fileParser) {
        super(fileParser);
    }

    @Override
    public void parseFile() {

    }

    @Override
    void extractProtocolType() {

    }

    @Override
    void extractHeaders() {

    }

    @Override
    void extractRequest() {

    }

    @Override
    void extractExepcted() {

    }

    @Override
    public Map<UUID, ? extends OperationTestCase> createTestCase() {
        return null;
    }
}
