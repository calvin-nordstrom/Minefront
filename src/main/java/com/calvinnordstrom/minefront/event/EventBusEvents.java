package com.calvinnordstrom.minefront.event;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.entity.ModEntities;
import com.calvinnordstrom.minefront.entity.client.BulletEntityModel;
import com.calvinnordstrom.minefront.entity.client.BulletEntityRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Minefront.MOD_ID, value = Dist.CLIENT)
public class EventBusEvents {
    @SubscribeEvent
    public static void onEntityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BulletEntityModel.LAYER_LOCATION, BulletEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.BULLET.get(), BulletEntityRenderer::new);
    }
}
