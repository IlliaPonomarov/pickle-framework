package com.pickle.services.parsers.yaml;

import com.pickle.models.ExpectedResponse;
import com.pickle.models.Headers;
import com.pickle.models.Request;
import com.pickle.models.TestCase;
import com.pickle.services.parsers.FileParser;
import com.pickle.utility.enums.ProtocolType;

import java.util.Map;
import java.util.UUID;

public abstract class YamlEndpointParser{

    private final FileParser fileParser;

    public YamlEndpointParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }
    public abstract Map<UUID, ? extends TestCase> createTestCase();

    public abstract TestCase getTestCase(Map.Entry<String, Object> operations, String requestName);

    public abstract ProtocolType extractProtocolType();
    public abstract Headers extractHeaders(Map<String, Object> headers);

    public abstract Request extractRequest(Map.Entry<String, Object> requestInfo);

    public abstract ExpectedResponse extractExpectedResponse(Map.Entry<String, Object> requestInfo);

    public FileParser getFileParser() {
        return fileParser;
    }



}
