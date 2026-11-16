package com.calvinnordstrom.minefront.entity.client;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.entity.custom.BulletEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class BulletEntityRenderer extends EntityRenderer<BulletEntity, BulletEntityRenderState> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Minefront.MOD_ID, "textures/entity/bullet/bullet.png");
    private final BulletEntityModel model;

    public BulletEntityRenderer(EntityRendererProvider.Context context) {
        super(context);

        model = new BulletEntityModel(context.bakeLayer(BulletEntityModel.LAYER_LOCATION));
    }

    @Override
    public void submit(BulletEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();

        poseStack.rotateDegrees(Axis.YP, state.yaw);
        poseStack.rotateDegrees(Axis.XP, -state.pitch);

        submitNodeCollector.submitModel(model, state, poseStack, TEXTURE, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);

        poseStack.popPose();

        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public BulletEntityRenderState createRenderState() {
        return new BulletEntityRenderState();
    }

    @Override
    public void extractRenderState(BulletEntity entity, BulletEntityRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.yaw = entity.getInterpolatedYaw(partialTicks);
        state.pitch = entity.getInterpolatedPitch(partialTicks);
    }
}
