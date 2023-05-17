package com.pickle.utility.enums;

public enum HeadersValue {

    AUTHORIZATION("Authorization"),
    CONTENT_TYPE("Content-Type"),
    ACCEPT("Accept"),
    ACCEPT_LANGUAGE("Accept-Language"),
    ACCEPT_ENCODING("Accept-Encoding"),
    USER_AGENT("User-Agent"),
    CONTENT_LENGTH("Content-Length"),
    CONTENT_MD5("Content-MD5"),
    CONTENT_ENCODING("Content-Encoding"),
    CONTENT_LANGUAGE("Content-Language"),
    CONTENT_LOCATION("Content-Location"),
    CACHE_CONTROL("Cache-Control"),
    X_REQUESTED_WITH("X-Requested-With"),

    // Custom headers
    COOKIE("Cookie"),
    X_API_KEY("X-API-Key"),
    X_API_TOKEN("X-API-Token"),
    X_API_VERSION("X-API-Version"),
    X_API_CLIENT("X-API-Client"),
    X_API_CLIENT_VERSION("X-API-Client-Version"),
    X_API_CLIENT_OS("X-API-Client-OS"),
    X_API_CLIENT_OS_VERSION("X-API-Client-OS-Version"),
    X_API_CLIENT_DEVICE("X-API-Client-Device"),
    X_API_CLIENT_DEVICE_VERSION("X-API-Client-Device-Version"),
    X_API_CLIENT_DEVICE_TYPE("X-API-Client-Device-Type");

    private final String value;

    HeadersValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
