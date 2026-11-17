package com.calvinnordstrom.minefront.registry;

import com.calvinnordstrom.minefront.Minefront;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Minefront.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GUNS = CREATIVE_MODE_TABS.register("guns", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.minefront.guns"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.KARABINER_98K.get().getDefaultInstance())
            .displayItems((parameters, output) -> ModItems.ITEMS.getEntries().forEach(item -> output.accept(item.get())))
            .build());

    private ModCreativeModeTabs() {
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
