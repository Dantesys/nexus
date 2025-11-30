package org.dantesys.nexus.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.blocks.NexusBlocks;

import java.util.List;

public class NexusPlacedFeatures {

    public static final ResourceKey<PlacedFeature> AGUA_PLACED_KEY = registerKey("agua_placed");
    public static final ResourceKey<PlacedFeature> ELETRICO_PLACED_KEY = registerKey("eletrico_placed");
    public static final ResourceKey<PlacedFeature> ESCURO_PLACED_KEY = registerKey("escuro_placed");
    public static final ResourceKey<PlacedFeature> FOGO_PLACED_KEY = registerKey("fogo_placed");
    public static final ResourceKey<PlacedFeature> LUZ_PLACED_KEY = registerKey("luz_placed");
    public static final ResourceKey<PlacedFeature> METAL_PLACED_KEY = registerKey("metal_placed");
    public static final ResourceKey<PlacedFeature> METAL_PILAR_PLACED_KEY = registerKey("metal_pilar_placed");
    public static final ResourceKey<PlacedFeature> METAL_ORES_PLACED_KEY = registerKey("metal_ores_placed");
    public static final ResourceKey<PlacedFeature> NATUREZA_PLACED_KEY = registerKey("natureza_placed");
    public static final ResourceKey<PlacedFeature> ROCHA_PLACED_KEY = registerKey("rocha_placed");
    public static final ResourceKey<PlacedFeature> ROCHA_PILAR_PLACED_KEY = registerKey("rocha_pilar_placed");
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        registerTree(context, AGUA_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.AGUA_KEY),
                NexusBlocks.AGUA_SAPLING.get());
        registerTree(context, ELETRICO_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.ELETRICO_KEY),
                NexusBlocks.ELETRICO_SAPLING.get());
        registerTree(context, ESCURO_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.ESCURO_KEY),
                NexusBlocks.ESCURO_SAPLING.get());
        registerTree(context, FOGO_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.FOGO_KEY),
                NexusBlocks.FOGO_SAPLING.get());
        registerTree(context, LUZ_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.LUZ_KEY),
                NexusBlocks.LUZ_SAPLING.get());
        registerTree(context, METAL_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.METAL_KEY),
                NexusBlocks.METAL_SAPLING.get());
        registerTree(context, NATUREZA_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.NATUREZA_KEY),
                NexusBlocks.NATUREZA_SAPLING.get());
        registerTree(context, ROCHA_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.ROCHA_KEY),
                NexusBlocks.ROCHA_SAPLING.get());
        registerStone(context,ROCHA_PILAR_PLACED_KEY,configuredFeatures.getOrThrow(NexusConfiguratedFeatures.ROCHA_PILAR_KEY));
        registerStone(context,METAL_PILAR_PLACED_KEY,configuredFeatures.getOrThrow(NexusConfiguratedFeatures.METAL_PILAR_KEY));
        registerMetal(context,METAL_ORES_PLACED_KEY,configuredFeatures.getOrThrow(NexusConfiguratedFeatures.METAL_ORES_KEY));
    }
    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Nexus.MODID, name));
    }
    private static void registerStone(BootstrapContext<PlacedFeature> ctx,
                                      ResourceKey<PlacedFeature> key,
                                      Holder<ConfiguredFeature<?, ?>> configuration){
        List<PlacementModifier> modifiers = List.of(
                CountPlacement.of(3),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                SurfaceWaterDepthFilter.forMaxDepth(0),
                BiomeFilter.biome()
        );

        ctx.register(key, new PlacedFeature(configuration, modifiers));
    }
    private static void registerMetal(BootstrapContext<PlacedFeature> ctx,
                                      ResourceKey<PlacedFeature> key,
                                      Holder<ConfiguredFeature<?, ?>> configuration){
        List<PlacementModifier> modifiers = List.of(
                CountPlacement.of(10),
                InSquarePlacement.spread(),
                PlacementUtils.FULL_RANGE,
                SurfaceWaterDepthFilter.forMaxDepth(0),
                BiomeFilter.biome()
        );

        ctx.register(key, new PlacedFeature(configuration, modifiers));
    }

    private static void registerTree(
            BootstrapContext<PlacedFeature> ctx,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> configuration,
            net.minecraft.world.level.block.Block sapling) {

        List<PlacementModifier> modifiers = List.of(
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome(),
                PlacementUtils.countExtra(2, 0.2f, 1),
                PlacementUtils.filteredByBlockSurvival(sapling),
                BiomeFilter.biome(),
                SurfaceWaterDepthFilter.forMaxDepth(0)
        );

        ctx.register(key, new PlacedFeature(configuration, modifiers));
    }
}
