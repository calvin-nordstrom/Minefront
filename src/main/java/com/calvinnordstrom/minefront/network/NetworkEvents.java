package com.calvinnordstrom.minefront.network;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.gun.GunItem;
import com.calvinnordstrom.minefront.gun.GunState;
import com.calvinnordstrom.minefront.network.payload.StartFiringPayload;
import com.calvinnordstrom.minefront.network.payload.StopFiringPayload;
import com.calvinnordstrom.minefront.registry.ModAttachmentTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static com.calvinnordstrom.minefront.gun.GunUtil.getHeldGun;

@EventBusSubscriber(modid = Minefront.MOD_ID)
public final class NetworkEvents {
    @SubscribeEvent // on the mod event bus
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(StartFiringPayload.TYPE, StartFiringPayload.STREAM_CODEC, NetworkEvents::handleStartShooting);
        registrar.playToServer(StopFiringPayload.TYPE, StopFiringPayload.STREAM_CODEC, NetworkEvents::handleStopShooting);
    }

    private static void handleStartShooting(StartFiringPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = ctx.player();
            if (!(player instanceof ServerPlayer)) {
                return;
            }

            GunItem gun = getHeldGun(player);
            if (gun == null) {
                return;
            }

            GunState state = player.getData(ModAttachmentTypes.GUN_STATE);
            gun.getFireControl().onTriggerPressed((ServerPlayer) player, gun, state);
        });
    }

    private static void handleStopShooting(StopFiringPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = ctx.player();
            if (!(player instanceof ServerPlayer)) {
                return;
            }

            // Release the trigger even if the gun is no longer held
            GunState state = player.getData(ModAttachmentTypes.GUN_STATE);
            state.triggerHeld = false;

            GunItem gun = getHeldGun(player);
            if (gun == null) {
                return;
            }

            gun.getFireControl().onTriggerReleased((ServerPlayer) player, gun, state);
        });
    }
}
