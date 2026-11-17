package com.calvinnordstrom.minefront;

import com.calvinnordstrom.minefront.attachment.ModAttachments;
import com.calvinnordstrom.minefront.entity.ModEntities;
import com.calvinnordstrom.minefront.item.ModCreativeModeTabs;
import com.calvinnordstrom.minefront.item.ModItems;
import com.calvinnordstrom.minefront.sound.ModSounds;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Minefront.MOD_ID)
public class Minefront {
    public static final String MOD_ID = "minefront";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Minefront(IEventBus modEventBus) {
        ModAttachments.register(modEventBus);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);

        ModEntities.register(modEventBus);

        ModSounds.register(modEventBus);
    }
}
