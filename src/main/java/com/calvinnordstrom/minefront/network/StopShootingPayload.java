package com.calvinnordstrom.minefront.network;

import com.calvinnordstrom.minefront.Minefront;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record StopShootingPayload() implements CustomPacketPayload {
    public static final Type<StopShootingPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Minefront.MOD_ID, "stop_shooting"));

    public static final StreamCodec<ByteBuf, StopShootingPayload> STREAM_CODEC = StreamCodec.unit(new StopShootingPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
