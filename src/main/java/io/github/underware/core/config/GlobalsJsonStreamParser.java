package io.github.underware.core.config;

import io.github.underware.UnderWare;
import io.github.underware.config.directory.DirectoryManager;
import io.github.underware.config.jsonstream.JsonStreamParser;
import io.github.underware.core.Globals;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GlobalsJsonStreamParser implements JsonStreamParser {

    @Override
    public void readJsonStream() {
        try (FileReader reader = new FileReader(DirectoryManager.INSTANCE.globalsFilePath.toFile())) {
            Globals.INSTANCE = Globals.INSTANCE.GSON.fromJson(reader, Globals.class);
        } catch (Exception e) {
            UnderWare.LOGGER.warn("Unable to load globals.");
            e.printStackTrace();
        }
    }

    @Override
    public void writeJsonStream() {
        try (FileWriter fileWriter = new FileWriter(DirectoryManager.INSTANCE.globalsFilePath.toFile())) {
            Globals.INSTANCE.GSON.toJson(Globals.INSTANCE, fileWriter);
        } catch (IOException e) {
            UnderWare.LOGGER.warn("Unable to save globals.");
            e.printStackTrace();
        }
    }

}
