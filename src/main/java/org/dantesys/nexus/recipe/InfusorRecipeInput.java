package org.dantesys.nexus.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public record InfusorRecipeInput(List<ItemStack> input) implements RecipeInput {
    @Override
    public ItemStack getItem(int i) {
        return input.get(i);
    }

    @Override
    public int size() {
        return input.size();
    }
}