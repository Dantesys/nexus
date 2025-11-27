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

public record ExtratorRecipe(Ingredient inputItem, ItemStack output) implements Recipe<ExtratorRecipeInput> {
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }
    @Override
    public boolean matches(ExtratorRecipeInput input, Level level) {
        if (level.isClientSide()) return false;
        return inputItem.test(input.getItem(0));
    }
    @Override
    public ItemStack assemble(ExtratorRecipeInput infusorRecipeInput, HolderLookup.Provider provider) {
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
        return NexusRecipes.EXTRATOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return NexusRecipes.EXTRATOR_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<ExtratorRecipe> {
        public static final MapCodec<ExtratorRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ExtratorRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(ExtratorRecipe::output)
        ).apply(inst, ExtratorRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ExtratorRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC,ExtratorRecipe::inputItem,
                        ItemStack.STREAM_CODEC, ExtratorRecipe::output,
                        ExtratorRecipe::new);

        @Override
        public MapCodec<ExtratorRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ExtratorRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}