package com.calvinnordstrom.minefront.network;

import com.calvinnordstrom.minefront.Minefront;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record StartShootingPayload() implements CustomPacketPayload {
    public static final Type<StartShootingPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Minefront.MOD_ID, "start_shooting"));

    public static final StreamCodec<ByteBuf, StartShootingPayload> STREAM_CODEC = StreamCodec.unit(new StartShootingPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
