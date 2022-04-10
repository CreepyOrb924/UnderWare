package io.github.underware.command.impl;

import io.github.underware.command.CommandBase;
import io.github.underware.module.ModuleBase;
import io.github.underware.module.ModuleManager;
import io.github.underware.module.setting.SettingBase;
import io.github.underware.module.setting.SettingValueParser;

public class ModuleSettingCommand extends CommandBase {

    public ModuleSettingCommand() {
        super("ModuleSetting", "Change a Module's setting value.", new String[]{"ModuleSettings", "Setting", "Set"}, "<module> <setting> <value>");
    }

    @Override
    public void execute(String[] args) throws ArrayIndexOutOfBoundsException {
        if (args.length != 3) {
            sendUsageFormatted();
        }

        ModuleBase module = ModuleManager.INSTANCE.getModule(args[0]);
        SettingBase<?> setting = ModuleManager.INSTANCE.getSettingByName(module, args[1]);
        new SettingValueParser(setting).parseStringValue(args[2]);
    }

}
