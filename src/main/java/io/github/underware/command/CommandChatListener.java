package io.github.underware.command;

import io.github.underware.core.Globals;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public enum CommandChatListener {

    INSTANCE;

    public void init() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientChat(ClientChatEvent event) {
        String message = event.getMessage().toLowerCase();
        if (message.startsWith(String.valueOf(Globals.INSTANCE.prefix))) {
            CommandManager.INSTANCE.executeArgs(message.substring(1).split(" "));
            event.setCanceled(true);
        }
    }

}
