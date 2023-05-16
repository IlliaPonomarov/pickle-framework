package com.pickle.services.parsers.parserFactories;

import com.pickle.services.parsers.FileParser;
import com.pickle.services.parsers.json.JsonEndpointParser;
import com.pickle.services.parsers.xml.XmlEndpointParser;
import com.pickle.services.parsers.yaml.YamlEndpointParser;

public interface EndpointParserFactory {
    YamlEndpointParser createYamlEndpointParser(FileParser fileParser);
    JsonEndpointParser createJsonEndpointParser(FileParser fileParser);

    XmlEndpointParser createXmlEndpointParser(FileParser fileParser);
}
