package io.github.underware.core;

import io.github.underware.command.CommandManager;
import io.github.underware.config.ConfigManager;
import io.github.underware.config.directory.DirectoryManager;
import io.github.underware.event.ForgeEventHandler;
import io.github.underware.module.ModuleManager;

public enum SingletonInitializer {

    INSTANCE;

    /**
     * Order is very important for this method.
     * We need the directories to load first, everything else second, and the config last.
     */
    public void init() {
        // Load the directory first.
        DirectoryManager.INSTANCE.init();

        // Load everything else.
        CommandManager.INSTANCE.init();
        ModuleManager.INSTANCE.init();
        ForgeEventHandler.INSTANCE.init();

        // Load the config second.
        ConfigManager.INSTANCE.load();
    }

}
