package com.calvinnordstrom.minefront.network.payload;

import com.calvinnordstrom.minefront.Minefront;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record StartFiringPayload() implements CustomPacketPayload {
    public static final Type<StartFiringPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Minefront.MOD_ID, "start_firing"));

    public static final StreamCodec<ByteBuf, StartFiringPayload> STREAM_CODEC = StreamCodec.unit(new StartFiringPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
