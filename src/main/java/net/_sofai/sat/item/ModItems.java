package net._sofai.sat.item;

import net._sofai.sat.SofaiSStuffAndTweaks;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Function;

public class ModItems {
    //Items reg. -w-
    public static final Item BELL_PLATE = registerItem("bell_plate", properties -> new Item(properties.fireResistant()));
    public static final Item PIE_BASE = registerItem("pie_base", Item::new);




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

    }
}
