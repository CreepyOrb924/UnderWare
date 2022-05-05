package io.github.underware.command;

import com.mojang.realmsclient.gui.ChatFormatting;
import io.github.underware.core.Globals;
import io.github.underware.util.StringUtil;
import io.github.underware.util.chat.ChatUtil;
import net.minecraft.client.Minecraft;

import java.util.Arrays;

// TODO: 4/6/22 Tab complete commands.
// TODO: 4/6/22 Change chat color when you start with prefix. (AKA GUI Elements for commands.)
public abstract class CommandBase {

    protected final Minecraft mc = Minecraft.getMinecraft();
    private final String name, description, usage;
    private final String[] alias;

    public CommandBase(String name, String description, String[] alias, String usage) {
        this.name = name;
        this.description = description;
        this.alias = alias;
        this.usage = usage;
    }

    /**
     * @param args The message arguments not including the command name.
     * @throws ArrayIndexOutOfBoundsException When trying to find an argument that isn't there.
     */
    public abstract void execute(String[] args) throws ArrayIndexOutOfBoundsException;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        String finalUsage = Globals.INSTANCE.prefix + name;
        if (usage != null) finalUsage += ' ' + usage;
        return finalUsage;
    }

    public String[] getAlias() {
        return alias;
    }

    public void sendUsage() {
        ChatUtil.sendClientMessage(getUsage());
    }

    public void sendUsageFormatted() {
        ChatUtil.sendClientMessage(getUsageFormatted());
    }

    public String getUsageFormatted() {
        return StringUtil.buildString(ChatFormatting.RED.toString(),
                getName(),
                ChatFormatting.RESET.toString(),
                " \u23d0 ",
                ChatFormatting.GREEN.toString(),
                getDescription(),
                ChatFormatting.RESET.toString(),
                " \u23d0 ",
                ChatFormatting.BLUE.toString(),
                getUsage());
    }

    @Override
    public String toString() {
        return "CommandBase{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", usage='" + usage + '\'' +
                ", alias=" + Arrays.toString(alias) +
                '}';
    }

}
