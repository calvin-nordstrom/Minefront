package com.calvinnordstrom.minefront.data;

import com.calvinnordstrom.minefront.Minefront;
import com.calvinnordstrom.minefront.registry.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, Minefront.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.KARABINER_98K.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MK12_MOD_1.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.AK_47.get(), ModelTemplates.FLAT_ITEM);
    }
}
