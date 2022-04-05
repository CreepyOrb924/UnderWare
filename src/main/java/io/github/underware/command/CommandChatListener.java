package io.github.underware.command;

import io.github.underware.event.PlayerSendChatMessageEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public enum CommandChatListener {

    INSTANCE;

    public void init() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerSendChatMessage(PlayerSendChatMessageEvent event) {
        String message = event.getMessage().toLowerCase();
        if (message.startsWith(String.valueOf(CommandPrefix.prefix))) {
            CommandManager.INSTANCE.executeArgs(dropFirstString(message.split(" ")));
            event.setCanceled(true);
        }
    }

    private String[] dropFirstString(String[] input) {
        String[] strings = new String[input.length - 1];
        System.arraycopy(input, 1, strings, 0, input.length - 1);
        return strings;
    }
}
