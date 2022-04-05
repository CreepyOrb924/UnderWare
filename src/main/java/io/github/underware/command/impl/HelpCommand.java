package io.github.underware.command.impl;

import io.github.underware.command.CommandBase;
import io.github.underware.command.CommandManager;
import io.github.underware.util.chat.ChatUtil;

public class HelpCommand extends CommandBase {

    public HelpCommand() {
        super("Help", "Sends a list of all commands.", new String[]{"Commands", "Man"}, "");
    }

    @Override
    public void execute(String[] args) throws ArrayIndexOutOfBoundsException {
        StringBuilder stringBuilder = new StringBuilder();
        for (CommandBase command : CommandManager.INSTANCE.commands) {
            stringBuilder.append(command.getName())
                    .append(' ')
                    .append(command.getUsage())
                    .append('\n');
        }
        ChatUtil.sendClientMessage(stringBuilder.toString());
    }

}
