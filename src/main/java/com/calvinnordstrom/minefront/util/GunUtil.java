package com.calvinnordstrom.minefront.util;

import com.calvinnordstrom.minefront.item.custom.GunItem;
import net.minecraft.client.Minecraft;
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

    public static boolean isAiming(Player player) {
        return isHoldingGun(player) && Minecraft.getInstance().options.keyUse.isDown();
    }

    public static boolean isFiring(Player player) {
        return isHoldingGun(player) && Minecraft.getInstance().options.keyAttack.isDown();
    }
}
