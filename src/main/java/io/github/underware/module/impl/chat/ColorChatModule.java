package io.github.underware.module.impl.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import io.github.underware.core.Globals;
import io.github.underware.module.Category;
import io.github.underware.module.ModuleBase;
import io.github.underware.module.setting.impl.EnumSetting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ColorChatModule extends ModuleBase {

    private final EnumSetting<Modes> mode = new EnumSetting<>("Mode", "The type of color-chat to use.", Modes.CLASSIC);
    private final EnumSetting<ChatFormatting> colorCode = new EnumSetting<>("ColorCode", "The type of color-code to use.", ChatFormatting.BLACK);

    public ColorChatModule() {
        super("ColorChat", "Changes your chat color if servers allow it.", Category.CHAT);
    }

    @SubscribeEvent
    public void onClientChat(ClientChatEvent event) {
        if (startWithPrefix(event.getMessage())) {
            return;
        }

        switch (mode.getValue()) {
            case CLASSIC:
                // Classic 2B2T green-text prefix.
                event.setMessage("> " + event.getMessage());
                break;
            case COLORCODE:
                // Only works on servers that allow color-codes through the & sign.
                event.setMessage("&" + colorCode.getValue().getChar() + event.getMessage());
                break;
            default:
                break;
        }
    }

    private boolean startWithPrefix(String message) {
        return message.startsWith("/")
                || message.startsWith(String.valueOf(Globals.INSTANCE.prefix));
    }

    private enum Modes {
        CLASSIC,
        COLORCODE
    }

}
