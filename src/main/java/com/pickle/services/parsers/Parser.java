package com.pickle.services.parsers;

import com.pickle.models.rest.HttpHeaders;
import com.pickle.utility.enums.ProtocolType;

public interface Parser {
    default ProtocolType getRequestType(String protocolType) {
        return ProtocolType.getProtocolType(protocolType).orElseThrow(() -> new IllegalArgumentException("Invalid protocol type"));
    }
}
