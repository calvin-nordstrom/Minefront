package com.calvinnordstrom.minefront.item;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.gun.*;
import com.calvinnordstrom.minefront.item.custom.GunItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Minefront.MOD_ID);

    public static final DeferredItem<Item> KARABINER_98K = ITEMS.register("karabiner_98k", () -> new GunItem(
            new Item.Properties().stacksTo(1),
            new GunStats(8, 20, 0, 0.9F, 0.9),
            new RepeatingFireControl()
    ));

    public static final DeferredItem<Item> MK12_MOD_1 = ITEMS.register("mk12_mod_1", () -> new GunItem(
            new Item.Properties().stacksTo(1),
            new GunStats(16, 4, 0, 0.2F, 0.2),
            new SemiAutoFireControl()
    ));
    public static final DeferredItem<Item> AK_47 = ITEMS.register("ak_47", () -> new GunItem(
            new Item.Properties().stacksTo(1),
            new GunStats(4, 1, 5, 0.6F, 0.6),
            new FullAutoFireControl()
    ));

    private ModItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
