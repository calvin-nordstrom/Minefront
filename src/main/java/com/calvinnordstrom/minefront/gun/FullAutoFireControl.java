package com.calvinnordstrom.minefront.gun;

import com.calvinnordstrom.minefront.projectile.BulletEntity;
import com.calvinnordstrom.minefront.registry.ModEntityTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class FullAutoFireControl implements FireControl {
    @Override
    public void onTriggerPressed(ServerPlayer player, GunItem gun, GunState state) {
        state.triggerHeld = true;
    }

    @Override
    public void onTriggerReleased(ServerPlayer player, GunItem gun, GunState state) {
        state.triggerHeld = false;
    }

    @Override
    public void serverTick(ServerPlayer player, GunItem gun, GunState state) {
        if (!state.triggerHeld) {
            return;
        }
        if (state.cooldownTicks > 0) {
            return;
        }

        BulletEntity bullet = new BulletEntity(ModEntityTypes.BULLET.get(), player.level(), player);
        Vec3 look = player.getLookAngle();
        bullet.shoot(look, gun.getStats().getVelocity());
        player.level().addFreshEntity(bullet);

        state.cooldownTicks = gun.getStats().getFireDelay();
    }
}
