package net._sofai.sat.block;

import net._sofai.sat.SofaiSStuffAndTweaks;
import net._sofai.sat.item.ModItems;
import net._sofai.sat.worldgen.ModFeature;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {
    //Block reg. -w-
    public static final Block RED_MOSS_BLOCK = registerBlockWithoutBlockItem("red_moss_block",
            properties -> new BonemealableFeaturePlacerBlock(ModFeature.RED_MOSS_PATCH_BONEMEAL,properties.ignitedByLava().mapColor(MapColor.COLOR_RED).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.POPPED)));
    public static final Block RED_MOSS_CARPET = registerBlockWithoutBlockItem("red_moss_carpet",
            properties -> new CarpetBlock(properties.ignitedByLava().mapColor(RED_MOSS_BLOCK.defaultMapColor()).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.POPPED).noOcclusion()));
    public static final Block ORANGE_MOSS_BLOCK = registerBlockWithoutBlockItem("orange_moss_block",
            properties -> new BonemealableFeaturePlacerBlock(ModFeature.ORANGE_MOSS_PATCH_BONEMEAL,properties.ignitedByLava().mapColor(MapColor.COLOR_ORANGE).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.POPPED)));
    public static final Block ORANGE_MOSS_CARPET = registerBlockWithoutBlockItem("orange_moss_carpet",
            properties -> new CarpetBlock(properties.ignitedByLava().mapColor(ORANGE_MOSS_BLOCK.defaultMapColor()).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.POPPED).noOcclusion()));
    public static final Block ORANGE_SHRUB = registerBlockWithoutBlockItem("orange_shrub",
            properties -> new BushBlock(properties.mapColor(MapColor.TERRACOTTA_ORANGE).replaceable().noCollision().instabreak().sound(SoundType.RED_SHRUB).ignitedByLava().pushReaction(PushReaction.POPPED)));
    public static final Block YELLOW_MOSS_BLOCK = registerBlockWithoutBlockItem("yellow_moss_block",
            properties -> new BonemealableFeaturePlacerBlock(ModFeature.YELLOW_MOSS_PATCH_BONEMEAL,properties.ignitedByLava().mapColor(MapColor.COLOR_YELLOW).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.POPPED)));
    public static final Block YELLOW_MOSS_CARPET = registerBlockWithoutBlockItem("yellow_moss_carpet",
            properties -> new CarpetBlock(properties.ignitedByLava().mapColor(YELLOW_MOSS_BLOCK.defaultMapColor()).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.POPPED).noOcclusion()));
    public static final Block YELLOW_SHRUB = registerBlockWithoutBlockItem("yellow_shrub",
            properties -> new BushBlock(properties.mapColor(MapColor.TERRACOTTA_YELLOW).replaceable().noCollision().instabreak().sound(SoundType.RED_SHRUB).ignitedByLava().pushReaction(PushReaction.POPPED)));
    public static final Block LEAF_LITTER_BLOCK = registerBlock("leaf_litter_block",
            properties -> new ColoredFallingBlock(new ColorRGBA(-11715803),properties.ignitedByLava().mapColor(MapColor.PODZOL).noCollision().speedFactor(0.4F).jumpFactor(0.5F).friction(0.8F).fallDistanceReduction(100).strength(0.1F).sound(SoundType.LEAF_LITTER).pushReaction(PushReaction.PUSH).noOcclusion()));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, name)))));
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, name))));
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, name), toRegister);
    }

    public static void registerModBlocks() {
        SofaiSStuffAndTweaks.LOGGER.info("Registering Blocks for " + SofaiSStuffAndTweaks.MOD_ID);
        //tabs and placement
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.insertAfter(Items.WILDFLOWERS, LEAF_LITTER_BLOCK);
        });

    }
}
