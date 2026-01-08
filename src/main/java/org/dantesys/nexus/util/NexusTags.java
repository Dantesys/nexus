package org.dantesys.nexus.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import org.dantesys.nexus.Nexus;

public class NexusTags {
    public static class Blocks{
        public static final TagKey<Block> NEED_ACO = createTag("need_aco");
        public static final TagKey<Block> INCORRECT_ACO = createTag("incorrect_aco");
        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Nexus.MODID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> EMERALD_ELEMENTAL = createTag("esmeralda_elemental");
        public static final TagKey<Item> AGUA_LOGS = createTag("agua_logs");
        public static final TagKey<Item> ELETRICO_LOGS = createTag("eletrico_logs");
        public static final TagKey<Item> ESCURO_LOGS = createTag("escuro_logs");
        public static final TagKey<Item> FOGO_LOGS = createTag("fogo_logs");
        public static final TagKey<Item> LUZ_LOGS = createTag("luz_logs");
        public static final TagKey<Item> METAL_LOGS = createTag("metal_logs");
        public static final TagKey<Item> NATUREZA_LOGS = createTag("natureza_logs");
        public static final TagKey<Item> ROCHA_LOGS = createTag("rocha_logs");
        public static final TagKey<Item> NUCLEO_ELEMENTAL = createTag("nucleo_elemental");
        public static final TagKey<Item> CATALIZADORES = createTag("catalizadores");
        public static final TagKey<Item> MOLDES = createTag("moldes");
        public static final TagKey<Item> ESPADA_ELEMENTAL = createTag("espada_elemental");
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Nexus.MODID, name));
        }
    }
}
