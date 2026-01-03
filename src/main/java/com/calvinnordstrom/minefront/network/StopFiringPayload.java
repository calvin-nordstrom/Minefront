package com.calvinnordstrom.minefront.network;

import com.calvinnordstrom.minefront.Minefront;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record StopFiringPayload() implements CustomPacketPayload {
    public static final Type<StopFiringPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Minefront.MOD_ID, "stop_firing"));

    public static final StreamCodec<ByteBuf, StopFiringPayload> STREAM_CODEC = StreamCodec.unit(new StopFiringPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
