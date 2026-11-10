package net._sofai.sat.datagen;

import net._sofai.sat.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;
public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                //crafting table
                stairBuilder(ModBlocks.CRACKED_STONE_BRICK_STAIRS, Ingredient.of(Blocks.CRACKED_STONE_BRICKS))
                        .unlockedBy(getHasName(Blocks.CRACKED_STONE_BRICKS), has(Blocks.CRACKED_STONE_BRICKS))
                        .group("cracked_stone_bricks")
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_STONE_BRICK_SLAB, Blocks.CRACKED_STONE_BRICKS);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_STONE_BRICK_WALL, Blocks.CRACKED_STONE_BRICKS);
                //stone cutter
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRACKED_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_STONE_BRICK_STAIRS,1)
                        .unlockedBy(getHasName(Blocks.CRACKED_STONE_BRICKS), has(Blocks.CRACKED_STONE_BRICKS))
                        .group("cracked_stone_bricks")
                        .save(output, "cracked_stone_brick_stairs_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRACKED_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_STONE_BRICK_SLAB,2)
                        .unlockedBy(getHasName(Blocks.CRACKED_STONE_BRICKS), has(Blocks.CRACKED_STONE_BRICKS))
                        .group("cracked_stone_bricks")
                        .save(output, "cracked_stone_brick_slab_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRACKED_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_STONE_BRICK_WALL,1)
                        .unlockedBy(getHasName(Blocks.CRACKED_STONE_BRICKS), has(Blocks.CRACKED_STONE_BRICKS))
                        .group("cracked_stone_bricks")
                        .save(output, "cracked_stone_brick_wall_from_stonecutting");
                }
            };
        };
    }
