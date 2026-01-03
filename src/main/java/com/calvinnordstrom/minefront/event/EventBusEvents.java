package com.calvinnordstrom.minefront.event;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.entity.client.BulletEntityModel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Minefront.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EventBusEvents {
    @SubscribeEvent
    public static void onEntityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BulletEntityModel.LAYER_LOCATION, BulletEntityModel::createBodyLayer);
    }
}
