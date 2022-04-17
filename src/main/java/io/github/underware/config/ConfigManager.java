package io.github.underware.config;

import io.github.underware.UnderWare;
import io.github.underware.config.jsonstream.JsonStreamParser;
import io.github.underware.config.jsonstream.JsonStreamParserLoader;

public enum ConfigManager {

    INSTANCE;

    private final JsonStreamParserLoader jsonStreamParserLoader = new JsonStreamParserLoader();

    public void save() {
        jsonStreamParserLoader.getJsonStreamParsers()
                .forEach(JsonStreamParser::writeJsonStream);
        UnderWare.LOGGER.info("Finished saving config.");
    }

    public void load() {
        jsonStreamParserLoader.getJsonStreamParsers()
                .forEach(JsonStreamParser::readJsonStream);
        UnderWare.LOGGER.info("Finished loading config.");
    }

}
