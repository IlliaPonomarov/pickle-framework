package com.pickle.services.parsers;

import com.pickle.services.parsers.json.JsonParser;
import com.pickle.services.parsers.xml.XmlParser;
import com.pickle.services.parsers.yaml.YamlParser;
import com.pickle.utility.enums.ExtensionType;
import org.springframework.stereotype.Component;

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
                ExtensionType.YAML, new YamlParser(this.fileParser),
                ExtensionType.JSON, new JsonParser(this.fileParser),
                ExtensionType.XML, new XmlParser(this.fileParser)
        );
    }

    /**
     * The getParser method
     * @return Parser
     */
    public Parser getParser() {
        ExtensionType extensionType = this.fileParser.getInputExtensionType();
        Optional<Parser> parser = Optional.of((Parser) this.parsers.get(extensionType));
        System.out.println(parser);
        return parser.orElseGet(parser::orElseThrow);
    }


}
