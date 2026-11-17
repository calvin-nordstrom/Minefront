package com.calvinnordstrom.minefront.gun;

import net.minecraft.world.entity.player.Player;

public class GunUtil {
    private GunUtil() {
    }

    public static GunItem getHeldGun(Player player) {
        if (player != null) {
            if (player.getMainHandItem().getItem() instanceof GunItem gunItem) {
                return gunItem;
            }
        }
        return null;
    }

    public static boolean isHoldingGun(Player player) {
        return getHeldGun(player) != null;
    }
}
