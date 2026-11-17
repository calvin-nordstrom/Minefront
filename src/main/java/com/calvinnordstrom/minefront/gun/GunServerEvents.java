package com.calvinnordstrom.minefront.gun;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.registry.ModAttachmentTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import static com.calvinnordstrom.minefront.gun.GunUtil.getHeldGun;

@EventBusSubscriber(modid = Minefront.MOD_ID)
public class GunServerEvents {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (!(player instanceof ServerPlayer)) {
            return;
        }

        GunState state = player.getData(ModAttachmentTypes.GUN_STATE);

        // Count down even while no gun is held so cooldowns do not pause
        if (state.cooldownTicks > 0) {
            state.cooldownTicks--;
        }

        // Cancel any held trigger or burst in progress when the held gun changes
        GunItem gun = getHeldGun(player);
        int slot = player.getInventory().getSelectedSlot();
        if (gun != state.heldGun || slot != state.heldSlot) {
            state.triggerHeld = false;
            state.burstShotsRemaining = 0;
            state.heldGun = gun;
            state.heldSlot = slot;
        }

        if (gun == null) {
            return;
        }

        gun.getFireControl().serverTick((ServerPlayer) player, gun, state);
    }
}
