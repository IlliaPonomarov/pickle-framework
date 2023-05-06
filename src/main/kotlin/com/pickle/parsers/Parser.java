package com.pickle.parsers;

public interface Parser {
    Object parse(FileParser fileParser);
    String headerParser();
    String requestParser();
    String expectedResponseParser();
}
