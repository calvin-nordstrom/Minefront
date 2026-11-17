package com.calvinnordstrom.minefront.registry;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.gun.FullAutoFireControl;
import com.calvinnordstrom.minefront.gun.GunItem;
import com.calvinnordstrom.minefront.gun.GunStats;
import com.calvinnordstrom.minefront.gun.RepeatingFireControl;
import com.calvinnordstrom.minefront.gun.SemiAutoFireControl;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Minefront.MOD_ID);

    public static final DeferredItem<GunItem> KARABINER_98K = ITEMS.registerItem("karabiner_98k", properties -> new GunItem(
            properties.stacksTo(1),
            new GunStats(8, 20, 0, 0.9F, 0.9),
            new RepeatingFireControl()
    ));

    public static final DeferredItem<GunItem> MK12_MOD_1 = ITEMS.registerItem("mk12_mod_1", properties -> new GunItem(
            properties.stacksTo(1),
            new GunStats(16, 4, 0, 0.2F, 0.2),
            new SemiAutoFireControl()
    ));

    public static final DeferredItem<GunItem> AK_47 = ITEMS.registerItem("ak_47", properties -> new GunItem(
            properties.stacksTo(1),
            new GunStats(4, 1, 5, 0.6F, 0.6),
            new FullAutoFireControl()
    ));

    private ModItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
