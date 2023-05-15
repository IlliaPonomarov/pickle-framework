package com.pickle.services.parsers.xml;

import com.pickle.models.rest.HttpHeaders;
import com.pickle.services.parsers.FileParser;
import com.pickle.services.parsers.Parser;

public class XmlParser implements Parser {

    private final FileParser fileParser;

    public XmlParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }
    @Override
    public Object parseFile() {
        return null;
    }

    @Override
    public HttpHeaders createRestTestCase() {
        return null;
    }

    @Override
    public String requestParser() {
        return null;
    }

    @Override
    public String expectedResponseParser() {
        return null;
    }
}
