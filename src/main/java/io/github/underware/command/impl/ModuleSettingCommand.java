package io.github.underware.command.impl;

import io.github.underware.command.CommandBase;
import io.github.underware.module.ModuleBase;
import io.github.underware.module.ModuleManager;
import io.github.underware.module.setting.SettingBase;
import io.github.underware.module.setting.SettingValueParser;
import io.github.underware.util.chat.ChatUtil;
import io.github.underware.util.chat.LoggerType;

public class ModuleSettingCommand extends CommandBase {

    public ModuleSettingCommand() {
        super("ModuleSetting", "Change a Module's setting value.", new String[]{"ModuleSettings", "Setting", "Set"}, "<module> <setting> <value>");
    }

    @Override
    public void execute(String[] args) throws ArrayIndexOutOfBoundsException {
        if (args.length != 3) {
            sendUsageFormatted();
            return;
        }

        try {
            ModuleBase module = ModuleManager.INSTANCE.get(args[0]);
            SettingBase<?> setting = ModuleManager.INSTANCE.getSetting(module, args[1]);
            new SettingValueParser(setting).parseValue(args[2]);
            ChatUtil.sendLogger(LoggerType.SUCCESS, "Set setting: {} to: {}.", setting, args[2]);
        } catch (IllegalArgumentException e) {
            sendUsageFormatted();
        }
    }

}
