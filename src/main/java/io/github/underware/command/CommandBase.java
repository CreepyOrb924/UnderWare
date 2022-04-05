package io.github.underware.command;

import net.minecraft.client.Minecraft;

import java.util.Arrays;

public abstract class CommandBase {

    private final String name, description, usage;
    private final String[] alias;

    protected final Minecraft mc = Minecraft.getMinecraft();

    public CommandBase(String name, String description, String[] alias, String usage) {
        this.name = name;
        this.description = description;
        this.alias = alias;
        this.usage = usage;
    }

    public abstract void execute(String[] args) throws ArrayIndexOutOfBoundsException;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        String finalUsage = CommandPrefix.prefix + name;
        if (usage != null) finalUsage += ' ' + usage;
        return finalUsage;
    }

    public String[] getAlias() {
        return alias;
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
