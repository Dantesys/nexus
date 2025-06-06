package org.dantesys.nexus.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    public static final CreativeModeTab NEXUS_TAB = new CreativeModeTab("nexustab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.GEMA_NEXUS_VAZIA.get());
        }
    };
}
