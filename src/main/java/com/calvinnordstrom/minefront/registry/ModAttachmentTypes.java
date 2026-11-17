package com.calvinnordstrom.minefront.registry;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.gun.GunState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Minefront.MOD_ID);

    public static final Supplier<AttachmentType<GunState>> GUN_STATE = ATTACHMENT_TYPES.register(
            "gun_state", () -> AttachmentType.builder(GunState::new).build()
    );

    private ModAttachmentTypes() {
    }

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
