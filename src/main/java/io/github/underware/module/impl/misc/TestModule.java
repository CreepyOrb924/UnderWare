package io.github.underware.module.impl.misc;

import io.github.underware.UnderWare;
import io.github.underware.event.PlayerSendChatMessageEvent;
import io.github.underware.module.Category;
import io.github.underware.module.ModuleBase;
import io.github.underware.module.setting.impl.BooleanSetting;
import io.github.underware.module.setting.impl.StringSetting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class TestModule extends ModuleBase {

    // TODO: 3/28/22 This setting isn't being saved to the config.
    //  I am assuming that the reflection to access this field isn't working correctly. Test it.
    private final BooleanSetting booleanSetting = new BooleanSetting("Boolean", "Boolean setting.", true);
    public final StringSetting stringSetting = new StringSetting("String", "String setting.", "FooBar");

    public TestModule() {
        super("Test", "Test module", Category.MISC, Keyboard.KEY_P);
    }

    @Override
    public void onEnable() {
        super.onEnable();

        UnderWare.LOGGER.info("Enabled: {}, with setting: {}.", this, booleanSetting);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        UnderWare.LOGGER.info("Disabled {}.", getName());
    }

    @SubscribeEvent
    public void onPlayerSendChatMessage(PlayerSendChatMessageEvent event) {
        if (event.getMessage().startsWith(",")) {
            event.setMessage("Among us.");
        }
    }

}
