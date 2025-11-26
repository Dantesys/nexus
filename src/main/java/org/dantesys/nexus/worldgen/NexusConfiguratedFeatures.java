package org.dantesys.nexus.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.blocks.NexusBlocks;

public class NexusConfiguratedFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> AGUA_KEY = registerKey("agua");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ELETRICO_KEY = registerKey("eletrico");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ESCURO_KEY = registerKey("escuro");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FOGO_KEY = registerKey("fogo");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LUZ_KEY = registerKey("luz");
    public static final ResourceKey<ConfiguredFeature<?, ?>> METAL_KEY = registerKey("metal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NATUREZA_KEY = registerKey("natureza");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCHA_KEY = registerKey("rocha");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, AGUA_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(NexusBlocks.AGUA_LOG.get()),
                new FancyTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(NexusBlocks.AGUA_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());
        register(context, ELETRICO_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(NexusBlocks.ELETRICO_LOG.get()),
                new ForkingTrunkPlacer(5, 2, 5),
                BlockStateProvider.simple(NexusBlocks.ELETRICO_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 1, 2)).build());
        register(context, ESCURO_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(NexusBlocks.ESCURO_LOG.get()),
                new DarkOakTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(NexusBlocks.ESCURO_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());
        register(context, FOGO_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(NexusBlocks.FOGO_LOG.get()),
                new StraightTrunkPlacer(5, 4, 3),
                BlockStateProvider.simple(NexusBlocks.FOGO_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(5), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());
        register(context, LUZ_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(NexusBlocks.LUZ_LOG.get()),
                new GiantTrunkPlacer(10, 5, 9),
                BlockStateProvider.simple(NexusBlocks.LUZ_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(8), ConstantInt.of(9), 9),
                new TwoLayersFeatureSize(1, 0, 2)).build());
        register(context, METAL_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(NexusBlocks.METAL_LOG.get()),
                new BendingTrunkPlacer(4, 4, 3,2,ConstantInt.of(2)),
                BlockStateProvider.simple(NexusBlocks.METAL_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(2, 0, 3)).build());
        register(context, NATUREZA_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(NexusBlocks.NATUREZA_LOG.get()),
                new MegaJungleTrunkPlacer(10, 8, 6),
                BlockStateProvider.simple(NexusBlocks.NATUREZA_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());
        register(context, ROCHA_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(NexusBlocks.ROCHA_LOG.get()),
                new ForkingTrunkPlacer(3, 3, 2),
                BlockStateProvider.simple(NexusBlocks.ROCHA_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Nexus.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
