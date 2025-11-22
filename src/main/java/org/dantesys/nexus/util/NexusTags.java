package org.dantesys.nexus.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
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

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Nexus.MODID, name));
        }
    }
}
