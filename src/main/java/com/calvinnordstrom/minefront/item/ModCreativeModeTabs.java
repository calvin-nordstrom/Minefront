package com.calvinnordstrom.minefront.item;

import com.calvinnordstrom.minefront.Minefront;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Minefront.MOD_ID);

    public static final Supplier<CreativeModeTab> GUNS_TAB = CREATIVE_MODE_TAB.register("guns", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.KARABINER_98K.get()))
            .title(Component.translatable("creativetab.minefront.guns"))
            .displayItems((displayItemsGenerator, output) -> {
                output.accept(ModItems.KARABINER_98K);
                output.accept(ModItems.MK12_MOD_1);
                output.accept(ModItems.AK_47);
            })
            .build());

    private ModCreativeModeTabs() {
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
