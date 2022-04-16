package io.github.underware.module.config;

import com.google.gson.reflect.TypeToken;
import io.github.underware.UnderWare;
import io.github.underware.config.directory.DirectoryManager;
import io.github.underware.config.gson.JsonStreamWriter;
import io.github.underware.module.ModuleBase;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static io.github.underware.core.Globals.GSON;

public class ModuleJsonStreamWriter implements JsonStreamWriter<ModuleBase> {

    public void writeJsonStream(List<ModuleBase> modules) {
        try (FileWriter fileWriter = new FileWriter(DirectoryManager.INSTANCE.moduleFilePath.toFile())) {
            GSON.toJson(modules, new TypeToken<List<ModuleBase>>() {
            }.getType(), fileWriter);
        } catch (IOException e) {
            UnderWare.LOGGER.warn("Unable to save modules.");
            e.printStackTrace();
        }
    }

}
