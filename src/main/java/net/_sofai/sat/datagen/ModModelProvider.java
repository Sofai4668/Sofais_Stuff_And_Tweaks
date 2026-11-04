package net._sofai.sat.datagen;

import net._sofai.sat.block.ModBlocks;
import net._sofai.sat.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.world.level.block.Blocks;

public class ModModelProvider extends FabricModelProvider {
    public  ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createFullAndCarpetBlocks(ModBlocks.RED_MOSS_BLOCK,ModBlocks.RED_MOSS_CARPET);
        blockModelGenerators.createFullAndCarpetBlocks(ModBlocks.ORANGE_MOSS_BLOCK,ModBlocks.ORANGE_MOSS_CARPET);
        blockModelGenerators.createFullAndCarpetBlocks(ModBlocks.YELLOW_MOSS_BLOCK,ModBlocks.YELLOW_MOSS_CARPET);

        blockModelGenerators.createCrossBlockWithDefaultItem(ModBlocks.ORANGE_SHRUB, BlockModelGenerators.PlantType.NOT_TINTED, TextureMapping.cross(ModBlocks.ORANGE_SHRUB));
        blockModelGenerators.createCrossBlockWithDefaultItem(ModBlocks.YELLOW_SHRUB, BlockModelGenerators.PlantType.NOT_TINTED, TextureMapping.cross(ModBlocks.YELLOW_SHRUB));

        blockModelGenerators.createTrivialCube(ModBlocks.LEAF_LITTER_BLOCK);

        blockModelGenerators.family(Blocks.STONE).wall(ModBlocks.STONE_WALL);

        blockModelGenerators.family(Blocks.SMOOTH_STONE)
                .stairs(ModBlocks.SMOOTH_STONE_STAIRS)
                .wall(ModBlocks.SMOOTH_STONE_WALL)
        ;
        blockModelGenerators.family(Blocks.CRACKED_STONE_BRICKS)
                .stairs(ModBlocks.CRACKED_STONE_BRICK_STAIRS)
                .slab(ModBlocks.CRACKED_STONE_BRICK_SLAB)
                .wall(ModBlocks.CRACKED_STONE_BRICK_WALL)
        ;
        blockModelGenerators.family(Blocks.POLISHED_GRANITE)
                .wall(ModBlocks.POLISHED_GRANITE_WALL)
        ;
        blockModelGenerators.family(Blocks.POLISHED_DIORITE)
                .wall(ModBlocks.POLISHED_DIORITE_WALL)
        ;
        blockModelGenerators.family(Blocks.POLISHED_ANDESITE)
                .wall(ModBlocks.POLISHED_ANDESITE_WALL)
        ;
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.A_BIT_OF_MOSS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.A_BIT_OF_RED_MOSS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.A_BIT_OF_ORANGE_MOSS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.A_BIT_OF_YELLOW_MOSS, ModelTemplates.FLAT_ITEM);
    }
}
