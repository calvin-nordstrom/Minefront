package com.calvinnordstrom.minefront.event.client;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.item.custom.GunItem;
import com.calvinnordstrom.minefront.network.StartFiringPayload;
import com.calvinnordstrom.minefront.network.StopFiringPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static com.calvinnordstrom.minefront.util.GunUtil.*;

@EventBusSubscriber(value = Dist.CLIENT, modid = Minefront.MOD_ID)
public final class GunItemEvents {
    private static double storedSensitivity = -1.0;
    private static boolean triggerHeld = false;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Player player = Minecraft.getInstance().player;
        Options options = Minecraft.getInstance().options;

        GunItem gun = getHeldGun(player);
        if (gun == null) {
            return;
        }

        // Handle aiming sensitivity
        if (isAiming(player) && storedSensitivity < 0) {
            storedSensitivity = options.sensitivity().get();
            options.sensitivity().set(storedSensitivity * gun.getStats().getAimSensitivityCoefficient());
        } else if (!isAiming(player) && storedSensitivity >= 0) {
            options.sensitivity().set(storedSensitivity);
            storedSensitivity = -1;
        }

        // Handle shooting
        if (isFiring(player)) {
            if (!triggerHeld) {
                PacketDistributor.sendToServer(new StartFiringPayload());
            }
            triggerHeld = true;
        } else {
            if (triggerHeld) {
                PacketDistributor.sendToServer(new StopFiringPayload());
            }
            triggerHeld = false;
        }
    }

    @SubscribeEvent
    public static void onComputeFovModifier(ComputeFovModifierEvent event) {
        Player player = event.getPlayer();

        GunItem gun = getHeldGun(player);
        if (gun == null) {
            return;
        }

        // Handle aiming FOV
        if (isAiming(player)) {
            event.setNewFovModifier(gun.getStats().getAimFovModifier());
        }
    }

    @SubscribeEvent
    public static void onInput(InputEvent.InteractionKeyMappingTriggered event) {
        Player player = Minecraft.getInstance().player;

        // Handle gun input behavior and remove hand swinging
        if (isHoldingGun(player)) {
            if (event.isAttack()) {
                event.setCanceled(true);
            }
            event.setSwingHand(false);
        }
    }
}
