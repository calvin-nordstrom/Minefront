package com.calvinnordstrom.minefront.event;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.attachment.ModAttachments;
import com.calvinnordstrom.minefront.network.StartShootingPayload;
import com.calvinnordstrom.minefront.network.StopShootingPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Minefront.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class NetworkEvents {
    @SubscribeEvent // on the mod event bus
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playBidirectional(StartShootingPayload.TYPE, StartShootingPayload.STREAM_CODEC, NetworkEvents::handleStartShooting);
        registrar.playBidirectional(StopShootingPayload.TYPE, StopShootingPayload.STREAM_CODEC, NetworkEvents::handleStopShooting);
    }

    private static void handleStartShooting(StartShootingPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = ctx.player();

            player.getData(ModAttachments.GUN_STATE).triggerHeld = true;
        });
    }

    private static void handleStopShooting(StopShootingPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = ctx.player();

            player.getData(ModAttachments.GUN_STATE).triggerHeld = false;
        });
    }
}
