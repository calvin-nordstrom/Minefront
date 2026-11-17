package com.calvinnordstrom.minefront.data;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.registry.ModEntityTypes;
import com.calvinnordstrom.minefront.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, Minefront.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItem(ModItems.KARABINER_98K, "Karabiner 98k");
        addItem(ModItems.MK12_MOD_1, "Mk12 Mod 1");
        addItem(ModItems.AK_47, "AK-47");

        add("itemGroup.minefront.guns", "Guns");

        addEntityType(ModEntityTypes.BULLET, "Bullet");

        add("subtitles.minefront.bullet_impacts", "Bullet impacts");
    }
}
