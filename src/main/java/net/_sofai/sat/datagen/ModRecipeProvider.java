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
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STONE_WALL, Blocks.STONE);

                stairBuilder(ModBlocks.SMOOTH_STONE_STAIRS, Ingredient.of(Blocks.SMOOTH_STONE))
                        .unlockedBy(getHasName(Blocks.SMOOTH_STONE), has(Blocks.SMOOTH_STONE))
                        .group("cracked_stone_bricks")
                        .save(output);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_WALL, Blocks.SMOOTH_STONE);

                stairBuilder(ModBlocks.CRACKED_STONE_BRICK_STAIRS, Ingredient.of(Blocks.CRACKED_STONE_BRICKS))
                        .unlockedBy(getHasName(Blocks.CRACKED_STONE_BRICKS), has(Blocks.CRACKED_STONE_BRICKS))
                        .group("cracked_stone_bricks")
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_STONE_BRICK_SLAB, Blocks.CRACKED_STONE_BRICKS);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_STONE_BRICK_WALL, Blocks.CRACKED_STONE_BRICKS);

                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);

                stairBuilder(ModBlocks.DEEPSLATE_STAIRS, Ingredient.of(Blocks.DEEPSLATE))
                        .unlockedBy(getHasName(Blocks.DEEPSLATE), has(Blocks.DEEPSLATE))
                        .group("deepslate")
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEEPSLATE_SLAB, Blocks.DEEPSLATE);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEEPSLATE_WALL, Blocks.DEEPSLATE);

                stairBuilder(ModBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS, Ingredient.of(Blocks.CRACKED_DEEPSLATE_BRICKS))
                        .unlockedBy(getHasName(Blocks.CRACKED_DEEPSLATE_BRICKS), has(Blocks.CRACKED_DEEPSLATE_BRICKS))
                        .group("cracked_deepslate_bricks")
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_BRICK_SLAB, Blocks.CRACKED_DEEPSLATE_BRICKS);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_BRICK_WALL, Blocks.CRACKED_DEEPSLATE_BRICKS);

                stairBuilder(ModBlocks.CRACKED_DEEPSLATE_TILE_STAIRS, Ingredient.of(Blocks.CRACKED_DEEPSLATE_TILES))
                        .unlockedBy(getHasName(Blocks.CRACKED_DEEPSLATE_TILES), has(Blocks.CRACKED_DEEPSLATE_TILES))
                        .group("cracked_deepslate_tiles")
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_TILE_SLAB, Blocks.CRACKED_DEEPSLATE_TILES);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_TILE_WALL, Blocks.CRACKED_DEEPSLATE_TILES);

                stairBuilder(ModBlocks.PACKED_MUD_STAIRS, Ingredient.of(Blocks.PACKED_MUD))
                        .unlockedBy(getHasName(Blocks.PACKED_MUD), has(Blocks.PACKED_MUD))
                        .group("packed_mud")
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_SLAB, Blocks.PACKED_MUD);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_WALL, Blocks.PACKED_MUD);




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
