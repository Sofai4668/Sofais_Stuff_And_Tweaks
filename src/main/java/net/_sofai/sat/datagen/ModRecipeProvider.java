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
                        .group("stone")
                        .save(output);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_WALL, Blocks.SMOOTH_STONE);

                stairBuilder(ModBlocks.CRACKED_STONE_BRICK_STAIRS, Ingredient.of(Blocks.CRACKED_STONE_BRICKS))
                        .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                        .group("stone")
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
                        .unlockedBy(getHasName(Blocks.DEEPSLATE), has(Blocks.DEEPSLATE))
                        .group("deepslate")
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_BRICK_SLAB, Blocks.CRACKED_DEEPSLATE_BRICKS);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_BRICK_WALL, Blocks.CRACKED_DEEPSLATE_BRICKS);

                stairBuilder(ModBlocks.CRACKED_DEEPSLATE_TILE_STAIRS, Ingredient.of(Blocks.CRACKED_DEEPSLATE_TILES))
                        .unlockedBy(getHasName(Blocks.DEEPSLATE), has(Blocks.DEEPSLATE))
                        .group("deepslate")
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_TILE_SLAB, Blocks.CRACKED_DEEPSLATE_TILES);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_TILE_WALL, Blocks.CRACKED_DEEPSLATE_TILES);

                stairBuilder(ModBlocks.PACKED_MUD_STAIRS, Ingredient.of(Blocks.PACKED_MUD))
                        .unlockedBy(getHasName(Blocks.PACKED_MUD), has(Blocks.PACKED_MUD))
                        .group("packed_mud")
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_SLAB, Blocks.PACKED_MUD);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_WALL, Blocks.PACKED_MUD);

                stairBuilder(ModBlocks.CUT_SANDSTONE_STAIRS, Ingredient.of(Blocks.CUT_SANDSTONE))
                        .unlockedBy(getHasName(Blocks.SANDSTONE), has(Blocks.SANDSTONE))
                        .group("sandstone")
                        .save(output);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SANDSTONE_WALL, Blocks.CUT_SANDSTONE);

                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SANDSTONE_WALL, Blocks.SMOOTH_SANDSTONE);

                stairBuilder(ModBlocks.CUT_RED_SANDSTONE_STAIRS, Ingredient.of(Blocks.CUT_RED_SANDSTONE))
                        .unlockedBy(getHasName(Blocks.RED_SANDSTONE), has(Blocks.RED_SANDSTONE))
                        .group("red_sandstone")
                        .save(output);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_RED_SANDSTONE_WALL, Blocks.CUT_RED_SANDSTONE);

                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_RED_SANDSTONE_WALL, Blocks.SMOOTH_RED_SANDSTONE);



                //stone cutter
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.STONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.STONE_WALL,1)
                        .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                        .group("stone")
                        .save(output, "stone_wall_from_stonecutting");

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.SMOOTH_STONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_STAIRS,1)
                        .unlockedBy(getHasName(Blocks.SMOOTH_STONE), has(Blocks.SMOOTH_STONE))
                        .group("stone")
                        .save(output, "smooth_stone_stairs_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.SMOOTH_STONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_WALL,1)
                        .unlockedBy(getHasName(Blocks.SMOOTH_STONE), has(Blocks.SMOOTH_STONE))
                        .group("stone")
                        .save(output, "smooth_stone_wall_from_stonecutting");

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRACKED_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_STONE_BRICK_STAIRS,1)
                        .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                        .group("stone")
                        .save(output, "cracked_stone_brick_stairs_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRACKED_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_STONE_BRICK_SLAB,2)
                        .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                        .group("stone")
                        .save(output, "cracked_stone_brick_slab_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRACKED_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_STONE_BRICK_WALL,1)
                        .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                        .group("stone")
                        .save(output, "cracked_stone_brick_wall_from_stonecutting");

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.POLISHED_DIORITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DIORITE_WALL,1)
                        .unlockedBy(getHasName(Blocks.DIORITE), has(Blocks.DIORITE))
                        .group("diorite")
                        .save(output, "polished_diorite_wall_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.DIORITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DIORITE_WALL,1)
                        .unlockedBy(getHasName(Blocks.DIORITE), has(Blocks.DIORITE))
                        .group("diorite")
                        .save(output, "polished_diorite_wall_from_raw_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.POLISHED_GRANITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GRANITE_WALL,1)
                        .unlockedBy(getHasName(Blocks.GRANITE), has(Blocks.GRANITE))
                        .group("granite")
                        .save(output, "polished_granite_wall_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.GRANITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GRANITE_WALL,1)
                        .unlockedBy(getHasName(Blocks.GRANITE), has(Blocks.GRANITE))
                        .group("granite")
                        .save(output, "polished_granite_wall_from_raw_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.POLISHED_ANDESITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ANDESITE_WALL,1)
                        .unlockedBy(getHasName(Blocks.ANDESITE), has(Blocks.ANDESITE))
                        .group("andesite")
                        .save(output, "polished_andesite_wall_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.ANDESITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ANDESITE_WALL,1)
                        .unlockedBy(getHasName(Blocks.ANDESITE), has(Blocks.ANDESITE))
                        .group("andesite")
                        .save(output, "polished_andesite_wall_from_raw_stonecutting");

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.DEEPSLATE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEEPSLATE_STAIRS,1)
                        .unlockedBy(getHasName(Blocks.DEEPSLATE), has(Blocks.DEEPSLATE))
                        .group("deepslate")
                        .save(output, "deepslate_stairs_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.DEEPSLATE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEEPSLATE_SLAB,2)
                        .unlockedBy(getHasName(Blocks.DEEPSLATE), has(Blocks.DEEPSLATE))
                        .group("deepslate")
                        .save(output, "deepslate_slab_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.DEEPSLATE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEEPSLATE_WALL,1)
                        .unlockedBy(getHasName(Blocks.DEEPSLATE), has(Blocks.DEEPSLATE))
                        .group("deepslate")
                        .save(output, "deepslate_wall_from_stonecutting");

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRACKED_DEEPSLATE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_BRICK_STAIRS,1)
                        .unlockedBy(getHasName(Blocks.CRACKED_DEEPSLATE_BRICKS), has(Blocks.CRACKED_DEEPSLATE_BRICKS))
                        .group("deepslate")
                        .save(output, "cracked_deepslate_brick_stairs_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRACKED_DEEPSLATE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_BRICK_SLAB,2)
                        .unlockedBy(getHasName(Blocks.CRACKED_DEEPSLATE_BRICKS), has(Blocks.CRACKED_DEEPSLATE_BRICKS))
                        .group("deepslate")
                        .save(output, "cracked_deepslate_brick_slab_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRACKED_DEEPSLATE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_BRICK_WALL,1)
                        .unlockedBy(getHasName(Blocks.CRACKED_DEEPSLATE_BRICKS), has(Blocks.CRACKED_DEEPSLATE_BRICKS))
                        .group("deepslate")
                        .save(output, "cracked_deepslate_brick_wall_from_stonecutting");

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRACKED_DEEPSLATE_TILES), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_TILE_STAIRS,1)
                        .unlockedBy(getHasName(Blocks.CRACKED_DEEPSLATE_TILES), has(Blocks.CRACKED_DEEPSLATE_TILES))
                        .group("deepslate")
                        .save(output, "cracked_deepslate_tile_stairs_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRACKED_DEEPSLATE_TILES), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_TILE_SLAB,2)
                        .unlockedBy(getHasName(Blocks.CRACKED_DEEPSLATE_TILES), has(Blocks.CRACKED_DEEPSLATE_TILES))
                        .group("deepslate")
                        .save(output, "cracked_deepslate_tile_slab_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CRACKED_DEEPSLATE_TILES), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_DEEPSLATE_TILE_WALL,1)
                        .unlockedBy(getHasName(Blocks.CRACKED_DEEPSLATE_TILES), has(Blocks.CRACKED_DEEPSLATE_TILES))
                        .group("deepslate")
                        .save(output, "cracked_deepslate_tile_wall_from_stonecutting");

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.PACKED_MUD), RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_STAIRS,1)
                        .unlockedBy(getHasName(Blocks.PACKED_MUD), has(Blocks.PACKED_MUD))
                        .group("packed_mud")
                        .save(output, "packed_mud_stairs_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.PACKED_MUD), RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_SLAB,2)
                        .unlockedBy(getHasName(Blocks.PACKED_MUD), has(Blocks.PACKED_MUD))
                        .group("packed_mud")
                        .save(output, "packed_mud_slab_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.PACKED_MUD), RecipeCategory.BUILDING_BLOCKS, ModBlocks.PACKED_MUD_WALL,1)
                        .unlockedBy(getHasName(Blocks.PACKED_MUD), has(Blocks.PACKED_MUD))
                        .group("packed_mud")
                        .save(output, "packed_mud_wall_from_stonecutting");

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.SMOOTH_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SANDSTONE_WALL,1)
                        .unlockedBy(getHasName(Blocks.SANDSTONE), has(Blocks.SANDSTONE))
                        .group("sandstone")
                        .save(output, "smooth_sandstone_wall_from_stonecutting");

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CUT_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SANDSTONE_STAIRS,1)
                        .unlockedBy(getHasName(Blocks.SANDSTONE), has(Blocks.SANDSTONE))
                        .group("sandstone")
                        .save(output, "cut_sandstone_stairs_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SANDSTONE_STAIRS,1)
                        .unlockedBy(getHasName(Blocks.SANDSTONE), has(Blocks.SANDSTONE))
                        .group("sandstone")
                        .save(output, "cut_sandstone_stairs_from_raw_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CUT_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SANDSTONE_WALL,1)
                        .unlockedBy(getHasName(Blocks.SANDSTONE), has(Blocks.SANDSTONE))
                        .group("sandstone")
                        .save(output, "cut_sandstone_wall_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SANDSTONE_WALL,1)
                        .unlockedBy(getHasName(Blocks.SANDSTONE), has(Blocks.SANDSTONE))
                        .group("sandstone")
                        .save(output, "cut_sandstone_wall_from_raw_stonecutting");

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CUT_RED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_RED_SANDSTONE_STAIRS,1)
                        .unlockedBy(getHasName(Blocks.RED_SANDSTONE), has(Blocks.RED_SANDSTONE))
                        .group("red_sandstone")
                        .save(output, "cut_red_sandstone_stairs_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.RED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_RED_SANDSTONE_STAIRS,1)
                        .unlockedBy(getHasName(Blocks.RED_SANDSTONE), has(Blocks.RED_SANDSTONE))
                        .group("red_sandstone")
                        .save(output, "cut_red_sandstone_stairs_from_raw_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.CUT_RED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_RED_SANDSTONE_WALL,1)
                        .unlockedBy(getHasName(Blocks.RED_SANDSTONE), has(Blocks.RED_SANDSTONE))
                        .group("red_sandstone")
                        .save(output, "cut_red_sandstone_wall_from_stonecutting");
                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.RED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_RED_SANDSTONE_WALL,1)
                        .unlockedBy(getHasName(Blocks.RED_SANDSTONE), has(Blocks.RED_SANDSTONE))
                        .group("red_sandstone")
                        .save(output, "cut_red_sandstone_wall_from_raw_stonecutting");

                SingleItemRecipeBuilder.stonecutting(Ingredient.of(Blocks.SMOOTH_RED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_RED_SANDSTONE_WALL,1)
                        .unlockedBy(getHasName(Blocks.RED_SANDSTONE), has(Blocks.RED_SANDSTONE))
                        .group("red_sandstone")
                        .save(output, "smooth_red_sandstone_wall_from_stonecutting");
                }
            };
        };
    }
