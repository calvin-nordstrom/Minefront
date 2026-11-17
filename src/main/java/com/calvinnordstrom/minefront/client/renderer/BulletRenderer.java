package com.calvinnordstrom.minefront.client.renderer;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.projectile.BulletEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class BulletRenderer extends EntityRenderer<BulletEntity, BulletRenderState> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(Minefront.MOD_ID, "textures/entity/bullet/bullet.png");
    private final BulletModel model;

    public BulletRenderer(EntityRendererProvider.Context context) {
        super(context);

        model = new BulletModel(context.bakeLayer(BulletModel.LAYER_LOCATION));
    }

    @Override
    public void submit(BulletRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();

        poseStack.rotateDegrees(Axis.YP, state.yaw);
        poseStack.rotateDegrees(Axis.XP, -state.pitch);

        submitNodeCollector.submitModel(model, state, poseStack, TEXTURE, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);

        poseStack.popPose();

        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public BulletRenderState createRenderState() {
        return new BulletRenderState();
    }

    @Override
    public void extractRenderState(BulletEntity entity, BulletRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.yaw = entity.getInterpolatedYaw(partialTicks);
        state.pitch = entity.getInterpolatedPitch(partialTicks);
    }
}
