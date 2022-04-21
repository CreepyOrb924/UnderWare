package io.github.underware.event;

import io.github.underware.core.Globals;
import io.github.underware.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public enum ForgeEventHandler {

    INSTANCE;

    private final Minecraft mc = Minecraft.getMinecraft();

    public void init() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onInputKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            // Is fired when hitting the super key on Linux.
            if (Keyboard.getEventKey() == Keyboard.KEY_NONE) {
                return;
            }

            if (Keyboard.getEventCharacter() == Globals.INSTANCE.prefix && mc.currentScreen == null) {
                mc.displayGuiScreen(new GuiChat(String.valueOf(Globals.INSTANCE.prefix)));
            }

            ModuleManager.INSTANCE.onKeyPress(Keyboard.getEventKey());
        }
    }

}
