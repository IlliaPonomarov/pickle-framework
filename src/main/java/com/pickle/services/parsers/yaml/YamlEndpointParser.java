package com.pickle.services.parsers.yaml;

import com.pickle.services.parsers.FileParser;
import com.pickle.utility.enums.ProtocolType;

import java.util.Map;

public abstract class YamlEndpointParser{

    private final FileParser fileParser;

    public YamlEndpointParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }
    public abstract Map<String, Object> createTestCase();

    public abstract ProtocolType extractProtocolType();
    public abstract void extractHeaders();

    public abstract void extractRequest();

    public abstract void extractExcpevtedResponse();

    public FileParser getFileParser() {
        return fileParser;
    }



}
