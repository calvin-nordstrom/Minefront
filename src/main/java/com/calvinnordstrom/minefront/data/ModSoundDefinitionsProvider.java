package com.calvinnordstrom.minefront.data;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.registry.ModSoundEvents;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class ModSoundDefinitionsProvider extends SoundDefinitionsProvider {
    public ModSoundDefinitionsProvider(PackOutput output) {
        super(output, Minefront.MOD_ID);
    }

    @Override
    public void registerSounds() {
        add(ModSoundEvents.BULLET_IMPACTS, SoundDefinition.definition()
                .subtitle("subtitles.minefront.bullet_impacts")
                .with(sound(Identifier.fromNamespaceAndPath(Minefront.MOD_ID, "bullet_impacts_1"))));
    }
}
