package com.pickle.utility.enums;

import java.util.Arrays;

public enum ExtensionType {
    YAML("yaml"),
    JSON("json"),
    XML("xml"),
    NONE("");

    private final String extension;
    ExtensionType(String extension) {
        this.extension = extension;
    }

    public static ExtensionType getExtensionType(String extension) {
        return Arrays.stream(ExtensionType.values()).filter(extensionType -> extensionType.getExtension().equals(extension)).findFirst().orElse(NONE);
    }
    public String getExtension() {
        return extension;
    }

}
