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
    }
    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Nexus.MODID, name));
    }
}
