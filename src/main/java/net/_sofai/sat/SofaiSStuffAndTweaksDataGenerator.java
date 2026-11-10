package net._sofai.sat;

import net._sofai.sat.datagen.ModBlockLootTableProvider;
import net._sofai.sat.datagen.ModModelProvider;
import net._sofai.sat.datagen.ModRecipeProvider;
import net._sofai.sat.worldgen.ModFeature;
import net._sofai.sat.worldgen.ModPlacedFeature;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class SofaiSStuffAndTweaksDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.FEATURE, ModFeature::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeature::bootstrap);
	}
}
