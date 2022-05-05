package io.github.underware.command.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import io.github.underware.command.CommandBase;
import io.github.underware.command.CommandManager;
import io.github.underware.util.StringUtil;
import io.github.underware.util.chat.ChatUtil;

import java.util.List;

public class HelpCommand extends CommandBase {

    public HelpCommand() {
        super("Help", "Get help for a specific command.", new String[]{"Commands", "Man"}, "<command>");
    }

    @Override
    public void execute(String[] args) throws ArrayIndexOutOfBoundsException {
        if (args.length == 1) {
            CommandManager.INSTANCE.get(args[0]).sendUsageFormatted();
        } else {
            StringBuilder stringBuilder = new StringBuilder(StringUtil.buildString("\n",
                    ChatFormatting.RED + "Command",
                    ChatFormatting.RESET + " \u23d0 ",
                    ChatFormatting.GREEN + "Description",
                    ChatFormatting.RESET + " \u23d0 ",
                    ChatFormatting.BLUE + "Usage\n"));

            List<CommandBase> commandsAlphabetically = CommandManager.INSTANCE.getObjectsAlphabetically();
            for (int i = 0; i < commandsAlphabetically.size(); i++) {
                stringBuilder.append(commandsAlphabetically.get(i).getUsageFormatted());
                if (i != commandsAlphabetically.size() - 1) {
                    stringBuilder.append('\n');
                }
            }
            ChatUtil.sendClientMessage(stringBuilder.toString());
        }
    }

}
