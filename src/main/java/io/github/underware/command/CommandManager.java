package io.github.underware.command;

import io.github.underware.UnderWare;
import io.github.underware.core.interfaces.ManagerHandler;
import io.github.underware.util.reflection.ReflectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CommandManager implements ManagerHandler<CommandBase> {

    INSTANCE;

    public final List<CommandBase> commands = new ArrayList<>();

    public void init() {
        loadObjects();
        new CommandChatListener();
    }

    @Override
    public void loadObjects() {
        try {
            ReflectionUtil.getAddClassesFromPackageToList("io.github.underware.command.impl", commands, CommandBase.class);
            commands.forEach(command -> UnderWare.LOGGER.info("Loaded Command: {}.", command));
        } catch (ClassNotFoundException e) {
            UnderWare.LOGGER.error(e);
            e.printStackTrace();
        }
    }

    @Override
    public List<CommandBase> getObjects() {
        return commands;
    }

    @Override
    public List<CommandBase> getObjectsAlphabetically() {
        return commands.stream()
                .sorted((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public CommandBase get(String name) {
        return commands.stream()
                .filter(command -> command.getName().equalsIgnoreCase(name) || commandAliasMatchString(command, name))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unable to find " + name + "."));
    }

    public void executeArgs(String[] args) {
        commands.stream()
                .filter(command -> command.getName().equalsIgnoreCase(args[0])
                        || commandAliasMatchString(command, args[0]))
                .findAny()
                .ifPresent(commandBase -> {
                            try {
                                commandBase.execute(dropFirstString(args));
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                            }
                        }
                );
    }

    private boolean commandAliasMatchString(CommandBase command, String string) {
        return Arrays.stream(command.getAlias()).anyMatch(alias -> alias.equalsIgnoreCase(string));
    }

    private String[] dropFirstString(String[] input) {
        String[] strings = new String[input.length - 1];
        System.arraycopy(input, 1, strings, 0, input.length - 1);
        return strings;
    }

}
