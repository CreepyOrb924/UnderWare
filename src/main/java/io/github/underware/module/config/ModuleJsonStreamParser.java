package io.github.underware.module.config;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.github.underware.UnderWare;
import io.github.underware.config.directory.DirectoryManager;
import io.github.underware.config.jsonstream.JsonStreamParser;
import io.github.underware.core.Globals;
import io.github.underware.module.ModuleBase;
import io.github.underware.module.ModuleManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ModuleJsonStreamParser implements JsonStreamParser {

    public void readJsonStream() {
        try (FileReader reader = new FileReader(DirectoryManager.INSTANCE.moduleFilePath.toFile())) {
            List<JsonObject> jsonObjects = Globals.INSTANCE.GSON.fromJson(reader, new TypeToken<List<JsonObject>>() {
            }.getType());
            readModuleArray(jsonObjects);
        } catch (Exception e) {
            UnderWare.LOGGER.warn("Unable to load modules.");
            e.printStackTrace();
        }
    }

    @Override
    public void writeJsonStream() {
        try (FileWriter fileWriter = new FileWriter(DirectoryManager.INSTANCE.moduleFilePath.toFile())) {
            Globals.INSTANCE.GSON.toJson(ModuleManager.INSTANCE.modules, new TypeToken<List<ModuleBase>>() {
            }.getType(), fileWriter);
        } catch (IOException e) {
            UnderWare.LOGGER.warn("Unable to save modules.");
            e.printStackTrace();
        }
    }

    private void readModuleArray(List<JsonObject> jsonObjects) {
        for (JsonObject jsonObject : jsonObjects) {
            if (!jsonObject.has("name")) {
                return;
            }
            String name = jsonObject.get("name").getAsString();
            loadModule(jsonObject, name);
        }
    }

    private void loadModule(JsonObject jsonObject, String name) {
        ModuleManager.INSTANCE.modules.stream()
                .filter(module -> module.getName().equalsIgnoreCase(name))
                .findAny()
                .ifPresent(moduleBase -> new ModuleDeserializer(moduleBase).readJsonObject(jsonObject));
    }
}
