package com.calvinnordstrom.minefront;

import com.calvinnordstrom.minefront.registry.ModAttachmentTypes;
import com.calvinnordstrom.minefront.registry.ModCreativeModeTabs;
import com.calvinnordstrom.minefront.registry.ModEntityTypes;
import com.calvinnordstrom.minefront.registry.ModItems;
import com.calvinnordstrom.minefront.registry.ModSoundEvents;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Minefront.MOD_ID)
public class Minefront {
    public static final String MOD_ID = "minefront";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Minefront(IEventBus modEventBus) {
        ModAttachmentTypes.register(modEventBus);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);

        ModEntityTypes.register(modEventBus);

        ModSoundEvents.register(modEventBus);
    }
}
