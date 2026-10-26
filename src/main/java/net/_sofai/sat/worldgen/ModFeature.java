package net._sofai.sat.worldgen;

import net._sofai.sat.SofaiSStuffAndTweaks;
import net._sofai.sat.block.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.VegetationPatchFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;


public class ModFeature {


    public static final ResourceKey<Feature> RED_MOSS_PATCH_BONEMEAL = FeatureUtils.createKey("red_moss_patch_bonemeal");

    public static final ResourceKey<Feature> RED_MOSS_VEGETATION = FeatureUtils.createKey("red_moss_vegetation");

    public static final ResourceKey<Feature> ORANGE_MOSS_PATCH_BONEMEAL = FeatureUtils.createKey("orange_moss_patch_bonemeal");

    public static final ResourceKey<Feature> ORANGE_MOSS_VEGETATION = FeatureUtils.createKey("orange_moss_vegetation");

    public static final ResourceKey<Feature> YELLOW_MOSS_PATCH_BONEMEAL = FeatureUtils.createKey("yellow_moss_patch_bonemeal");

    public static final ResourceKey<Feature> YELLOW_MOSS_VEGETATION = FeatureUtils.createKey("yellow_moss_vegetation");


    public static void bootstrap(BootstrapContext<Feature> context) {
        var blocks = context.lookup(Registries.BLOCK);
        HolderGetter<Feature> features = context.lookup(Registries.FEATURE);



        context.register(RED_MOSS_VEGETATION, new SimpleBlockFeature(
                BlockStateProvider.holderOf(ModBlocks.RED_MOSS_BLOCK), false
        ));

        context.register(ORANGE_MOSS_VEGETATION, new SimpleBlockFeature(
                BlockStateProvider.holderOf(ModBlocks.ORANGE_MOSS_BLOCK), false
        ));

        context.register(YELLOW_MOSS_VEGETATION, new SimpleBlockFeature(
                BlockStateProvider.holderOf(ModBlocks.YELLOW_MOSS_BLOCK), false
        ));






        context.register(RED_MOSS_PATCH_BONEMEAL, new VegetationPatchFeature(
                        blocks.getOrThrow(BlockTags.MOSS_REPLACEABLE),
                        BlockStateProvider.holderOf(ModBlocks.RED_MOSS_BLOCK),
                        PlacementUtils.inlinePlaced(features.getOrThrow(ModFeature.RED_MOSS_VEGETATION)),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0F,
                        5,
                        0.3F,
                        UniformInt.of(1, 2),
                        0.75F
                )
        );
        context.register(ORANGE_MOSS_PATCH_BONEMEAL, new VegetationPatchFeature(
                        blocks.getOrThrow(BlockTags.MOSS_REPLACEABLE),
                        BlockStateProvider.holderOf(ModBlocks.ORANGE_MOSS_BLOCK),
                PlacementUtils.inlinePlaced(features.getOrThrow(ModFeature.ORANGE_MOSS_VEGETATION)),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0F,
                        5,
                        0.3F,
                        UniformInt.of(1, 2),
                        0.75F
                )
        );
        context.register(YELLOW_MOSS_PATCH_BONEMEAL, new VegetationPatchFeature(
                        blocks.getOrThrow(BlockTags.MOSS_REPLACEABLE),
                        BlockStateProvider.holderOf(ModBlocks.YELLOW_MOSS_BLOCK),
                PlacementUtils.inlinePlaced(features.getOrThrow(ModFeature.YELLOW_MOSS_VEGETATION)),
                        CaveSurface.FLOOR,
                        ConstantInt.of(1),
                        0.0F,
                        5,
                        0.3F,
                        UniformInt.of(1, 2),
                        0.75F
                )
        );
    }
    public static ResourceKey<Feature> registerKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, name));
    }

}
