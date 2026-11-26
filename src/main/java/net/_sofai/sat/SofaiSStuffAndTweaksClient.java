package net._sofai.sat;

import net._sofai.sat.gui.ModMenuTypes;
import net._sofai.sat.gui.SewingTableScreen;
import net._sofai.sat.recipe.ModRecipes;
import net._sofai.sat.recipe.SewingTableRecipe;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.recipe.v1.sync.ClientRecipeSynchronizedEvent;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Collection;
import java.util.List;

public class SofaiSStuffAndTweaksClient implements ClientModInitializer {
    public static Collection<RecipeHolder<SewingTableRecipe>> syncedSewingRecipes = List.of();

    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.SEWING_TABLE, SewingTableScreen::new);

        ClientRecipeSynchronizedEvent.EVENT.register((client, syncedRecipes) -> {
            syncedSewingRecipes = syncedRecipes.getAllOfType(ModRecipes.SEWING_TYPE);
        });
    }
}
