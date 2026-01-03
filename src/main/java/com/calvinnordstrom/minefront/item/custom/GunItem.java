package com.calvinnordstrom.minefront.item.custom;

import com.calvinnordstrom.minefront.gun.FireControl;
import com.calvinnordstrom.minefront.gun.GunStats;
import net.minecraft.world.item.Item;

public class GunItem extends Item {
    private final GunStats stats;
    private final FireControl fireControl;

    public GunItem(Properties properties, GunStats stats, FireControl fireControl) {
        super(properties);
        this.stats = stats;
        this.fireControl = fireControl;
    }

    public GunStats getStats() {
        return stats;
    }

    public FireControl getFireControl() {
        return fireControl;
    }
}
