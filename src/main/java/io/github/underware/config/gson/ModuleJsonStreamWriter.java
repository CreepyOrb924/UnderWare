package io.github.underware.config.gson;

import com.google.gson.reflect.TypeToken;
import io.github.underware.UnderWare;
import io.github.underware.config.directory.DirectoryManager;
import io.github.underware.module.ModuleBase;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static io.github.underware.config.gson.GsonInstance.GSON;

public final class ModuleJsonStreamWriter {

    public void writeJsonStream(List<ModuleBase> modules) {
        try (FileWriter fileWriter = new FileWriter(DirectoryManager.INSTANCE.moduleFilePath.toFile())) {
            GSON.toJson(modules, new TypeToken<List<ModuleBase>>(){}.getType(), fileWriter);
        } catch (IOException e) {
            UnderWare.LOGGER.warn("Unable to save config file.");
            e.printStackTrace();
        }
    }

}
