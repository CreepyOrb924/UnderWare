package io.github.underware.module.impl.chat;

import io.github.underware.module.Category;
import io.github.underware.module.ModuleBase;
import io.github.underware.module.setting.impl.EnumSetting;
import io.github.underware.module.setting.impl.StringSetting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SuffixModule extends ModuleBase {

    private final StringSetting suffix = new StringSetting("Suffix", "The suffix that will be shown in chat.", "UnderWare");

    private final EnumSetting<Separators> separator = new EnumSetting<>(
            "Separator",
            "The separator between the message and the suffix.",
            Separators.LIGHTNING);

    public SuffixModule() {
        super("Suffix", "Appends a suffix to your chat messages.", Category.CHAT);
    }

    @SubscribeEvent
    public void onClientChat(ClientChatEvent event) {
        event.setMessage(event.getMessage() + " " + separator.getValue().getSeparator() + " " + getWideText(suffix.getValue()));
    }

    private String getWideText(String message) {
        StringBuilder sb = new StringBuilder();

        for (char c : message.toCharArray()) {
            sb.append((char) (0xff01 + c - 33));
        }
        return sb.toString();
    }

    private enum Separators {
        // »
        ARROW('\u00bb'),
        // ⚡
        LIGHTNING('\u26a1'),
        // ⏐
        LINE('\u23d0');

        private final char separator;

        Separators(char separator) {
            this.separator = separator;
        }

        public char getSeparator() {
            return separator;
        }
    }

}
