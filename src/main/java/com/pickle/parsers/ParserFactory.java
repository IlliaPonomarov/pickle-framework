package com.pickle.parsers;

import com.pickle.parsers.json.JsonParser;
import com.pickle.parsers.xml.XmlParser;
import com.pickle.parsers.yaml.YamlParser;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
/**
 * The InputNotFoundException class for the input path
 * @version 1.0
 * @since 2023-05-07
 * @author Illia Ponomarov
 */
@Component
public class ParserFactory {

    private Map<String, Object> parsers;

    /**
     * The ParserFactory constructor
     */
    public ParserFactory() {
        this.parsers = Map.of(
            "yaml", new YamlParser(),
            "json", new JsonParser(),
            "xml", new XmlParser()
        );
    }

    /**
     * The getParser method
     * @param extension the extension of file
     * @return Parser
     */
    public Parser getParser(String extension) {
        Optional<Parser> parser = Optional.of((Parser) this.parsers.get(extension));

        return parser.orElseGet(parser::orElseThrow);
    }


}
