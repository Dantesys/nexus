package org.dantesys.nexus.catalizadores;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.dantesys.nexus.items.NexusItems;
import org.dantesys.nexus.util.ICatalisador;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class CatalisadoresRegistry {
    private static final Map<Item, ICatalisador> MAP = new HashMap<>();

    static {
        MAP.put(Items.COAL, new CatalisadorCarvao());
        MAP.put(Items.NETHERITE_INGOT, new CatalisadorNetherite());
        MAP.put(NexusItems.NUCLEO.get(), new CatalisadorNucleoElemental());
    }
    @Nullable
    public static ICatalisador get(ItemStack stack) {
        return MAP.get(stack.getItem());
    }
}
