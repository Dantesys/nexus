package org.dantesys.nexus.worldgen;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public class NexusSurfaceRule {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }
    public static SurfaceRules.RuleSource defaltuRule(ResourceKey<Biome> biome) {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(isBiome(biome), SurfaceRules.sequence(
                        SurfaceRules.ifTrue(ON_FLOOR, GRASS_BLOCK),
                        SurfaceRules.ifTrue(UNDER_FLOOR, DIRT))
                ));
    }
    // Utility to register them in TerraBlender region
    public static SurfaceRules.RuleSource registerAll() {
        return sequence(
                defaltuRule(NexusBiomes.AGUA),
                defaltuRule(NexusBiomes.FOGO),
                defaltuRule(NexusBiomes.LUZ),
                defaltuRule(NexusBiomes.ESCURO),
                defaltuRule(NexusBiomes.ROCHA),
                defaltuRule(NexusBiomes.METAL),
                defaltuRule(NexusBiomes.NATUREZA),
                defaltuRule(NexusBiomes.ELETRICO)
        );
    }
}
