package net._sofai.sat.datagen;

import net._sofai.sat.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {

        dropSelf(ModBlocks.RED_MOSS_BLOCK);
        dropSelf(ModBlocks.ORANGE_MOSS_BLOCK);
        dropSelf(ModBlocks.YELLOW_MOSS_BLOCK);
        dropSelf(ModBlocks.RED_MOSS_CARPET);
        dropSelf(ModBlocks.ORANGE_MOSS_CARPET);
        dropSelf(ModBlocks.YELLOW_MOSS_CARPET);
        dropSelf(ModBlocks.ORANGE_SHRUB);
        dropSelf(ModBlocks.YELLOW_SHRUB);
        dropSelf(ModBlocks.LEAF_LITTER_BLOCK);

    }
}
