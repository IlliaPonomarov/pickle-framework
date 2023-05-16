package com.pickle.services.parsers.xml;

import com.pickle.services.parsers.FileParser;

public abstract class XmlEndpointParser {
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

}
