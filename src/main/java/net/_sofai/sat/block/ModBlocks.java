package net._sofai.sat.block;

import net._sofai.sat.SofaiSStuffAndTweaks;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
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
    public static final Block RED_MOSS_BLOCK = registerBlock("red_moss_block",
            properties -> new BonemealableFeaturePlacerBlock(VegetationFeatures.PALE_MOSS_PATCH_BONEMEAL,properties.ignitedByLava().mapColor(MapColor.COLOR_RED).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.POPPED)));
    public static final Block RED_MOSS_CARPET = registerBlock("red_moss_carpet",
            properties -> new CarpetBlock(properties.ignitedByLava().mapColor(RED_MOSS_BLOCK.defaultMapColor()).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.POPPED).noOcclusion()));
    public static final Block ORANGE_MOSS_BLOCK = registerBlock("orange_moss_block",
            properties -> new BonemealableFeaturePlacerBlock(VegetationFeatures.PALE_MOSS_PATCH_BONEMEAL,properties.ignitedByLava().mapColor(MapColor.COLOR_ORANGE).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.POPPED)));
    public static final Block ORANGE_MOSS_CARPET = registerBlock("orange_moss_carpet",
            properties -> new CarpetBlock(properties.ignitedByLava().mapColor(ORANGE_MOSS_BLOCK.defaultMapColor()).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.POPPED).noOcclusion()));
    public static final Block ORANGE_SHRUB = registerBlock("orange_shrub",
            properties -> new BushBlock(properties.mapColor(MapColor.TERRACOTTA_ORANGE).replaceable().noCollision().instabreak().sound(SoundType.RED_SHRUB).ignitedByLava().pushReaction(PushReaction.POPPED)));
    public static final Block YELLOW_MOSS_BLOCK = registerBlock("yellow_moss_block",
            properties -> new BonemealableFeaturePlacerBlock(VegetationFeatures.PALE_MOSS_PATCH_BONEMEAL,properties.ignitedByLava().mapColor(MapColor.COLOR_YELLOW).strength(0.1F).sound(SoundType.MOSS).pushReaction(PushReaction.POPPED)));
    public static final Block YELLOW_MOSS_CARPET = registerBlock("yellow_moss_carpet",
            properties -> new CarpetBlock(properties.ignitedByLava().mapColor(YELLOW_MOSS_BLOCK.defaultMapColor()).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.POPPED).noOcclusion()));
    public static final Block YELLOW_SHRUB = registerBlock("yellow_shrub",
            properties -> new BushBlock(properties.mapColor(MapColor.TERRACOTTA_YELLOW).replaceable().noCollision().instabreak().sound(SoundType.RED_SHRUB).ignitedByLava().pushReaction(PushReaction.POPPED)));

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

    public static void registerModBlocks() {
        SofaiSStuffAndTweaks.LOGGER.info("Registering Blocks for " + SofaiSStuffAndTweaks.MOD_ID);
        //tabs and placement
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.insertAfter(Items.PALE_MOSS_CARPET, RED_MOSS_BLOCK, RED_MOSS_CARPET, ORANGE_MOSS_BLOCK, ORANGE_MOSS_CARPET, YELLOW_MOSS_BLOCK, YELLOW_MOSS_CARPET);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.insertAfter(Items.RED_SHRUB, ORANGE_SHRUB, YELLOW_SHRUB);
        });
    }
}
