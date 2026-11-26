package net._sofai.sat.gui;

import net._sofai.sat.SofaiSStuffAndTweaks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<SewingTableMenu> SEWING_TABLE = Registry.register(
            BuiltInRegistries.MENU,
            Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, "sewing_table"),
            new MenuType<>(SewingTableMenu::new, FeatureFlags.VANILLA_SET)
    );

    public static void registerMenuTypes() {
        SofaiSStuffAndTweaks.LOGGER.info("Registering MenuTypes for " + SofaiSStuffAndTweaks.MOD_ID);
    }
}
