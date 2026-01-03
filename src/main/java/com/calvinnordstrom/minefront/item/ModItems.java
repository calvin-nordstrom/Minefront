package com.calvinnordstrom.minefront.item;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.item.custom.GunItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Minefront.MOD_ID);

    public static final DeferredItem<Item> KARABINER_98K = ITEMS.register("karabiner_98k", () -> new GunItem.Builder()
            .velocity(8)
            .aimFovModifier(0.9f)
            .aimSensitivityCoefficient(0.9f)
            .build(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MK12_MOD_1 = ITEMS.register("mk12_mod_1", () -> new GunItem.Builder()
            .velocity(32)
            .aimFovModifier(0.2f)
            .aimSensitivityCoefficient(0.4f)
            .build(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> AK_47 = ITEMS.register("ak_47", () -> new GunItem.Builder()
            .velocity(16)
            .aimFovModifier(0.9f)
            .aimSensitivityCoefficient(0.9f)
            .build(new Item.Properties().stacksTo(1)));

    private ModItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
