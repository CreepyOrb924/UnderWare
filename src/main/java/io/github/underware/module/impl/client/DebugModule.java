package io.github.underware.module.impl.client;

import io.github.underware.core.Globals;
import io.github.underware.module.Category;
import io.github.underware.module.ModuleBase;

public class DebugModule extends ModuleBase {

    public DebugModule() {
        super("Debug", "Enable debug messages in chat from the client.", Category.CLIENT);
    }

    @Override
    public void onEnable() {
        super.onEnable();

        Globals.INSTANCE.debug = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();

        Globals.INSTANCE.debug = false;
    }
}
