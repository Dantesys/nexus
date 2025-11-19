package org.dantesys.nexus.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.blocks.NexusBlocks;
import org.dantesys.nexus.recipe.InfusorRecipe;
import org.jetbrains.annotations.Nullable;

public class InfusorRecipeCategory implements IRecipeCategory<InfusorRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Nexus.MODID, "infusor");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Nexus.MODID,
            "textures/gui/infusor_gui.png");
    public static final RecipeType<InfusorRecipe> INFUSOR_RECIPE_TYPE =
            new RecipeType<>(UID, InfusorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public InfusorRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,0 ,0, 176, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(NexusBlocks.INFUSOR));
    }

    @Override
    public RecipeType<InfusorRecipe> getRecipeType() {
        return INFUSOR_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.nexus.infusor");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, InfusorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 44, 16).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 117, 16).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 44, 52).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 117, 52).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 34).addItemStack(recipe.getResultItem(null));
    }
}
