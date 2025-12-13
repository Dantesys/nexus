package org.dantesys.nexus.items;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.dantesys.nexus.enchantment.NexusEnchants;

public class NucleoItem extends Item {

    public NucleoItem(Properties properties) {
        super(properties);
    }

    // 🔑 LIBERA a mesa de encantamentos
    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    // 🎲 Chance de aparecer encantamento
    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return enchantment.is(NexusEnchants.DESBLOQUEIO_ELEMENTAL) || enchantment.is(NexusEnchants.DISTURBIO_ELEMENTAL);
    }
}

