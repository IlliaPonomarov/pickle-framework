package com.pickle.services.parsers.parserFactories;

import com.pickle.services.parsers.FileParser;
import com.pickle.services.parsers.json.JsonEndpointParser;
import com.pickle.services.parsers.json.RESTJsonEndpointParser;
import com.pickle.services.parsers.xml.RESTXmlEndpointParser;
import com.pickle.services.parsers.xml.XmlEndpointParser;
import com.pickle.services.parsers.yaml.RESTYamlEndpointParser;
import com.pickle.services.parsers.yaml.YamlEndpointParser;

public class RESTEndpointParserFactory implements EndpointParserFactory{
    @Override
    public YamlEndpointParser createYamlEndpointParser(FileParser fileParser) {
        return new RESTYamlEndpointParser(fileParser);
    }

    @Override
    public JsonEndpointParser createJsonEndpointParser(FileParser fileParser) {
        return new RESTJsonEndpointParser(fileParser);
    }

    @Override
    public XmlEndpointParser createXmlEndpointParser(FileParser fileParser) {
        return new RESTXmlEndpointParser(fileParser);
    }
}
