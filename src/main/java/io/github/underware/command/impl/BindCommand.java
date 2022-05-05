package io.github.underware.command.impl;

import io.github.underware.command.CommandBase;
import io.github.underware.module.ModuleBase;
import io.github.underware.module.ModuleManager;
import io.github.underware.util.chat.ChatUtil;
import io.github.underware.util.chat.LoggerType;
import org.lwjgl.input.Keyboard;

public class BindCommand extends CommandBase {

    public BindCommand() {
        super("Bind", "Bind a module to a different key.", new String[]{}, "<module> <key>");
    }

    @Override
    public void execute(String[] args) throws ArrayIndexOutOfBoundsException {
        if (!(args.length == 2)) {
            sendUsage();
        }

        ModuleBase module = ModuleManager.INSTANCE.get(args[0]);
        String keyName = args[1].toUpperCase();
        module.setKeyBind(Keyboard.getKeyIndex(keyName));

        if (Keyboard.getKeyName(module.getKeyBind()).equals(keyName)) {
            ChatUtil.sendLogger(LoggerType.SUCCESS, "Changed key-bind of: {} to: {}.", module, keyName);
        }
    }

}
