package com.calvinnordstrom.minefront.data;

import com.calvinnordstrom.minefront.Minefront;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

// Runs only through the clientData run configuration. Client data covers
// both assets and data, and the model provider needs client classes.
@EventBusSubscriber(modid = Minefront.MOD_ID, value = Dist.CLIENT)
public class DataGenerators {
    private DataGenerators() {
    }

    @SubscribeEvent
    public static void onGatherClientData(GatherDataEvent.Client event) {
        event.createProvider(ModModelProvider::new);
        event.createProvider(ModLanguageProvider::new);
        event.createProvider(ModSoundDefinitionsProvider::new);
    }
}
