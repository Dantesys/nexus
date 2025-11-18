package org.dantesys.nexus.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record InfusorRecipe(Ingredient inputItem1, Ingredient inputItem2,
                            Ingredient inputItem3, Ingredient inputItem4,
                            ItemStack output) implements Recipe<InfusorRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem1);
        list.add(inputItem2);
        list.add(inputItem3);
        list.add(inputItem4);
        return list;
    }

    @Override
    public boolean matches(InfusorRecipeInput infusorRecipeInput, Level level) {
        if (level.isClientSide()) {
            return false;
        }
        boolean check = inputItem1.test(infusorRecipeInput.getItem(0));
        check = check && inputItem2.test(infusorRecipeInput.getItem(1));
        check = check && inputItem3.test(infusorRecipeInput.getItem(3));
        check = check && inputItem4.test(infusorRecipeInput.getItem(4));
        return check;
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
                Ingredient.CODEC_NONEMPTY.fieldOf("input_1").forGetter(InfusorRecipe::inputItem1),
                Ingredient.CODEC_NONEMPTY.fieldOf("input_2").forGetter(InfusorRecipe::inputItem2),
                Ingredient.CODEC_NONEMPTY.fieldOf("input_3").forGetter(InfusorRecipe::inputItem3),
                Ingredient.CODEC_NONEMPTY.fieldOf("input_4").forGetter(InfusorRecipe::inputItem4),
                ItemStack.CODEC.fieldOf("result").forGetter(InfusorRecipe::output)
        ).apply(inst, InfusorRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, InfusorRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, InfusorRecipe::inputItem1,
                        Ingredient.CONTENTS_STREAM_CODEC, InfusorRecipe::inputItem2,
                        Ingredient.CONTENTS_STREAM_CODEC, InfusorRecipe::inputItem3,
                        Ingredient.CONTENTS_STREAM_CODEC, InfusorRecipe::inputItem4,
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