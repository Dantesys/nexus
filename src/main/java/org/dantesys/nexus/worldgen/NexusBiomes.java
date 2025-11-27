package org.dantesys.nexus.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.dantesys.nexus.Nexus;

public class NexusBiomes {
    public static final ResourceKey<Biome> BIOME_AGUA = registerKey("biome_agua");

    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> worldCarvers = context.lookup(Registries.CONFIGURED_CARVER);
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                placedFeatures.getOrThrow(NexusPlacedFeatures.AGUA_PLACED_KEY));

        Biome agua = new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.5F)
                .downfall(0.4F)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .skyColor(0x3d47d8)
                        .fogColor(0x7b82e4)
                        .waterColor(0x008bff)
                        .waterFogColor(0x389cf0)
                        .foliageColorOverride(0x38b9f0)
                        .grassColorOverride(0x00fcf5)
                        .build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.COW,2,1,5)).build())
                .generationSettings(generation.build())
                .build();
        context.register(BIOME_AGUA,agua);
    }
    private static ResourceKey<Biome> registerKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Nexus.MODID, name));
    }
}