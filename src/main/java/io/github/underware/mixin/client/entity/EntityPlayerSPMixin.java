package io.github.underware.mixin.client.entity;

import io.github.underware.UnderWare;
import io.github.underware.event.PlayerSendChatMessageEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class EntityPlayerSPMixin {

    private PlayerSendChatMessageEvent chatMessageEvent;

    @Inject(method = "sendChatMessage(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    public void onPlayerSendChatMessage(String message, CallbackInfo ci) {
        chatMessageEvent = new PlayerSendChatMessageEvent(message);

        MinecraftForge.EVENT_BUS.post(chatMessageEvent);
        if (chatMessageEvent.isCanceled()) {
            ci.cancel();
        }
    }

    /**
     * <p>
     * A way to directly change the player's chat message using {@link PlayerSendChatMessageEvent#setMessage(String)}
     * rather than canceling the event and sending another chat message explicitly.
     * </p>
     *
     * @param message The message originally sent by the play.
     * @return The message you want the player to send.
     */
    @ModifyArg(method = "sendChatMessage(Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/play/client/CPacketChatMessage;<init>(Ljava/lang/String;)V"))
    public String sendMessageArgHook(String message) {
        if (chatMessageEvent != null) {
            return chatMessageEvent.getMessage();
        } else {
            UnderWare.LOGGER.error("ChatMessageEvent null. Sending: {}.", message);
            return message;
        }
    }

}
