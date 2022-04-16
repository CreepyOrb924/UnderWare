package io.github.underware.module.config;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.github.underware.UnderWare;
import io.github.underware.config.directory.DirectoryManager;
import io.github.underware.config.gson.JsonStreamReader;
import io.github.underware.module.ModuleManager;

import java.io.FileReader;
import java.util.List;

import static io.github.underware.core.Globals.GSON;

public class ModuleJsonStreamReader implements JsonStreamReader {

    public void readJsonStream() {
        try (FileReader reader = new FileReader(DirectoryManager.INSTANCE.moduleFilePath.toFile())) {
            List<JsonObject> jsonObjects = GSON.fromJson(reader, new TypeToken<List<JsonObject>>() {
            }.getType());
            readModuleArray(jsonObjects);
        } catch (Exception e) {
            UnderWare.LOGGER.warn("Unable to load modules.");
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
