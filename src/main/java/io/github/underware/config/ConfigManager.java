package io.github.underware.config;

import io.github.underware.UnderWare;
import io.github.underware.config.json.JsonStreamParser;
import io.github.underware.core.config.GlobalsJsonStreamParser;
import io.github.underware.module.config.ModuleJsonStreamParser;

// TODO: 4/17/22 Maybe use ReflectionUtil to check all packages for JsonStreamParser classes?
//  I don't think this would be very time efficient but it would also safe a lot of hard coding.
public enum ConfigManager {

    INSTANCE;

    private final JsonStreamParser moduleJsonStreamParser = new ModuleJsonStreamParser();
    private final JsonStreamParser globalsJsonStreamParser = new GlobalsJsonStreamParser();

    public void save() {
        moduleJsonStreamParser.writeJsonStream();
        globalsJsonStreamParser.writeJsonStream();
        UnderWare.LOGGER.info("Finished saving config.");
    }

    public void load() {
        moduleJsonStreamParser.readJsonStream();
        globalsJsonStreamParser.readJsonStream();
        UnderWare.LOGGER.info("Finished loading config.");
    }

}
