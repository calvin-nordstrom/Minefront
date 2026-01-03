package com.calvinnordstrom.minefront.event.server;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.attachment.ModAttachments;
import com.calvinnordstrom.minefront.attachment.custom.GunState;
import com.calvinnordstrom.minefront.item.custom.GunItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import static com.calvinnordstrom.minefront.util.GunUtil.getHeldGun;

@EventBusSubscriber(modid = Minefront.MOD_ID)
public class ServerTickEvents {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (!(player instanceof ServerPlayer)) {
            return;
        }

        GunItem gun = getHeldGun(player);
        if (gun == null) {
            return;
        }

        GunState state = player.getData(ModAttachments.GUN_STATE);
        if (state.cooldownTicks > 0) {
            state.cooldownTicks--;
        }

        gun.getFireControl().serverTick((ServerPlayer) player, gun, state);
    }
}
