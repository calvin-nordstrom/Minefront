package com.calvinnordstrom.minefront.gun;

import com.calvinnordstrom.minefront.projectile.BulletEntity;
import com.calvinnordstrom.minefront.registry.ModEntityTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class BurstFireControl implements FireControl {
    private static final int BURST_SIZE = 3;

    @Override
    public void onTriggerPressed(ServerPlayer player, GunItem gun, GunState state) {
        if (state.cooldownTicks > 0) {
            return;
        }
        if (state.burstShotsRemaining > 0) {
            return;
        }

        state.burstShotsRemaining = BURST_SIZE;
    }

    @Override
    public void onTriggerReleased(ServerPlayer player, GunItem gun, GunState state) {
    }

    @Override
    public void serverTick(ServerPlayer player, GunItem gun, GunState state) {
        if (state.burstShotsRemaining <= 0) {
            return;
        }
        if (state.cooldownTicks > 0) {
            return;
        }

        BulletEntity bullet = new BulletEntity(ModEntityTypes.BULLET.get(), player.level(), player);
        Vec3 look = player.getLookAngle();
        bullet.shoot(look, gun.getStats().getVelocity());
        player.level().addFreshEntity(bullet);

        state.burstShotsRemaining--;

        if (state.burstShotsRemaining == 0) {
            state.cooldownTicks = gun.getStats().getBurstDelay();
        } else {
            state.cooldownTicks = gun.getStats().getFireDelay();
        }
    }
}

