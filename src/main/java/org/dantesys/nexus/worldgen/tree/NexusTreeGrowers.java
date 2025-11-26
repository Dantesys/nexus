package org.dantesys.nexus.worldgen.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.worldgen.NexusConfiguratedFeatures;

import java.util.Optional;

public class NexusTreeGrowers {
    public static final TreeGrower AGUA = new TreeGrower(Nexus.MODID + ":agua",
            Optional.empty(), Optional.of(NexusConfiguratedFeatures.AGUA_KEY), Optional.empty());
    public static final TreeGrower ELETRICO = new TreeGrower(Nexus.MODID + ":eletrico",
            Optional.empty(), Optional.of(NexusConfiguratedFeatures.ELETRICO_KEY), Optional.empty());
    public static final TreeGrower ESCURO = new TreeGrower(Nexus.MODID + ":escuro",
            Optional.empty(), Optional.of(NexusConfiguratedFeatures.ESCURO_KEY), Optional.empty());
    public static final TreeGrower FOGO = new TreeGrower(Nexus.MODID + ":fogo",
            Optional.empty(), Optional.of(NexusConfiguratedFeatures.FOGO_KEY), Optional.empty());
    public static final TreeGrower LUZ = new TreeGrower(Nexus.MODID + ":luz",
            Optional.empty(), Optional.of(NexusConfiguratedFeatures.LUZ_KEY), Optional.empty());
    public static final TreeGrower METAL = new TreeGrower(Nexus.MODID + ":metal",
            Optional.empty(), Optional.of(NexusConfiguratedFeatures.METAL_KEY), Optional.empty());
    public static final TreeGrower NATUREZA = new TreeGrower(Nexus.MODID + ":natureza",
            Optional.empty(), Optional.of(NexusConfiguratedFeatures.NATUREZA_KEY), Optional.empty());
    public static final TreeGrower ROCHA = new TreeGrower(Nexus.MODID + ":rocha",
            Optional.empty(), Optional.of(NexusConfiguratedFeatures.ROCHA_KEY), Optional.empty());
}
