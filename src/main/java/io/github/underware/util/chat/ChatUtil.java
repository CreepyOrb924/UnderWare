package io.github.underware.util.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import io.github.underware.UnderWare;
import io.github.underware.core.Globals;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

public class ChatUtil {

    public static final ChatFormatting CLIENT_COLOR = ChatFormatting.BOLD;

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void sendMessage(String message) {
        if (mc.world != null && mc.player != null) {
            mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(message));
        }
    }

    public static void sendClientMessage(String message) {
        if (mc.world != null && mc.player != null) {
            mc.ingameGUI.getChatGUI().printChatMessage(
                    new TextComponentString(CLIENT_COLOR + "[" + UnderWare.NAME + "] " + ChatFormatting.RESET + message));
        }
    }

    /**
     * <p>
     * Used for debugging when you would rather look at Minecraft's chat instead of checking console.
     * </p>
     *
     * @param loggerType The {@link LoggerType} that the debug message will be.
     * @param message    The message that will be printed.
     */
    public static void sendLogger(LoggerType loggerType, String message) {
        if (mc.world == null || mc.player == null || !Globals.INSTANCE.debug) {
            return;
        }

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
            case WARN:
                message = "[WARN] " + message;
                chatFormatting = ChatFormatting.YELLOW;
                break;
            default:
                return;
        }
        sendMessage(chatFormatting + message);
    }

    /**
     * <p>
     * Used for debugging as described in {@link #sendLogger(LoggerType, String)}
     * with the added functionality of being able to pass objects in Strings using "{}"
     * just like it is done in {@link org.apache.logging.log4j}
     * </p>
     *
     * @param loggerType The {@link LoggerType} that the debug message will be.
     * @param message    The message that will be printed.
     * @param params     The objects that will be passed inside "{}" of your message.
     */
    public static void sendLogger(LoggerType loggerType, String message, Object... params) {
        int matches = StringUtils.countMatches(message, "{}");
        for (int i = 0; i < matches; i++) {
            message = message.replaceFirst("(\\{})", "{" + i + "}");
        }
        sendLogger(loggerType, new MessageFormat(message).format(params));
    }

}
