package org.dantesys.nexus.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record InfusorRecipeInput(ItemStack input1,ItemStack input2,ItemStack input3,ItemStack input4) implements RecipeInput {
    @Override
    public ItemStack getItem(int i) {
        return switch (i){
            case 1 -> input2;
            case 2 -> input3;
            case 3 -> input4;
            default -> input1;
        };
    }

    @Override
    public int size() {
        return 4;
    }
}