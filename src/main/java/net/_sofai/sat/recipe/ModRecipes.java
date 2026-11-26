package net._sofai.sat.recipe;

import net._sofai.sat.SofaiSStuffAndTweaks;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
    public static final RecipeSerializer<SewingTableRecipe> SEWING_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, "sewing"),
            new RecipeSerializer<>(SewingTableRecipe.CODEC, SewingTableRecipe.STREAM_CODEC)
    );

    public static final RecipeType<SewingTableRecipe> SEWING_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Identifier.fromNamespaceAndPath(SofaiSStuffAndTweaks.MOD_ID, "sewing"),
            new RecipeType<SewingTableRecipe>() {
                @Override
                public String toString() {
                    return "sewing";
                }
            }
    );

    public static void registerModRecipes() {
        RecipeSynchronization.synchronizeRecipeSerializer(SEWING_SERIALIZER);
        SofaiSStuffAndTweaks.LOGGER.info("Registering ModRecipes for " + SofaiSStuffAndTweaks.MOD_ID);
    }
}
