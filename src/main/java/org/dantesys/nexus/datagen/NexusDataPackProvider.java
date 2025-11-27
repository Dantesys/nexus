package org.dantesys.nexus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.worldgen.NexusBiomeModifier;
import org.dantesys.nexus.worldgen.NexusBiomes;
import org.dantesys.nexus.worldgen.NexusConfiguratedFeatures;
import org.dantesys.nexus.worldgen.NexusPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class NexusDataPackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, NexusConfiguratedFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, NexusPlacedFeatures::bootstrap)
            .add(Registries.BIOME, NexusBiomes::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, NexusBiomeModifier::bootstrap);

    public NexusDataPackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Nexus.MODID));
    }
}
