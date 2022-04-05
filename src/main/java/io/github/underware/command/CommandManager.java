package io.github.underware.command;

import io.github.underware.UnderWare;
import io.github.underware.util.reflection.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CommandManager {

    INSTANCE;

    public final List<CommandBase> commands = new ArrayList<>();

    public void init() {
        loadCommands();
        CommandChatListener.INSTANCE.init();
    }

    private void loadCommands() {
        try {
            ArrayList<Class<?>> commandClasses = ReflectionUtil.getClassesForPackage("io.github.underware.command.impl");
            commandClasses.spliterator().forEachRemaining(aClass -> {
                if (!CommandBase.class.isAssignableFrom(aClass)) {
                    return;
                }
                try {
                    CommandBase command = (CommandBase) aClass.getConstructor().newInstance();
                    commands.add(command);
                    UnderWare.LOGGER.info("Loaded Command: {}.", command);
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                    UnderWare.LOGGER.error("Unable to create command class. [{}]", e.toString());
                    e.printStackTrace();
                }
            });
        } catch (ClassNotFoundException e) {
            UnderWare.LOGGER.error(e);
            e.printStackTrace();
        }
    }

    private CommandBase getCommand(Class<? extends CommandBase> aClass) {
        return commands.stream()
                .filter(module -> module.getClass() == aClass)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unable to find Command: " + aClass + "."));
    }

    private CommandBase getCommand(String commandName) {
        return commands.stream()
                .filter(command -> command.getName().equalsIgnoreCase(commandName))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unable to find Command: " + commandName + "."));
    }

    public void executeArgs(String[] args) {
        commands.stream()
                .filter(command -> command.getName().equalsIgnoreCase(args[0])
                        || commandAliasMatchString(command, args[0]))
                .findAny()
                .ifPresent(commandBase -> commandBase.execute(args));

    }

    private boolean commandAliasMatchString(CommandBase command, String string) {
        return Arrays.stream(command.getAlias()).anyMatch(alias -> alias.equalsIgnoreCase(string));
    }

}
