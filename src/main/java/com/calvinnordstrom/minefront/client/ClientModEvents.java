package com.calvinnordstrom.minefront.client;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.client.renderer.BulletModel;
import com.calvinnordstrom.minefront.client.renderer.BulletRenderer;
import com.calvinnordstrom.minefront.registry.ModEntityTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Minefront.MOD_ID, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onEntityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BulletModel.LAYER_LOCATION, BulletModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.BULLET.get(), BulletRenderer::new);
    }
}
