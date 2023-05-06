package com.pickle.parsers;

import com.pickle.parsers.json.JsonParser;
import com.pickle.parsers.xml.XmlParser;
import com.pickle.parsers.yaml.YamlParser;

import java.awt.desktop.OpenFilesHandler;
import java.util.Map;
import java.util.Optional;

public class ParserFactory {

    private Map<String, Object> parsers;

    public ParserFactory() {
        this.parsers = Map.of(
            "yaml", new YamlParser(),
            "json", new JsonParser(),
            "xml", new XmlParser()
        );
    }

    public Parser getParser(String extension) {
        Optional<Parser> parser = Optional.of((Parser) this.parsers.get(extension));

        return parser.orElseGet(parser::orElseThrow);
    }


}
