package io.github.underware.command.impl;

import io.github.underware.command.CommandBase;
import io.github.underware.module.ModuleBase;
import io.github.underware.module.ModuleManager;
import io.github.underware.util.chat.ChatUtil;
import io.github.underware.util.chat.LoggerType;

public class ToggleCommand extends CommandBase {

    public ToggleCommand() {
        super("Toggle", "Toggles ModulesCommand.", new String[]{"enable", "disable"}, "<module>");
    }

    @Override
    public void execute(String[] args) throws ArrayIndexOutOfBoundsException {
        if (!(args.length == 1)) {
            sendUsage();
            return;
        }

        try {
            ModuleBase module = ModuleManager.INSTANCE.get(args[0]);
            module.toggle();
            if (module.isEnabled()) {
                ChatUtil.sendLogger(LoggerType.SUCCESS, "Enabled module: {}.", module.getName());
            } else {
                ChatUtil.sendLogger(LoggerType.SUCCESS, "Disabled module: {}.", module.getName());
            }
        } catch (RuntimeException ignored) {
            ChatUtil.sendLogger(LoggerType.WARN, "Unable to find module: {}.", args[0]);
        }
    }

}
