package org.dantesys.nexus.util;

import net.minecraft.world.item.ItemStack;

public interface ICatalisador {
    void aplicar(ItemStack resultado, ForjaContext context);
}