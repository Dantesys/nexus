package org.dantesys.nexus.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.dantesys.nexus.items.NexusItems;

public class TagElemento {
    public static Item getTagElemento(TagKey<Item> key,Elementos elemento) {
        if(key.equals(NexusTags.Items.ESPADA_ELEMENTAL)){
            return switch (elemento) {
                case AGUA -> NexusItems.AGUA_SWORD.get();
                case ELETRICO -> NexusItems.ELETRICO_SWORD.get();
                case SOMBRA -> NexusItems.ESCURO_SWORD.get();
                case FOGO -> NexusItems.FOGO_SWORD.get();
                case LUZ -> NexusItems.LUZ_SWORD.get();
                case NATUREZA -> NexusItems.NATUREZA_SWORD.get();
                case METAL -> NexusItems.METAL_SWORD.get();
                case ROCHA -> NexusItems.ROCHA_SWORD.get();
            };
        }
        return null;
    }
}
