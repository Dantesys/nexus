package org.dantesys.nexus.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.blocks.NexusBlocks;
import org.dantesys.nexus.gui.screen.ExtratorScreen;
import org.dantesys.nexus.gui.screen.InfusorScreen;
import org.dantesys.nexus.recipe.ExtratorRecipe;
import org.dantesys.nexus.recipe.InfusorRecipe;
import org.dantesys.nexus.recipe.NexusRecipes;

import java.util.List;

@JeiPlugin
public class JEINexusPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Nexus.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new InfusorRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ExtratorRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<InfusorRecipe> infusorRecipes = recipeManager
                .getAllRecipesFor(NexusRecipes.INFUSOR_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(InfusorRecipeCategory.INFUSOR_RECIPE_TYPE, infusorRecipes);
        List<ExtratorRecipe> extratorRecipes = recipeManager
                .getAllRecipesFor(NexusRecipes.EXTRATOR_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(ExtratorRecipeCategory.EXTRATOR_RECIPE_TYPE, extratorRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(InfusorScreen.class, 74, 45, 22, 20,
                InfusorRecipeCategory.INFUSOR_RECIPE_TYPE);
        registration.addRecipeClickArea(ExtratorScreen.class, 74, 45, 22, 20,
                ExtratorRecipeCategory.EXTRATOR_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(NexusBlocks.INFUSOR.get().asItem()),
                InfusorRecipeCategory.INFUSOR_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(NexusBlocks.EXTRATOR.get().asItem()),
                ExtratorRecipeCategory.EXTRATOR_RECIPE_TYPE);
    }
}
