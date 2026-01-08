package org.dantesys.nexus.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IHabilidade {
    void ativar(ItemStack stack, Level level, Player player);
    void passivaTick(ItemStack stack, Level level, LivingEntity entity);
    void alternarPassiva(ItemStack stack, Player player);
    int getCooldown(ItemStack stack);
    int getCusto(ItemStack stack);
}