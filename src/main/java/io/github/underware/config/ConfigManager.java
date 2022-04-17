package io.github.underware.config;

import io.github.underware.UnderWare;
import io.github.underware.config.jsonstream.JsonStreamParser;
import io.github.underware.config.jsonstream.JsonStreamParserLoader;

public enum ConfigManager {

    INSTANCE;

    private final JsonStreamParserLoader jsonStreamParserLoader = new JsonStreamParserLoader();

    public void save() {
        new Thread(() -> {
            jsonStreamParserLoader.getJsonStreamParsers()
                    .forEach(JsonStreamParser::writeJsonStream);
            UnderWare.LOGGER.info("Finished saving config.");
        }).start();
    }

    public void load() {
        new Thread(() -> {
            jsonStreamParserLoader.getJsonStreamParsers()
                    .forEach(JsonStreamParser::readJsonStream);
            UnderWare.LOGGER.info("Finished loading config.");
        }).start();
    }

}
