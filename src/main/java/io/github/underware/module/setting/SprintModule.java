package io.github.underware.module.setting;

import io.github.underware.module.Category;
import io.github.underware.module.ModuleBase;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class SprintModule extends ModuleBase {
    public SprintModule() {
        super("Sprint", "Sprint in block game.", Category.MOVEMENT, Keyboard.KEY_O);
    }

    @Override
    public void onEnable() {
        super.onEnable();

        if (!worldCheck()) {
            return;
        }

        mc.player.setSprinting(true);
    }

    @SubscribeEvent
    public void onTickClientTick(TickEvent.ClientTickEvent event) {
        if (!worldCheck()) {
            return;
        }

        mc.player.setSprinting(true);
    }
}
