package io.github.underware.mixin.client;

import io.github.underware.config.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "crashed", at = @At(value = "HEAD"))
    public void crashHook(CrashReport crash, CallbackInfo ci) {
        ConfigManager.INSTANCE.save();
    }

    @Inject(method = "shutdown", at = @At(value = "HEAD"))
    public void shutdownHook(CallbackInfo info) {
        ConfigManager.INSTANCE.save();
    }

}
