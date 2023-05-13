package com.pickle.utility.enums;

import java.util.Arrays;

/**
 * The InputNotFoundException class for the input path
 * @version 1.0
 * @since 2023-05-07
 * @author Illia Ponomarov
 */
public enum ExtensionType {
    YAML("yaml"),
    JSON("json"),
    XML("xml"),
    NONE("");

    /**
     * @param extension is object of String class
     */
    private final String extension;

    /**
     * The ExtensionType constructor
     * @param extension the extension
     */
    ExtensionType(String extension) {
        this.extension = extension;
    }

    /**
     * The getExtensionType method
     * @param extension the extension
     * @return ExtensionType
     */
    public static ExtensionType getExtensionType(String extension) {
        return Arrays.stream(ExtensionType.values()).filter(extensionType -> extensionType.getExtension().equals(extension)).findFirst().orElse(NONE);
    }

    /**
     * The getExtension method
     * @return String
     */
    public String getExtension() {
        return extension;
    }

}
