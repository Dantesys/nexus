package org.dantesys.nexus.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.dantesys.nexus.Nexus;

public class NexusBiomes {
    public static final ResourceKey<Biome> AGUA = registerKey("biome_agua");
    public static final ResourceKey<Biome> FOGO = registerKey("biome_fogo");
    public static final ResourceKey<Biome> LUZ = registerKey("biome_luz");
    public static final ResourceKey<Biome> ESCURO = registerKey("biome_escuro");
    public static final ResourceKey<Biome> ROCHA = registerKey("biome_rocha");
    public static final ResourceKey<Biome> METAL = registerKey("biome_metal");
    public static final ResourceKey<Biome> NATUREZA = registerKey("biome_natureza");
    public static final ResourceKey<Biome> ELETRICO = registerKey("biome_eletrico");

    public static void bootstrap(BootstrapContext<Biome> ctx) {
        HolderGetter<PlacedFeature> placed = ctx.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = ctx.lookup(Registries.CONFIGURED_CARVER);
        // ---------- 1) Água (pantano-like, ciano) ----------
        BiomeSpecialEffects aguaEffects = new BiomeSpecialEffects.Builder()
                .fogColor(0x88EFFF) // leve ciano
                .waterColor(0x4FE9FF) // azul cristalino
                .waterFogColor(0x2FBFE0)
                .skyColor(0x7FDFFF)
                .foliageColorOverride(0x33F0E8)
                .grassColorOverride(0x66FFF1)
                .build();

        MobSpawnSettings aguaSpawns = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 8, 2, 4))
                .addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 1, 2))
                .addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SQUID, 8, 1, 3))
                .build();

        BiomeGenerationSettings.Builder aguaGen = new BiomeGenerationSettings.Builder(placed,carvers);
        aguaGen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.AGUA_PLACED_KEY);
        aguaGen.addFeature(GenerationStep.Decoration.LAKES, MiscOverworldPlacements.SPRING_WATER);
        // você pode adicionar ores/lagos conforme quiser

        Biome agua = new Biome.BiomeBuilder()
                .specialEffects(aguaEffects)
                .mobSpawnSettings(aguaSpawns)
                .generationSettings(aguaGen.build())
                .temperature(0.5f)
                .downfall(0.9f)
                .hasPrecipitation(true)
                .build();

        ctx.register(AGUA, agua);

        // ---------- 2) Fogo (badlands-like, avermelhado/cinza) ----------
        BiomeSpecialEffects fogoEffects = new BiomeSpecialEffects.Builder()
                .fogColor(0x6B3A2B)
                .waterColor(0x4A1A00) // não usado muito, mas deixamos escuro
                .waterFogColor(0x331200)
                .skyColor(0xAA5A33)
                .foliageColorOverride(0x7A3F3F)
                .grassColorOverride(0x8A4A3A)
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.FLAME, 0.004f))
                .build();

        MobSpawnSettings fogoSpawns = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 10, 1, 2))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 10,1,2))
                .build();

        BiomeGenerationSettings.Builder fogoGen = new BiomeGenerationSettings.Builder(placed,carvers);
        fogoGen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.FOGO_PLACED_KEY);
        fogoGen.addFeature(GenerationStep.Decoration.LAKES, MiscOverworldPlacements.LAKE_LAVA_SURFACE);
        fogoGen.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, MiscOverworldPlacements.SPRING_LAVA);
        // no Fogo teremos poucas fontes de água - se for necessário, adicione lakes de lava via feature

        Biome fogo = new Biome.BiomeBuilder()
                .specialEffects(fogoEffects)
                .mobSpawnSettings(fogoSpawns)
                .generationSettings(fogoGen.build())
                .temperature(1.2f)
                .downfall(0.0f)
                .hasPrecipitation(false)
                .build();

        ctx.register(FOGO, fogo);

        // ---------- 3) Luz (plains-like, clarinho, pacífico) ----------
        BiomeSpecialEffects luzEffects = new BiomeSpecialEffects.Builder()
                .fogColor(0xF7F9F6)
                .waterColor(0xAEEBFF)
                .waterFogColor(0x7FD0E6)
                .skyColor(0xEAF6FF)
                .grassColorOverride(0xF3FAF6)
                .foliageColorOverride(0xE7F7EE)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .build();

        MobSpawnSettings luzSpawns = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 10, 2, 4))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 8, 2, 4))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 8, 2, 4)).build();
        BiomeGenerationSettings.Builder luzGen = new BiomeGenerationSettings.Builder(placed,carvers);
        luzGen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.LUZ_PLACED_KEY);

        Biome luz = new Biome.BiomeBuilder()
                .specialEffects(luzEffects)
                .mobSpawnSettings(luzSpawns)
                .generationSettings(luzGen.build())
                .temperature(0.6f)
                .downfall(0.4f)
                .hasPrecipitation(true)
                .build();

        ctx.register(LUZ, luz);

        // ---------- 4) Escuro (dark oak forest like, noite densa) ----------
        BiomeSpecialEffects escuroEffects = new BiomeSpecialEffects.Builder()
                .fogColor(0x0A0A0A)
                .waterColor(0x071018)
                .waterFogColor(0x03060A)
                .skyColor(0x0B0B0B)
                .foliageColorOverride(0x0F1A12)
                .grassColorOverride(0x09120F)
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.SMOKE, 0.006f))
                .build();

        MobSpawnSettings escuroSpawns = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 12, 1, 3))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 10,1,2))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 8,1,2))
                .build();

        BiomeGenerationSettings.Builder escuroGen = new BiomeGenerationSettings.Builder(placed,carvers);
        escuroGen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.ESCURO_PLACED_KEY);

        Biome escuro = new Biome.BiomeBuilder()
                .specialEffects(escuroEffects)
                .mobSpawnSettings(escuroSpawns)
                .generationSettings(escuroGen.build())
                .temperature(0.45f)
                .downfall(0.6f)
                .hasPrecipitation(true)
                .build();

        ctx.register(ESCURO, escuro);

        // ---------- 5) Rocha (montanhoso com picos de neve) ----------
        BiomeSpecialEffects rochaEffects = new BiomeSpecialEffects.Builder()
                .fogColor(0xBFB6AD)
                .waterColor(0x6E6E6E)
                .waterFogColor(0x4F4F4F)
                .skyColor(0xA9B0B5)
                .foliageColorOverride(0x8C7D6E)
                .grassColorOverride(0x6F6A5F)
                .build();

        MobSpawnSettings rochaSpawns = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 6,1,2))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 8,1,2))
                .build();

        BiomeGenerationSettings.Builder rochaGen = new BiomeGenerationSettings.Builder(placed,carvers);
        rochaGen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.ROCHA_PLACED_KEY);
        rochaGen.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, NexusPlacedFeatures.ROCHA_PILAR_PLACED_KEY);
        Biome rocha = new Biome.BiomeBuilder()
                .specialEffects(rochaEffects)
                .mobSpawnSettings(rochaSpawns)
                .generationSettings(rochaGen.build())
                .temperature(0.3f)
                .downfall(0.5f)
                .hasPrecipitation(true)
                .build();

        ctx.register(ROCHA, rocha);

        // ---------- 6) Metal (pilares, veias, tom prateado, golems) ----------
        BiomeSpecialEffects metalEffects = new BiomeSpecialEffects.Builder()
                .fogColor(0xC5C9CC)
                .waterColor(0x8FA0A8)
                .waterFogColor(0x6F7B80)
                .skyColor(0xC1C7CB)
                .foliageColorOverride(0xBFC2C6)
                .grassColorOverride(0xBFC3C6)
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.SCRAPE, 0.004f)) // placeholder particle
                .build();

        MobSpawnSettings metalSpawns = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.VILLAGER, 5,1,2))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.PILLAGER, 5,1,2))
                .build();

        BiomeGenerationSettings.Builder metalGen = new BiomeGenerationSettings.Builder(placed,carvers);
        metalGen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.METAL_PLACED_KEY);
        metalGen.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, NexusPlacedFeatures.METAL_PILAR_PLACED_KEY);
        metalGen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NexusPlacedFeatures.METAL_ORES_PLACED_KEY);
        Biome metal = new Biome.BiomeBuilder()
                .specialEffects(metalEffects)
                .mobSpawnSettings(metalSpawns)
                .generationSettings(metalGen.build())
                .temperature(0.4f)
                .downfall(0.2f)
                .hasPrecipitation(false)
                .build();

        ctx.register(METAL, metal);

        // ---------- 7) Natureza (borda de selva, viva) ----------
        BiomeSpecialEffects naturezaEffects = new BiomeSpecialEffects.Builder()
                .fogColor(0x7BE86B)
                .waterColor(0x46D18C)
                .waterFogColor(0x2F9B64)
                .skyColor(0x8FF0A6)
                .foliageColorOverride(0x22FF44)
                .grassColorOverride(0x1EFF43)
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.CHERRY_LEAVES, 0.006f))
                .build();

        MobSpawnSettings naturezaSpawns = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 6,1,2))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 4,1,1))
                .build();

        BiomeGenerationSettings.Builder naturezaGen = new BiomeGenerationSettings.Builder(placed,carvers);
        naturezaGen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.NATUREZA_PLACED_KEY);

        Biome natureza = new Biome.BiomeBuilder()
                .specialEffects(naturezaEffects)
                .mobSpawnSettings(naturezaSpawns)
                .generationSettings(naturezaGen.build())
                .temperature(0.85f)
                .downfall(0.8f)
                .hasPrecipitation(true)
                .build();

        ctx.register(NATUREZA, natureza);

        // ---------- 8) Elétrico (plano / ilhas, amarelado, muita chance de raios) ----------
        BiomeSpecialEffects eletricoEffects = new BiomeSpecialEffects.Builder()
                .fogColor(0xFFF7C4)
                .waterColor(0xE6F7A8)
                .waterFogColor(0xCFEF87)
                .skyColor(0xFFF8D9)
                .foliageColorOverride(0xFFF3A8)
                .grassColorOverride(0xFFE86A)
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.ELECTRIC_SPARK, 0.008f)) // placeholder
                .build();

        MobSpawnSettings eletricoSpawns = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.HORSE, 6,1,2))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.STRAY, 5,1,2))
                .build();

        BiomeGenerationSettings.Builder eletricoGen = new BiomeGenerationSettings.Builder(placed,carvers);
        eletricoGen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NexusPlacedFeatures.ELETRICO_PLACED_KEY);

        Biome eletrico = new Biome.BiomeBuilder()
                .specialEffects(eletricoEffects)
                .mobSpawnSettings(eletricoSpawns)
                .generationSettings(eletricoGen.build())
                .temperature(0.7f)
                .downfall(0.5f)
                .hasPrecipitation(true)
                .build();

        ctx.register(ELETRICO, eletrico);
    }
    private static ResourceKey<Biome> registerKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Nexus.MODID, name));
    }
}