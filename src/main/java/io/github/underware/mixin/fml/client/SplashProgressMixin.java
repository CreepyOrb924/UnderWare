package io.github.underware.mixin.fml.client;

import net.minecraftforge.fml.client.SplashProgress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// TODO: 5/16/22 The Minecraft logo isn't rendering.
//  The Mojang logo has a white background so it either needs replaced with a transparent background
//  or the texture needs changed to a different texture. I wasn't able to figure out how to change
//  the texture with Mixins but I am certain that it is possible.

/**
 * Makes the launcher background color black to prevent being flashbanged when launching.
 */
@Mixin(value = SplashProgress.class)
public abstract class SplashProgressMixin {

    @Shadow
    private static int backgroundColor;

    @Shadow
    private static int fontColor;

    @Inject(
            method = "start",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraftforge/fml/client/SplashProgress;getHex(Ljava/lang/String;I)I", ordinal = 1),
            remap = false
    )
    private static void changeBackgroundColor(CallbackInfo ci) {
        backgroundColor = 0x000000;
        fontColor = 0xFFFFFF;
    }

}
