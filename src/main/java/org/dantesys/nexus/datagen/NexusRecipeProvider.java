package org.dantesys.nexus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.items.NexusItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class NexusRecipeProvider extends RecipeProvider {
    public NexusRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        createEsmeraldaRecipe(recipeOutput, NexusItems.ESMERALDA_FOGO.get(), NexusItems.FRAGMENTO_FOGO.get(), "fogo");
        createEsmeraldaRecipe(recipeOutput, NexusItems.ESMERALDA_AGUA.get(), NexusItems.FRAGMENTO_AGUA.get(), "agua");
        createEsmeraldaRecipe(recipeOutput, NexusItems.ESMERALDA_NATUREZA.get(), NexusItems.FRAGMENTO_NATUREZA.get(), "natureza");
        createEsmeraldaRecipe(recipeOutput, NexusItems.ESMERALDA_ROCHA.get(), NexusItems.FRAGMENTO_ROCHA.get(), "rocha");
        createEsmeraldaRecipe(recipeOutput, NexusItems.ESMERALDA_ELETRICO.get(), NexusItems.FRAGMENTO_ELETRICO.get(), "eletrico");
        createEsmeraldaRecipe(recipeOutput, NexusItems.ESMERALDA_METAL.get(), NexusItems.FRAGMENTO_METAL.get(), "metal");
        createEsmeraldaRecipe(recipeOutput, NexusItems.ESMERALDA_LUZ.get(), NexusItems.FRAGMENTO_LUZ.get(), "luz");
        createEsmeraldaRecipe(recipeOutput, NexusItems.ESMERALDA_ESCURO.get(), NexusItems.FRAGMENTO_ESCURO.get(), "escuro");
    }

    private void createEsmeraldaRecipe(RecipeOutput recipeOutput, Item esmeralda, Item fragmento, String nome) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, esmeralda)
                .pattern(" F ")
                .pattern("FEF")
                .pattern(" F ")
                .define('F', fragmento)
                .define('E', Items.EMERALD)
                .unlockedBy("has_" + nome + "_fragmento", has(fragmento))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Nexus.MODID, "esmeralda_" + nome));
    }
}
