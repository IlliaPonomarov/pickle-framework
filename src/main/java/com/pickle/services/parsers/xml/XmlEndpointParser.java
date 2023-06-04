package com.pickle.services.parsers.xml;

import com.pickle.models.OperationTestCase;
import com.pickle.services.parsers.EndpointParser;
import com.pickle.services.parsers.FileParser;

import java.util.Map;
import java.util.UUID;

public abstract class XmlEndpointParser implements EndpointParser {
    private final FileParser fileParser;

    public XmlEndpointParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public abstract void parseFile();

    public abstract void extractProtocolType();

    public abstract void extractHeaders();

    public abstract void extractRequest();

    public abstract void extractExepcted();

    public FileParser getFileParser() {
        return fileParser;
    }

    public abstract Map<UUID, ? extends OperationTestCase> createTestCase();
}
