package com.calvinnordstrom.minefront.event.client;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.network.StartShootingPayload;
import com.calvinnordstrom.minefront.network.StopShootingPayload;
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

        // Handle aiming sensitivity
        if (isAiming(player) && storedSensitivity < 0) {
            storedSensitivity = options.sensitivity().get();
            options.sensitivity().set(storedSensitivity * getHeldGun(player).getAimSensitivityCoefficient());
        } else if (!isAiming(player) && storedSensitivity >= 0) {
            options.sensitivity().set(storedSensitivity);
            storedSensitivity = -1;
        }

        // Handle shooting
        if (isShooting(player)) {
            if (!triggerHeld) {
                PacketDistributor.sendToServer(new StartShootingPayload());
            }
            triggerHeld = true;
        } else {
            if (triggerHeld) {
                PacketDistributor.sendToServer(new StopShootingPayload());
            }
            triggerHeld = false;
        }
    }

    @SubscribeEvent
    public static void onComputeFovModifier(ComputeFovModifierEvent event) {
        // Handle aiming FOV
        Player player = event.getPlayer();
        if (isAiming(player)) {
            event.setNewFovModifier(getHeldGun(player).getAimFovModifier());
        }
    }

    @SubscribeEvent
    public static void onInput(InputEvent.InteractionKeyMappingTriggered event) {
        // Handle gun input behavior and remove hand swinging
        if (isHoldingGun(Minecraft.getInstance().player)) {
            if (event.isAttack()) {
                event.setCanceled(true);
            }
            event.setSwingHand(false);
        }
    }
}
