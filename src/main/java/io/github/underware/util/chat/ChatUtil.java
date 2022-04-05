package io.github.underware.util.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class ChatUtil {

    public static final ChatFormatting CLIENT_COLOR = ChatFormatting.DARK_PURPLE;

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void sendMessage(String message) {
        if (mc.world != null && mc.player != null) {
            mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(message));
        }
    }

    public static void sendClientMessage(String message) {
        if (mc.world != null && mc.player != null) {
            mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(CLIENT_COLOR + "[Cybernet] " + ChatFormatting.RESET + message));
        }
    }

    /**
     * <p>
     * Used for debugging when you would rather look at Minecraft's chat instead of checking console.
     * </p>
     *
     * @param message The message that will be printed.
     * @param loggerType The {@link LoggerType} that the debug message will be.
     */
    public static void sendLogger(String message, LoggerType loggerType) {
        if (mc.world != null && mc.player != null) {
            ChatFormatting chatFormatting;
            switch (loggerType) {
                case DEBUG:
                    message = "[DEBUG] " + message;
                    chatFormatting = ChatFormatting.DARK_GRAY;
                    break;
                case ERROR:
                    message = "[ERROR] " + message;
                    chatFormatting = ChatFormatting.RED;
                    break;
                case FATAL:
                    message = "[FATAL] " + message;
                    chatFormatting = ChatFormatting.DARK_RED;
                    break;
                case INFO:
                    message = "[INFO] " + message;
                    chatFormatting = ChatFormatting.GRAY;
                    break;
                case SUCCESS:
                    message = "[SUCCESS] " + message;
                    chatFormatting = ChatFormatting.GREEN;
                    break;
                case WARNING:
                    message = "[WARNING] " + message;
                    chatFormatting = ChatFormatting.YELLOW;
                    break;
                default:
                    return;
            }
            sendMessage(chatFormatting + message);
        }
    }
}
