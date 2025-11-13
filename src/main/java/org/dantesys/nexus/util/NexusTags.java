package org.dantesys.nexus.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.dantesys.nexus.Nexus;

public class NexusTags {
    public static class Items {
        public static final TagKey<Item> EMERALD_ELEMENTAL = createTag("esmeralda_elemental");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Nexus.MODID, name));
        }
    }
}
