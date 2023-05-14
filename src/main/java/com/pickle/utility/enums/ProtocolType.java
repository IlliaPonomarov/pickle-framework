package com.pickle.utility.enums;

import java.util.Arrays;
import java.util.Optional;

public enum ProtocolType {
    REST("rest"),
    SOAP("soap");

    private String requestType;

    ProtocolType(String requestType) {
        this.requestType = requestType;
    }

    public String getProtocolType() {
        return requestType;
    }

    public static Optional<ProtocolType> getProtocolType(String requestType) {
        return Arrays.stream(ProtocolType.values()).filter(type -> type.getProtocolType().equals(requestType)).findFirst();
    }
}
