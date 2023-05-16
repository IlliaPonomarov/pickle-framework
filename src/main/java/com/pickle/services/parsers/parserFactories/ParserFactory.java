package com.pickle.services.parsers.parserFactories;

import com.pickle.services.parsers.FileParser;
import com.pickle.services.parsers.Parser;
import com.pickle.services.parsers.json.RESTJsonEndpointParser;
import com.pickle.services.parsers.xml.RESTXmlEndpointParser;
import com.pickle.services.parsers.xml.SOAPXmlEndpointParser;
import com.pickle.services.parsers.yaml.RESTYamlEndpointParser;
import com.pickle.utility.enums.ExtensionType;

import java.util.Map;
import java.util.Optional;
/**
 * The InputNotFoundException class for the input path
 * @version 1.0
 * @since 2023-05-07
 * @author Illia Ponomarov
 */
public class ParserFactory {

    private Map<ExtensionType, Object> parsers;
    private final FileParser fileParser;

    /**
     * The ParserFactory constructor
     */
    public ParserFactory(FileParser fileParser) {
        this.fileParser = fileParser;
        this.parsers = Map.of(
                ExtensionType.YAML, new RESTYamlEndpointParser(this.fileParser),
                ExtensionType.JSON, new RESTJsonEndpointParser(this.fileParser),
                ExtensionType.XML, new RESTXmlEndpointParser(this.fileParser)
        );
    }

    /**
     * The getParser method
     * @return Parser
     */
    public EndpointParserFactory getEndpointParserFactory() {
        boolean isSOAP = this.fileParser.isSOAP();
        boolean isREST = this.fileParser.isREST();

        if (isSOAP)
            return new SOAPEndpointParserFactory();

        if (isREST)
            return new RESTEndpointParserFactory();

        return null;
    }

}
