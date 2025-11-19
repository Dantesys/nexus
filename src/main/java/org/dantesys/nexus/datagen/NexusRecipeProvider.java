package org.dantesys.nexus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.blocks.NexusBlocks;
import org.dantesys.nexus.items.NexusItems;
import org.dantesys.nexus.util.NexusTags;
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NexusBlocks.INFUSOR.get())
                .pattern("EEE").pattern("GOG").pattern("FOF")
                .define('G',Items.GOLD_INGOT)
                .define('F',Items.IRON_INGOT)
                .define('O',Items.OBSIDIAN)
                .define('E', NexusTags.Items.EMERALD_ELEMENTAL)
                .unlockedBy("has_esmerald_elemental", has(NexusTags.Items.EMERALD_ELEMENTAL))
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"infusor"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NexusBlocks.ACO_BLOCK.get())
                .pattern("EEE").pattern("EEE").pattern("EEE")
                .define('E', NexusItems.ACO.get())
                .unlockedBy("has_aco", has(NexusItems.ACO.get()))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, NexusItems.ACO.get(), 9)
                .requires(NexusBlocks.ACO_BLOCK)
                .unlockedBy("has_bismuth_block", has(NexusBlocks.ACO_BLOCK)).save(recipeOutput);
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
