package io.github.underware.command.impl;

import io.github.underware.command.CommandBase;
import io.github.underware.module.ModuleManager;
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

        ModuleManager.INSTANCE.getModule(args[0]).setKeyBind(Keyboard.getKeyIndex(args[1]));
    }

}
