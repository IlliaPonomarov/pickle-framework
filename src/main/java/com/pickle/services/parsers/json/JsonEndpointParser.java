package com.pickle.services.parsers.json;

import com.pickle.models.OperationTestCase;
import com.pickle.services.parsers.EndpointParser;
import com.pickle.services.parsers.FileParser;

import java.util.Map;
import java.util.UUID;

public abstract class JsonEndpointParser implements EndpointParser {

    private final FileParser fileParser;

    public JsonEndpointParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }
    public abstract void parseFile();

    abstract void extractProtocolType();

    abstract void extractHeaders();

    abstract void extractRequest();

    abstract void extractExepcted();

    public FileParser getFileParser() {
        return fileParser;
    }

    public abstract Map<UUID,? extends OperationTestCase> createTestCase();
}
