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
import org.dantesys.nexus.recipe.ExtratorRecipe;
import org.jetbrains.annotations.Nullable;

public class ExtratorRecipeCategory  implements IRecipeCategory<ExtratorRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Nexus.MODID, "extrator");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Nexus.MODID,
            "textures/gui/extrator_gui.png");
    public static final RecipeType<ExtratorRecipe> EXTRATOR_RECIPE_TYPE =
            new RecipeType<>(UID, ExtratorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public ExtratorRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,0 ,0, 176, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(NexusBlocks.EXTRATOR));
    }

    @Override
    public RecipeType<ExtratorRecipe> getRecipeType() {
        return EXTRATOR_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.nexus.extrator");
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
    public void setRecipe(IRecipeLayoutBuilder builder, ExtratorRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 44, 32).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 117, 32).addItemStack(recipe.getResultItem(null));
    }
}