package com.calvinnordstrom.minefront.gun;

import net.minecraft.server.level.ServerPlayer;

public interface FireControl {
    void onTriggerPressed(ServerPlayer player, GunItem gun, GunState state);
    void onTriggerReleased(ServerPlayer player, GunItem gun, GunState state);
    void serverTick(ServerPlayer player, GunItem gun, GunState state);
}
