package com.calvinnordstrom.minefront.entity.client;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.entity.custom.BulletEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BulletEntityRenderer extends EntityRenderer<BulletEntity> {
    private final BulletEntityModel model;

    public BulletEntityRenderer(EntityRendererProvider.Context context) {
        super(context);

        model = new BulletEntityModel(context.bakeLayer(BulletEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(BulletEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        float yaw = entity.getInterpolatedYaw(partialTick);
        float pitch = entity.getInterpolatedPitch(partialTick);

        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        poseStack.mulPose(Axis.XP.rotationDegrees(-pitch));

        VertexConsumer vc = ItemRenderer.getFoilBufferDirect(
                bufferSource,
                model.renderType(getTextureLocation(entity)),
                false,
                false
        );

        model.renderToBuffer(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(BulletEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Minefront.MOD_ID, "textures/entity/bullet/bullet.png");
    }
}
