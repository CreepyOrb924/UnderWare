package io.github.underware.module.impl.client;

import io.github.underware.core.Globals;
import io.github.underware.module.Category;
import io.github.underware.module.ModuleBase;
import io.github.underware.util.chat.ChatUtil;
import io.github.underware.util.chat.LoggerType;

public class DebugModule extends ModuleBase {

    public DebugModule() {
        super("Debug", "Enable debug messages in chat from the client.", Category.CLIENT);
    }

    @Override
    public void onEnable() {
        super.onEnable();

        ChatUtil.sendLogger(LoggerType.SUCCESS, "Enabled debug mode.");
        Globals.INSTANCE.debug = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();

        ChatUtil.sendLogger(LoggerType.SUCCESS, "Disabled debug mode.");
        Globals.INSTANCE.debug = false;
    }

}
