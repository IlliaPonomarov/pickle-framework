package com.pickle.services.parsers.yaml;

import com.pickle.models.ExpectedResponse;
import com.pickle.models.Headers;
import com.pickle.models.OperationTestCase;
import com.pickle.models.Request;
import com.pickle.services.parsers.FileParser;
import com.pickle.utility.enums.ProtocolType;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class YamlEndpointParser{

    private final FileParser fileParser;
    protected final Yaml yaml;
    protected Map<String, Object> fieldsContent;
    private InputStream inputStream;

    public YamlEndpointParser(FileParser fileParser, Yaml yaml) {
        this.fileParser = fileParser;
        this.yaml = yaml;
    }
    public abstract Map<UUID, ? extends OperationTestCase> createTestCase();

    public abstract OperationTestCase getOperationTestCase(Map.Entry<String, Object> operations, String requestName);

    public abstract ProtocolType extractProtocolType();
    public abstract Headers extractHeaders(Map<String, Object> headers);

    public abstract Request extractRequest(Map.Entry<String, Object> requestInfo);

    public abstract ExpectedResponse extractExpectedResponse(Map.Entry<String, Object> requestInfo);

    public Map<String, Object> parseFile() {
        this.inputStream = this.fileParser.getInputStream();
        Map<String, Object> fieldsContent = this.yaml.load(inputStream);
        return fieldsContent.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public FileParser getFileParser() {
        return fileParser;
    }

    public Yaml getYaml() {
        return yaml;
    }

    public Map<String, Object> getFieldsContent() {
        return fieldsContent;
    }

    public void setFieldsContent(Map<String, Object> fieldsContent) {
        this.fieldsContent = fieldsContent;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YamlEndpointParser that)) return false;
        return Objects.equals(fileParser, that.fileParser) && Objects.equals(yaml, that.yaml) && Objects.equals(fieldsContent, that.fieldsContent) && Objects.equals(inputStream, that.inputStream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileParser, yaml, fieldsContent, inputStream);
    }

    @Override
    public String toString() {
        return "YamlEndpointParser{" +
                "fileParser=" + fileParser +
                ", yaml=" + yaml +
                ", fieldsContent=" + fieldsContent +
                ", inputStream=" + inputStream +
                '}';
    }
}
