package com.calvinnordstrom.minefront.registry;

import com.calvinnordstrom.minefront.Minefront;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, Minefront.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> BULLET_IMPACTS = registerVariableRange("bullet_impacts");

    private ModSoundEvents() {
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

    private static DeferredHolder<SoundEvent, SoundEvent> registerVariableRange(String name) {
        return SOUND_EVENTS.register(name, SoundEvent::createVariableRangeEvent);
    }
}
