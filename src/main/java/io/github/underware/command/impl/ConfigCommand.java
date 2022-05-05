package io.github.underware.command.impl;

import io.github.underware.command.CommandBase;
import io.github.underware.config.ConfigManager;
import io.github.underware.util.chat.ChatUtil;
import io.github.underware.util.chat.LoggerType;

public class ConfigCommand extends CommandBase {

    public ConfigCommand() {
        super("Config", "Interact with the client's config", new String[]{}, "<save/load>");
    }

    @Override
    public void execute(String[] args) throws ArrayIndexOutOfBoundsException {
        if (!(args.length == 1)) {
            sendUsageFormatted();
            return;
        }

        if (args[0].equalsIgnoreCase("save")) {
            ConfigManager.INSTANCE.save();
            ChatUtil.sendLogger(LoggerType.SUCCESS, "Config saved.");
        } else if (args[0].equalsIgnoreCase("load")) {
            ConfigManager.INSTANCE.load();
            ChatUtil.sendLogger(LoggerType.SUCCESS, "Config loaded.");
        }
    }

}
