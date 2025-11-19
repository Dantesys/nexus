package org.dantesys.nexus.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;

public record InfusorRecipe(List<Ingredient> inputItem, ItemStack output) implements Recipe<InfusorRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.addAll(inputItem);
        return list;
    }

    @Override
    public boolean matches(InfusorRecipeInput input, Level level) {
        if (level.isClientSide()) return false;
        // Cada ingrediente deve usar um slot distinto
        boolean[] used = new boolean[input.size()];
        for (Ingredient ingredient : inputItem) {
            boolean matched = false;
            for (int j = 0; j < input.size(); j++) {
                if (!used[j] && ingredient.test(input.getItem(j))) {
                    used[j] = true;    // reserva o slot
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                return false; // falha se algum ingrediente não encontrou um slot livre correspondente
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(InfusorRecipeInput infusorRecipeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NexusRecipes.INFUSOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return NexusRecipes.INFUSOR_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<InfusorRecipe> {
        public static final MapCodec<InfusorRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").forGetter(InfusorRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(InfusorRecipe::output)
        ).apply(inst, InfusorRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, InfusorRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),InfusorRecipe::inputItem,
                        ItemStack.STREAM_CODEC, InfusorRecipe::output,
                        InfusorRecipe::new);

        @Override
        public MapCodec<InfusorRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, InfusorRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}