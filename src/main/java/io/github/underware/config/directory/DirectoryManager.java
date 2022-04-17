package io.github.underware.config.directory;

import io.github.underware.UnderWare;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

// TODO: 4/16/22 Different presets
public enum DirectoryManager {

    INSTANCE;

    // Directories
    public final Path minecraftDirectoryPath = Minecraft.getMinecraft().gameDir.toPath().toAbsolutePath();
    public final Path modDirectoryPath = Paths.get(minecraftDirectoryPath.toString(), UnderWare.NAME);
    public final Path moduleDirectoryPath = Paths.get(modDirectoryPath.toString(), "module");
    public final Path globalsDirectoryPath = Paths.get(modDirectoryPath.toString(), "globals");

    // Files
    public final Path moduleFilePath = Paths.get(moduleDirectoryPath.toAbsolutePath().toString(), "modules.json");
    public final Path globalsFilePath = Paths.get(globalsDirectoryPath.toAbsolutePath().toString(), "globals.json");

    public void init() {
        createDirectories(modDirectoryPath, moduleDirectoryPath, globalsDirectoryPath);
        createFiles(moduleFilePath, globalsFilePath);
    }

    private void createDirectories(Path... paths) {
        for (Path path : paths) {
            File file = path.toFile();
            if (!file.exists()) {
                if (!file.mkdirs()) UnderWare.LOGGER.warn("Unable to create directory {}.", file);
            }
        }
    }

    private void createFiles(Path... paths) {
        for (Path path : paths) {
            File file = path.toFile();
            if (!file.exists()) {
                try {
                    if (!file.createNewFile()) UnderWare.LOGGER.warn("Unable to create file {}.", file);
                } catch (IOException e) {
                    UnderWare.LOGGER.error("Error creating file {}.", file);
                    e.printStackTrace();
                }
            }
        }
    }

}
