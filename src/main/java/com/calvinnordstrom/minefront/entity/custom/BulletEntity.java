package com.calvinnordstrom.minefront.entity.custom;

import com.calvinnordstrom.minefront.Minefront;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BulletEntity extends Projectile {
    public static final EntityDataAccessor<Float> YAW = SynchedEntityData.defineId(BulletEntity.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Float> PITCH = SynchedEntityData.defineId(BulletEntity.class, EntityDataSerializers.FLOAT);
    private static final double GRAVITY = 0.05;
    private static final double DRAG = 0.99;
    private Vec3 prevPos = Vec3.ZERO;
    private float prevYaw;
    private float prevPitch;
    private int life;

    public BulletEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    public BulletEntity(EntityType<? extends BulletEntity> entityType, Level level, LivingEntity shooter) {
        this(entityType, level);
        setOwner(shooter);
        setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
    }

    public void shoot(Vec3 direction, double velocity) {
        Vec3 motion = direction.normalize().scale(velocity);
        setDeltaMovement(motion);
        updateRotationFromVelocity(motion);
    }

    @Override
    public void tick() {
        prevPos = position();
        prevYaw = getYRot();
        prevPitch = getXRot();

        if (!level().isClientSide) {
            serverTick();
        }
    }

    private void serverTick() {
        Vec3 start = position();
        Vec3 velocity = getDeltaMovement();
        velocity = velocity.add(0, -GRAVITY, 0);
        velocity = velocity.scale(DRAG);
        setDeltaMovement(velocity);
        Vec3 end = start.add(velocity);

        HitResult hit = raycast(start, end);
        if (hit != null) {
            onHit(hit);
            discard();
            return;
        }

        setPos(end.x, end.y, end.z);

        updateRotationFromVelocity(velocity);

        if (++life > 100) {
            discard();
        }
    }

    private HitResult raycast(Vec3 start, Vec3 end) {
        HitResult block = level().clip(new ClipContext(
                start, end,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                this
        ));

        EntityHitResult entity = ProjectileUtil.getEntityHitResult(
                level(), this, start, end,
                getBoundingBox().expandTowards(getDeltaMovement()).inflate(1.0),
                e -> e != getOwner()
        );

        if (entity != null) {
            return entity;
        }

        if (block.getType() != HitResult.Type.MISS) {
            return block;
        }

        return null;
    }

    private void updateRotationFromVelocity(Vec3 v) {
        float yaw = (float) (Mth.atan2(v.x, v.z) * (180.0F / Math.PI));
        float pitch = (float) (Mth.atan2(v.y, v.horizontalDistance()) * (180.0F / Math.PI));

        setYRot(yaw);
        setXRot(pitch);

        entityData.set(YAW, yaw);
        entityData.set(PITCH, pitch);
    }

    public float getInterpolatedYaw(float partial) {
        return Mth.lerp(partial, prevYaw, entityData.get(YAW));
    }

    public float getInterpolatedPitch(float partial) {
        return Mth.lerp(partial, prevPitch, entityData.get(PITCH));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(YAW, 0f);
        builder.define(PITCH, 0f);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        Minefront.LOGGER.info("Block hit: {}", level().getBlockState(result.getBlockPos()).getBlock());
        discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Minefront.LOGGER.info("Entity hit: {}", result.getEntity());
        discard();
    }

    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
    }
}
