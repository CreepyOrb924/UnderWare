package io.github.underware.command.impl;

import io.github.underware.command.CommandBase;

public class GithubCommand extends CommandBase {

    public GithubCommand() {
        super("Github", "Prints the client's GitHub URL in chat.", new String[]{"git", "url"}, "");
    }

    @Override
    public void execute(String[] args) throws ArrayIndexOutOfBoundsException {
        mc.player.sendChatMessage("https://github.com/CreepyOrb924/UnderWare");
    }

}
