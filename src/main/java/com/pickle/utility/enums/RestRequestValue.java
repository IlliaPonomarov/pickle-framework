package com.pickle.utility.enums;

public enum RestRequestValue {
    URL("url"),
    BODY("body"),
    METHOD("method"),
    HEADERS("headers"),
    PARAMS("params");

    private final String value;

    RestRequestValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
