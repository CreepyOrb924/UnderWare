package io.github.underware.config;

import io.github.underware.UnderWare;
import io.github.underware.config.gson.ModuleJsonStreamReader;
import io.github.underware.config.gson.ModuleJsonStreamWriter;
import io.github.underware.module.ModuleManager;

public enum ConfigManager {

    INSTANCE;

    private final ModuleJsonStreamWriter moduleSerializer = new ModuleJsonStreamWriter();
    private final ModuleJsonStreamReader moduleDeserializer = new ModuleJsonStreamReader();

    public void save() {
        moduleSerializer.writeJsonStream(ModuleManager.INSTANCE.modules);
        UnderWare.LOGGER.info("Finished saving config.");
    }

    public void load() {
        moduleDeserializer.readJsonStream();
        UnderWare.LOGGER.info("Finished loading config.");
    }

}
