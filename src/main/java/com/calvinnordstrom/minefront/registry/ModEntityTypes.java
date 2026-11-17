package com.calvinnordstrom.minefront.registry;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.projectile.BulletEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntityTypes {
    public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(Minefront.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<BulletEntity>> BULLET = ENTITY_TYPES.registerEntityType(
            "bullet", BulletEntity::new, MobCategory.MISC, builder -> builder
                    .sized(0.1F, 0.1F)
                    .clientTrackingRange(64)
                    .updateInterval(1)
    );

    private ModEntityTypes() {
    }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
