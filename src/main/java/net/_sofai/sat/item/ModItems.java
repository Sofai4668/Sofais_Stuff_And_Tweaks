package net._sofai.sat.item;

import net._sofai.sat.SofaiSStuffAndTweaks;
import net._sofai.sat.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.function.Function;

public class ModItems {
    //Items reg. -w-
    public static final Item BELL_PLATE = registerItem("bell_plate", properties -> new Item(properties.fireResistant()));
    public static final Item PIE_BASE = registerItem("pie_base", Item::new);


    //Compostable
    public static final Item RED_MOSS_BLOCK = registerItem("red_moss_block",
            properties -> new BlockItem(ModBlocks.RED_MOSS_BLOCK, properties.compostable(ContextIntProviders.COMPOSTABLE_MEDIUM)));
    public static final Item RED_MOSS_CARPET = registerItem("red_moss_carpet",
            properties -> new BlockItem(ModBlocks.RED_MOSS_CARPET, properties.compostable(ContextIntProviders.COMPOSTABLE_LOW_MEDIUM)));
    public static final Item ORANGE_MOSS_BLOCK = registerItem("orange_moss_block",
            properties -> new BlockItem(ModBlocks.ORANGE_MOSS_BLOCK, properties.compostable(ContextIntProviders.COMPOSTABLE_MEDIUM)));
    public static final Item ORANGE_MOSS_CARPET = registerItem("orange_moss_carpet",
            properties -> new BlockItem(ModBlocks.ORANGE_MOSS_CARPET, properties.compostable(ContextIntProviders.COMPOSTABLE_LOW_MEDIUM)));
    public static final Item ORANGE_SHRUB = registerItem("orange_shrub",
            properties -> new BlockItem(ModBlocks.ORANGE_SHRUB, properties.compostable(ContextIntProviders.COMPOSTABLE_LOW_MEDIUM)));
    public static final Item YELLOW_MOSS_BLOCK = registerItem("yellow_moss_block",
            properties -> new BlockItem(ModBlocks.YELLOW_MOSS_BLOCK, properties.compostable(ContextIntProviders.COMPOSTABLE_MEDIUM)));
    public static final Item YELLOW_MOSS_CARPET = registerItem("yellow_moss_carpet",
            properties -> new BlockItem(ModBlocks.YELLOW_MOSS_CARPET, properties.compostable(ContextIntProviders.COMPOSTABLE_LOW_MEDIUM)));
    public static final Item YELLOW_SHRUB = registerItem("yellow_shrub",
            properties -> new BlockItem(ModBlocks.YELLOW_SHRUB, properties.compostable(ContextIntProviders.COMPOSTABLE_LOW_MEDIUM)));




    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, name)))));
    }

    public static void registerModItems() {
        SofaiSStuffAndTweaks.LOGGER.info("Registering Items for " + SofaiSStuffAndTweaks.MOD_ID);
//tabs and placement
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.insertAfter(Items.NETHERITE_SCRAP, BELL_PLATE);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.insertAfter(net.minecraft.world.item.Items.WHEAT, PIE_BASE);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.insertAfter(Items.PALE_MOSS_CARPET, RED_MOSS_BLOCK, RED_MOSS_CARPET, ORANGE_MOSS_BLOCK, ORANGE_MOSS_CARPET, YELLOW_MOSS_BLOCK, YELLOW_MOSS_CARPET);
            output.insertAfter(Items.RED_SHRUB, ORANGE_SHRUB, YELLOW_SHRUB);
        });
    }
}
