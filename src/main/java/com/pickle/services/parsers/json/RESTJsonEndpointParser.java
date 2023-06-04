package com.pickle.services.parsers.json;

import com.pickle.models.OperationTestCase;
import com.pickle.services.parsers.FileParser;

import java.util.Map;
import java.util.UUID;

public class RESTJsonEndpointParser extends JsonEndpointParser  {
    public RESTJsonEndpointParser(FileParser fileParser) {
        super(fileParser);
    }

    @Override
    public void parseFile() {

    }

    @Override
    public void extractProtocolType() {

    }

    @Override
    public void extractHeaders() {

    }

    @Override
    public void extractRequest() {

    }

    @Override
    public void extractExepcted() {

    }

    @Override
    public Map<UUID, ? extends OperationTestCase> createTestCase() {
        return null;
    }
}
