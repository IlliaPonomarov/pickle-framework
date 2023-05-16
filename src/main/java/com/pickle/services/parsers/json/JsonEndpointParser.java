package com.pickle.services.parsers.json;

import com.pickle.services.parsers.FileParser;

public abstract class JsonEndpointParser {

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

}
