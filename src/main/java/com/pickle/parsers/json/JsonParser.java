package com.pickle.parsers.json;

import com.pickle.parsers.FileParser;
import com.pickle.parsers.Parser;

public class JsonParser implements Parser {

    private final FileParser fileParser;

    public JsonParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }
    @Override
    public Object parse(FileParser fileParser) {
        return null;
    }

    @Override
    public String headerParser() {
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
