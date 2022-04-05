package io.github.underware.event;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class PacketEvent extends Event {

    private final Packet<?> packet;

    public PacketEvent(Packet<?> packet) {
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return packet;
    }

    public final static class Send extends PacketEvent {

        public Send(Packet<?> packet) {
            super(packet);
        }

    }

    public final static class Receive extends PacketEvent {

        private final ChannelHandlerContext context;

        public Receive(Packet<?> packet, ChannelHandlerContext context) {
            super(packet);
            this.context = context;
        }

        public ChannelHandlerContext getContext() {
            return context;
        }

    }

}
