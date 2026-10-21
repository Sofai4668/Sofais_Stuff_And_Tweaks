package net._sofai.sat.datagen;

import net._sofai.sat.block.ModBlocks;
import net._sofai.sat.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;

public class ModModelProvider extends FabricModelProvider {
    public  ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createFullAndCarpetBlocks(ModBlocks.RED_MOSS_BLOCK,ModBlocks.RED_MOSS_CARPET);
        blockModelGenerators.createFullAndCarpetBlocks(ModBlocks.ORANGE_MOSS_BLOCK,ModBlocks.ORANGE_MOSS_CARPET);
        blockModelGenerators.createCrossBlockWithDefaultItem(ModBlocks.ORANGE_SHRUB, BlockModelGenerators.PlantType.NOT_TINTED, TextureMapping.cross(ModBlocks.ORANGE_SHRUB));
        blockModelGenerators.createFullAndCarpetBlocks(ModBlocks.YELLOW_MOSS_BLOCK,ModBlocks.YELLOW_MOSS_CARPET);
        blockModelGenerators.createCrossBlockWithDefaultItem(ModBlocks.YELLOW_SHRUB, BlockModelGenerators.PlantType.NOT_TINTED, TextureMapping.cross(ModBlocks.YELLOW_SHRUB));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.BELL_PLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PIE_BASE, ModelTemplates.FLAT_ITEM);

    }
}
