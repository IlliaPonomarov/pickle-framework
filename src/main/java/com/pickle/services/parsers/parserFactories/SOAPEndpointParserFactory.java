package com.pickle.services.parsers.parserFactories;

import com.pickle.services.parsers.FileParser;
import com.pickle.services.parsers.json.JsonEndpointParser;
import com.pickle.services.parsers.xml.XmlEndpointParser;
import com.pickle.services.parsers.yaml.YamlEndpointParser;

public class SOAPEndpointParserFactory implements EndpointParserFactory{
    @Override
    public YamlEndpointParser createYamlEndpointParser(FileParser fileParser) {
        return null;
    }

    @Override
    public JsonEndpointParser createJsonEndpointParser(FileParser fileParser) {
        return null;
    }

    @Override
    public XmlEndpointParser createXmlEndpointParser(FileParser fileParser) {
        return null;
    }
}
