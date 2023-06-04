package com.pickle.utility.enums;

public enum ExpectedResponseValues {
    EXPECTED_RESPONSE("expected-response"),
    STATUS_CODE("status"),
    BODY("body"),
    HEADERS("headers");

    private final String value;

    ExpectedResponseValues(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
