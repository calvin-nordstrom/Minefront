package com.calvinnordstrom.minefront;

import com.calvinnordstrom.minefront.attachment.ModAttachments;
import com.calvinnordstrom.minefront.entity.ModEntities;
import com.calvinnordstrom.minefront.item.ModCreativeModeTabs;
import com.calvinnordstrom.minefront.item.ModItems;
import com.calvinnordstrom.minefront.sound.ModSounds;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(Minefront.MOD_ID)
public class Minefront {
    public static final String MOD_ID = "minefront";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Minefront(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        ModAttachments.register(modEventBus);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);

        ModEntities.register(modEventBus);

        ModSounds.register(modEventBus);

        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.LOCAL, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
