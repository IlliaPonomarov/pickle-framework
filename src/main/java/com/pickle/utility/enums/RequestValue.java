package com.pickle.utility.enums;

public enum RequestValue {
    REQUEST("request"),
    URL("url"),
    BODY("body"),
    METHOD("method"),
    HEADERS("headers"),
    PARAMS("params");

    private final String value;

    RequestValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
