package io.github.underware.command.impl;

import io.github.underware.command.CommandBase;
import io.github.underware.config.ConfigManager;

public class ConfigCommand extends CommandBase {

    public ConfigCommand() {
        super("Config", "Interact with the client's config", new String[]{}, "<save/load>");
    }

    @Override
    public void execute(String[] args) throws ArrayIndexOutOfBoundsException {
        if (!(args.length == 1)) {
            sendUsageFormatted();
        }

        if (args[0].equalsIgnoreCase("save")) {
            ConfigManager.INSTANCE.save();
        } else if (args[0].equalsIgnoreCase("load")) {
            ConfigManager.INSTANCE.load();
        } else {
            sendUsageFormatted();
        }
    }

}
