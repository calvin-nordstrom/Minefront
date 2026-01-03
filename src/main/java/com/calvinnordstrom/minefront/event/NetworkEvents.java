package com.calvinnordstrom.minefront.event;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.attachment.ModAttachments;
import com.calvinnordstrom.minefront.attachment.custom.GunState;
import com.calvinnordstrom.minefront.item.custom.GunItem;
import com.calvinnordstrom.minefront.network.StartShootingPayload;
import com.calvinnordstrom.minefront.network.StopShootingPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static com.calvinnordstrom.minefront.util.GunUtil.getHeldGun;

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
            if (!(player instanceof ServerPlayer)) {
                return;
            }

            GunItem gun = getHeldGun(player);
            if (gun == null) {
                return;
            }

            GunState state = player.getData(ModAttachments.GUN_STATE);
            gun.getFireControl().onTriggerPressed((ServerPlayer) player, gun, state);
        });
    }

    private static void handleStopShooting(StopShootingPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = ctx.player();
            if (!(player instanceof ServerPlayer)) {
                return;
            }

            GunItem gun = getHeldGun(player);
            if (gun == null) {
                return;
            }

            GunState state = player.getData(ModAttachments.GUN_STATE);
            gun.getFireControl().onTriggerReleased((ServerPlayer) player, gun, state);
        });
    }
}
