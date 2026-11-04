package net._sofai.sat.item;

import net._sofai.sat.SofaiSStuffAndTweaks;
import net._sofai.sat.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.ArrayList;
import java.util.function.Function;

public class ModItems {
    //Items reg. -w-
    public static final Item A_BIT_OF_MOSS = registerItem("a_bit_of_moss", properties -> new Item(properties.food(new FoodProperties(0, 1, true)).compostable(ContextIntProviders.COMPOSTABLE_LOW)));
    public static final Item A_BIT_OF_RED_MOSS = registerItem("a_bit_of_red_moss", properties -> new Item(properties.food(new FoodProperties(0, 1, true)).compostable(ContextIntProviders.COMPOSTABLE_LOW)));
    public static final Item A_BIT_OF_ORANGE_MOSS = registerItem("a_bit_of_orange_moss", properties -> new Item(properties.food(new FoodProperties(0, 1, true)).compostable(ContextIntProviders.COMPOSTABLE_LOW)));
    public static final Item A_BIT_OF_YELLOW_MOSS = registerItem("a_bit_of_yellow_moss", properties -> new Item(properties.food(new FoodProperties(0, 1, true)).compostable(ContextIntProviders.COMPOSTABLE_LOW)));


    //Compostable
    public static final Item RED_MOSS_BLOCK = registerItem("red_moss_block",
            properties -> new BlockItem(ModBlocks.RED_MOSS_BLOCK, properties.compostable(ContextIntProviders.COMPOSTABLE_MEDIUM)));
    public static final Item RED_MOSS_CARPET = registerItem("red_moss_carpet",
            properties -> new BlockItem(ModBlocks.RED_MOSS_CARPET, properties.compostable(ContextIntProviders.COMPOSTABLE_LOW)));
    public static final Item ORANGE_MOSS_BLOCK = registerItem("orange_moss_block",
            properties -> new BlockItem(ModBlocks.ORANGE_MOSS_BLOCK, properties.compostable(ContextIntProviders.COMPOSTABLE_MEDIUM)));
    public static final Item ORANGE_MOSS_CARPET = registerItem("orange_moss_carpet",
            properties -> new BlockItem(ModBlocks.ORANGE_MOSS_CARPET, properties.compostable(ContextIntProviders.COMPOSTABLE_LOW)));
    public static final Item ORANGE_SHRUB = registerItem("orange_shrub",
            properties -> new BlockItem(ModBlocks.ORANGE_SHRUB, properties.compostable(ContextIntProviders.COMPOSTABLE_LOW)));
    public static final Item YELLOW_MOSS_BLOCK = registerItem("yellow_moss_block",
            properties -> new BlockItem(ModBlocks.YELLOW_MOSS_BLOCK, properties.compostable(ContextIntProviders.COMPOSTABLE_MEDIUM)));
    public static final Item YELLOW_MOSS_CARPET = registerItem("yellow_moss_carpet",
            properties -> new BlockItem(ModBlocks.YELLOW_MOSS_CARPET, properties.compostable(ContextIntProviders.COMPOSTABLE_LOW_MEDIUM)));
    public static final Item YELLOW_SHRUB = registerItem("yellow_shrub",
            properties -> new BlockItem(ModBlocks.YELLOW_SHRUB, properties.compostable(ContextIntProviders.COMPOSTABLE_LOW)));




    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, name)))));
    }

    public static void registerModItems() {
        SofaiSStuffAndTweaks.LOGGER.info("Registering Items for " + SofaiSStuffAndTweaks.MOD_ID);
//tabs and placement
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.insertAfter(net.minecraft.world.item.Items.WHEAT, A_BIT_OF_MOSS, A_BIT_OF_RED_MOSS, A_BIT_OF_ORANGE_MOSS, A_BIT_OF_YELLOW_MOSS);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.insertAfter(Items.PALE_MOSS_CARPET, RED_MOSS_BLOCK, RED_MOSS_CARPET, ORANGE_MOSS_BLOCK, ORANGE_MOSS_CARPET, YELLOW_MOSS_BLOCK, YELLOW_MOSS_CARPET);
            output.insertAfter(Items.RED_SHRUB, ORANGE_SHRUB, YELLOW_SHRUB);
        });
    }
}
