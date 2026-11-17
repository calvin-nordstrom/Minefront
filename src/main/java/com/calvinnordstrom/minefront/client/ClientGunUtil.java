package com.calvinnordstrom.minefront.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import static com.calvinnordstrom.minefront.gun.GunUtil.isHoldingGun;

public class ClientGunUtil {
    private ClientGunUtil() {
    }

    public static boolean isAiming(Player player) {
        return isHoldingGun(player) && Minecraft.getInstance().options.keyUse.isDown();
    }

    public static boolean isFiring(Player player) {
        return isHoldingGun(player) && Minecraft.getInstance().options.keyAttack.isDown();
    }
}
