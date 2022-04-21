package io.github.underware.command.impl;

import io.github.underware.command.CommandBase;

public class DiscordCommand extends CommandBase {

    public DiscordCommand() {
        super("Discord", "Sends the Discord server's URL in chat.", new String[]{}, "");
    }

    @Override
    public void execute(String[] args) throws ArrayIndexOutOfBoundsException {
        mc.player.sendChatMessage("https://discord.gg/pScsnFEyAE");
    }

}
