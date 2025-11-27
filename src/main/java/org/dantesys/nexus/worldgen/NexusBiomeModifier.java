package org.dantesys.nexus.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.dantesys.nexus.Nexus;

public class NexusBiomeModifier {

    public static final ResourceKey<BiomeModifier> ADD_TREE_AGUA = registerKey("add_tree_agua");
    public static final ResourceKey<BiomeModifier> ADD_TREE_ELETRICO = registerKey("add_tree_eletrico");
    public static final ResourceKey<BiomeModifier> ADD_TREE_ESCURO = registerKey("add_tree_escuro");
    public static final ResourceKey<BiomeModifier> ADD_TREE_FOGO = registerKey("add_tree_fogo");
    public static final ResourceKey<BiomeModifier> ADD_TREE_LUZ = registerKey("add_tree_luz");
    public static final ResourceKey<BiomeModifier> ADD_TREE_METAL = registerKey("add_tree_metal");
    public static final ResourceKey<BiomeModifier> ADD_TREE_NATUREZA = registerKey("add_tree_natureza");
    public static final ResourceKey<BiomeModifier> ADD_TREE_ROCHA = registerKey("add_tree_rocha");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        // CF -> PF -> BM
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        context.register(ADD_TREE_AGUA, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(NexusBiomes.BIOME_AGUA), biomes.getOrThrow(Biomes.SWAMP)),
                HolderSet.direct(placedFeatures.getOrThrow(NexusPlacedFeatures.AGUA_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_TREE_ELETRICO, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS), biomes.getOrThrow(Biomes.MEADOW)),
                HolderSet.direct(placedFeatures.getOrThrow(NexusPlacedFeatures.ELETRICO_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_TREE_ESCURO, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DARK_FOREST), biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA)),
                HolderSet.direct(placedFeatures.getOrThrow(NexusPlacedFeatures.ESCURO_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_TREE_FOGO, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.BADLANDS), biomes.getOrThrow(Biomes.ERODED_BADLANDS)
                ,biomes.getOrThrow(Biomes.WOODED_BADLANDS),biomes.getOrThrow(Biomes.DESERT),biomes.getOrThrow(Biomes.SPARSE_JUNGLE)),
                HolderSet.direct(placedFeatures.getOrThrow(NexusPlacedFeatures.FOGO_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_TREE_LUZ, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA), biomes.getOrThrow(Biomes.PLAINS)),
                HolderSet.direct(placedFeatures.getOrThrow(NexusPlacedFeatures.LUZ_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_TREE_METAL, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.WINDSWEPT_GRAVELLY_HILLS), biomes.getOrThrow(Biomes.STONY_SHORE)),
                HolderSet.direct(placedFeatures.getOrThrow(NexusPlacedFeatures.METAL_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_TREE_NATUREZA, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE), biomes.getOrThrow(Biomes.FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(NexusPlacedFeatures.NATUREZA_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_TREE_ROCHA, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.STONY_PEAKS), biomes.getOrThrow(Biomes.WINDSWEPT_HILLS)),
                HolderSet.direct(placedFeatures.getOrThrow(NexusPlacedFeatures.ROCHA_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }
    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Nexus.MODID, name));
    }
}
