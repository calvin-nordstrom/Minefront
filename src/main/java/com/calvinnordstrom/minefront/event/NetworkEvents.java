package com.calvinnordstrom.minefront.event;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.attachment.ModAttachments;
import com.calvinnordstrom.minefront.entity.ModEntities;
import com.calvinnordstrom.minefront.entity.custom.BulletEntity;
import com.calvinnordstrom.minefront.item.custom.GunItem;
import com.calvinnordstrom.minefront.network.StartShootingPayload;
import com.calvinnordstrom.minefront.network.StopShootingPayload;
import com.calvinnordstrom.minefront.util.GunUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
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

            GunItem gunItem = GunUtil.getHeldGun(player);
            if (gunItem != null) {
                Level level = player.level();

                player.getData(ModAttachments.GUN_STATE).triggerHeld = true;

                BulletEntity bullet = new BulletEntity(ModEntities.BULLET.get(), level, player);
                Vec3 look = player.getLookAngle();
                bullet.shoot(look, gunItem.getVelocity());
                level.addFreshEntity(bullet);
            }
        });
    }

    private static void handleStopShooting(StopShootingPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = ctx.player();

            player.getData(ModAttachments.GUN_STATE).triggerHeld = false;
        });
    }
}
