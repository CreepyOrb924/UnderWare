package io.github.underware;

import io.github.underware.core.SingletonInitializer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid = UnderWare.MOD_ID, name = UnderWare.NAME, version = UnderWare.VERSION)
public final class UnderWare {

    public static final String MOD_ID = "underware";
    public static final String NAME = "UnderWare";
    public static final String VERSION = "1.0-SNAPSHOT";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        Display.setTitle(UnderWare.NAME + " " + UnderWare.VERSION);

        SingletonInitializer.INSTANCE.init();
    }

}
