package org.dantesys.nexus.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
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
    public static final ResourceKey<PlacedFeature> NATUREZA_PLACED_KEY = registerKey("natureza_placed");
    public static final ResourceKey<PlacedFeature> ROCHA_PLACED_KEY = registerKey("rocha_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, AGUA_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.AGUA_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.5f, 1),
                        NexusBlocks.AGUA_SAPLING.get()));
        register(context, ELETRICO_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.ELETRICO_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.5f, 1),
                        NexusBlocks.ELETRICO_SAPLING.get()));
        register(context, ESCURO_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.ESCURO_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.5f, 1),
                        NexusBlocks.ESCURO_SAPLING.get()));
        register(context, FOGO_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.FOGO_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.5f, 1),
                        NexusBlocks.FOGO_SAPLING.get()));
        register(context, LUZ_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.LUZ_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.5f, 1),
                        NexusBlocks.LUZ_SAPLING.get()));
        register(context, METAL_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.METAL_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.5f, 1),
                        NexusBlocks.METAL_SAPLING.get()));
        register(context, NATUREZA_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.NATUREZA_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.5f, 1),
                        NexusBlocks.NATUREZA_SAPLING.get()));
        register(context, ROCHA_PLACED_KEY, configuredFeatures.getOrThrow(NexusConfiguratedFeatures.ROCHA_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.5f, 1),
                        NexusBlocks.ROCHA_SAPLING.get()));
    }
    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Nexus.MODID, name));
    }
    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
