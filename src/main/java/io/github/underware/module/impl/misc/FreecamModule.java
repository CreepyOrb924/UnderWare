package io.github.underware.module.impl.misc;

import io.github.underware.event.PacketEvent;
import io.github.underware.module.Category;
import io.github.underware.module.ModuleBase;
import io.github.underware.module.setting.impl.EnumSetting;
import io.github.underware.module.setting.impl.NumberSetting;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FreecamModule extends ModuleBase {

    private final NumberSetting<Double> speed = new NumberSetting<>("Speed", "How fast you fly.", 0.7, 0.1, 3.0, 0.1);
    private final EnumSetting<Modes> mode = new EnumSetting<>("Mode", "The type of freecam mode.", Modes.NORMAL);

    // 0.01745329252 is pi / 180 or almost sin(1 degree)
    private final double piOver180 = 0.01745329252;
    private final int entityID = -69420;

    private EntityOtherPlayerMP clonedPlayer;
    private Vec3d location;

    public FreecamModule() {
        super("Freecam", "Allow your player to freely fly around the world.", Category.MISC);
    }

    private void handleMovement() {
        double motionX = 0, motionY = 0, motionZ = 0;

        if (mc.gameSettings.keyBindJump.isKeyDown()) {
            if (mode.getValue() == Modes.NORMAL) {
                motionY += 1;
            }
        }
        if (mc.gameSettings.keyBindSneak.isKeyDown()) {
            if (mode.getValue() == Modes.NORMAL) {
                motionY -= 1;
            }
        }

        if (mc.gameSettings.keyBindForward.isKeyDown()) {
            motionX += Math.cos((90 + mc.player.rotationYaw) * piOver180);
            motionZ += Math.sin((90 + mc.player.rotationYaw) * piOver180);

            if (mode.getValue() == Modes.CSGO) {
                motionY += Math.cos((90 + mc.player.rotationPitch) * piOver180);
            }
        }
        if (mc.gameSettings.keyBindBack.isKeyDown()) {
            motionX -= Math.cos((90 + mc.player.rotationYaw) * piOver180);
            motionZ -= Math.sin((90 + mc.player.rotationYaw) * piOver180);

            if (mode.getValue() == Modes.CSGO) {
                motionY -= Math.cos((90 + mc.player.rotationPitch) * piOver180);
            }
        }

        if (mc.gameSettings.keyBindLeft.isKeyDown()) {
            motionX += Math.cos((mc.player.rotationYaw) * piOver180);
            motionZ += Math.sin((mc.player.rotationYaw) * piOver180);
        }
        if (mc.gameSettings.keyBindRight.isKeyDown()) {
            motionX -= Math.cos((mc.player.rotationYaw) * piOver180);
            motionZ -= Math.sin((mc.player.rotationYaw) * piOver180);
        }

        mc.player.motionX = motionX * speed.getValue();
        mc.player.motionY = motionY * speed.getValue();
        mc.player.motionZ = motionZ * speed.getValue();
    }

    @Override
    public void onEnable() {
        super.onEnable();

        if (worldIsNull()) {
            onDisable();
            return;
        }

        clonedPlayer = new EntityOtherPlayerMP(mc.world, mc.player.getGameProfile());
        clonedPlayer.copyLocationAndAnglesFrom(mc.player);
        clonedPlayer.inventory.copyInventory(mc.player.inventory);
        clonedPlayer.rotationYaw = mc.player.rotationYaw;
        clonedPlayer.rotationYawHead = mc.player.rotationYawHead;
        mc.world.addEntityToWorld(entityID, clonedPlayer);

        mc.renderGlobal.loadRenderers();
        mc.renderChunksMany = false;
        location = mc.player.getPositionVector();
    }

    @Override
    public void onDisable() {
        super.onDisable();

        if (worldIsNull()) {
            return;
        }

        mc.world.removeEntityFromWorld(entityID);

        mc.player.noClip = false;
        mc.renderChunksMany = true;
        if (location != null) {
            mc.player.setPosition(location.x, location.y, location.z);
        }
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        event.setCanceled(event.getPacket() instanceof CPacketPlayer);
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (worldIsNull()) {
            return;
        }

        mc.player.noClip = true;

        mc.player.setVelocity(0, 0, 0);

        if (clonedPlayer != null) {
            clonedPlayer.inventory = mc.player.inventory;
        }

        handleMovement();
    }

    private enum Modes {
        NORMAL,
        CSGO
    }

}
