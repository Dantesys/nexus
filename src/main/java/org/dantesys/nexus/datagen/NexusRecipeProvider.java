package org.dantesys.nexus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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
        createEsmeraldaRecipe(recipeOutput, NexusItems.ESMERALDA_ELETRICO.get(), NexusItems.FRAGMENTO_ELETRICO.get(), "eletrico");
        createEsmeraldaRecipe(recipeOutput, NexusItems.ESMERALDA_NATUREZA.get(), NexusItems.FRAGMENTO_NATUREZA.get(), "natureza");
        createEsmeraldaRecipe(recipeOutput, NexusItems.ESMERALDA_ROCHA.get(), NexusItems.FRAGMENTO_ROCHA.get(), "rocha");
        createEsmeraldaRecipe(recipeOutput, NexusItems.ESMERALDA_AGUA.get(), NexusItems.FRAGMENTO_AGUA.get(), "agua");
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
                .unlockedBy("has_aco_block", has(NexusBlocks.ACO_BLOCK)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NexusItems.ACO_PICKAXE.get())
                .pattern("EEE").pattern(" S ").pattern(" S ")
                .define('S',Items.STICK)
                .define('E',NexusItems.ACO.get())
                .unlockedBy("has_aco", has(NexusItems.ACO.get()))
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"aco_pickaxe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NexusItems.ACO_AXE.get())
                .pattern("EE ").pattern("ES ").pattern(" S ")
                .define('S',Items.STICK)
                .define('E',NexusItems.ACO.get())
                .unlockedBy("has_aco", has(NexusItems.ACO.get()))
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"aco_axe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NexusItems.ACO_SHOVEL.get())
                .pattern(" E ").pattern(" S ").pattern(" S ")
                .define('S',Items.STICK)
                .define('E',NexusItems.ACO.get())
                .unlockedBy("has_aco", has(NexusItems.ACO.get()))
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"aco_shovel"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NexusItems.ACO_HOE.get())
                .pattern("EE ").pattern(" S ").pattern(" S ")
                .define('S',Items.STICK)
                .define('E',NexusItems.ACO.get())
                .unlockedBy("has_aco", has(NexusItems.ACO.get()))
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"aco_hoe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NexusItems.ACO_SWORD.get())
                .pattern(" E ").pattern(" E ").pattern(" S ")
                .define('S',Items.STICK)
                .define('E',NexusItems.ACO.get())
                .unlockedBy("has_aco", has(NexusItems.ACO.get()))
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"aco_sword"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NexusItems.ACO_HELMET.get())
                .pattern("EEE").pattern("E E")
                .define('E',NexusItems.ACO.get())
                .unlockedBy("has_aco", has(NexusItems.ACO.get()))
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"aco_helmet"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NexusItems.ACO_CHESTPLATE.get())
                .pattern("E E").pattern("EEE").pattern("EEE")
                .define('E',NexusItems.ACO.get())
                .unlockedBy("has_aco", has(NexusItems.ACO.get()))
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"aco_chestplate"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NexusItems.ACO_LEGGINGS.get())
                .pattern("EEE").pattern("E E").pattern("E E")
                .define('E',NexusItems.ACO.get())
                .unlockedBy("has_aco", has(NexusItems.ACO.get()))
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"aco_leggings"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NexusItems.ACO_BOOTS.get())
                .pattern("E E").pattern("E E")
                .define('E',NexusItems.ACO.get())
                .unlockedBy("has_aco", has(NexusItems.ACO.get()))
                .save(recipeOutput,ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"aco_boots"));
        stairBuilder(NexusBlocks.AGUA_STAIRS.get(), Ingredient.of(NexusBlocks.AGUA_PLANKS.get())).group("agua")
                .unlockedBy("has_agua", has(NexusBlocks.AGUA_PLANKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NexusBlocks.AGUA_SLAB.get(), NexusBlocks.AGUA_PLANKS.get());
        buttonBuilder(NexusBlocks.AGUA_BUTTON.get(), Ingredient.of(NexusBlocks.AGUA_PLANKS.get())).group("agua")
                .unlockedBy("has_agua", has(NexusBlocks.AGUA_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, NexusBlocks.AGUA_PRESSURE_PLATE.get(), NexusBlocks.AGUA_PLANKS.get());
        fenceBuilder(NexusBlocks.AGUA_FENCE.get(), Ingredient.of(NexusBlocks.AGUA_PLANKS.get())).group("agua")
                .unlockedBy("has_agua", has(NexusBlocks.AGUA_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(NexusBlocks.AGUA_FENCE_GATE.get(), Ingredient.of(NexusBlocks.AGUA_PLANKS.get())).group("agua")
                .unlockedBy("has_agua", has(NexusBlocks.AGUA_PLANKS.get())).save(recipeOutput);
        doorBuilder(NexusBlocks.AGUA_DOOR.get(), Ingredient.of(NexusBlocks.AGUA_PLANKS.get())).group("agua")
                .unlockedBy("has_agua", has(NexusBlocks.AGUA_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(NexusBlocks.AGUA_TRAPDOOR.get(), Ingredient.of(NexusBlocks.AGUA_PLANKS.get())).group("agua")
                .unlockedBy("has_agua", has(NexusBlocks.AGUA_PLANKS.get())).save(recipeOutput);
        stairBuilder(NexusBlocks.ELETRICO_STAIRS.get(), Ingredient.of(NexusBlocks.ELETRICO_PLANKS.get())).group("eletrico")
                .unlockedBy("has_eletrico", has(NexusBlocks.ELETRICO_PLANKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NexusBlocks.ELETRICO_SLAB.get(), NexusBlocks.ELETRICO_PLANKS.get());
        buttonBuilder(NexusBlocks.ELETRICO_BUTTON.get(), Ingredient.of(NexusBlocks.ELETRICO_PLANKS.get())).group("eletrico")
                .unlockedBy("has_eletrico", has(NexusBlocks.ELETRICO_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, NexusBlocks.ELETRICO_PRESSURE_PLATE.get(), NexusBlocks.ELETRICO_PLANKS.get());
        fenceBuilder(NexusBlocks.ELETRICO_FENCE.get(), Ingredient.of(NexusBlocks.ELETRICO_PLANKS.get())).group("eletrico")
                .unlockedBy("has_eletrico", has(NexusBlocks.ELETRICO_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(NexusBlocks.ELETRICO_FENCE_GATE.get(), Ingredient.of(NexusBlocks.ELETRICO_PLANKS.get())).group("eletrico")
                .unlockedBy("has_eletrico", has(NexusBlocks.ELETRICO_PLANKS.get())).save(recipeOutput);
        doorBuilder(NexusBlocks.ELETRICO_DOOR.get(), Ingredient.of(NexusBlocks.ELETRICO_PLANKS.get())).group("eletrico")
                .unlockedBy("has_eletrico", has(NexusBlocks.ELETRICO_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(NexusBlocks.ELETRICO_TRAPDOOR.get(), Ingredient.of(NexusBlocks.ELETRICO_PLANKS.get())).group("eletrico")
                .unlockedBy("has_eletrico", has(NexusBlocks.ELETRICO_PLANKS.get())).save(recipeOutput);
        stairBuilder(NexusBlocks.ESCURO_STAIRS.get(), Ingredient.of(NexusBlocks.ESCURO_PLANKS.get())).group("escuro")
                .unlockedBy("has_escuro", has(NexusBlocks.ESCURO_PLANKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NexusBlocks.ESCURO_SLAB.get(), NexusBlocks.ESCURO_PLANKS.get());
        buttonBuilder(NexusBlocks.ESCURO_BUTTON.get(), Ingredient.of(NexusBlocks.ESCURO_PLANKS.get())).group("escuro")
                .unlockedBy("has_escuro", has(NexusBlocks.ESCURO_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, NexusBlocks.ESCURO_PRESSURE_PLATE.get(), NexusBlocks.ESCURO_PLANKS.get());
        fenceBuilder(NexusBlocks.ESCURO_FENCE.get(), Ingredient.of(NexusBlocks.ESCURO_PLANKS.get())).group("escuro")
                .unlockedBy("has_escuro", has(NexusBlocks.ESCURO_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(NexusBlocks.ESCURO_FENCE_GATE.get(), Ingredient.of(NexusBlocks.ESCURO_PLANKS.get())).group("escuro")
                .unlockedBy("has_escuro", has(NexusBlocks.ESCURO_PLANKS.get())).save(recipeOutput);
        doorBuilder(NexusBlocks.ESCURO_DOOR.get(), Ingredient.of(NexusBlocks.ESCURO_PLANKS.get())).group("escuro")
                .unlockedBy("has_escuro", has(NexusBlocks.ESCURO_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(NexusBlocks.ESCURO_TRAPDOOR.get(), Ingredient.of(NexusBlocks.ESCURO_PLANKS.get())).group("escuro")
                .unlockedBy("has_escuro", has(NexusBlocks.ESCURO_PLANKS.get())).save(recipeOutput);
        stairBuilder(NexusBlocks.FOGO_STAIRS.get(), Ingredient.of(NexusBlocks.FOGO_PLANKS.get())).group("fogo")
                .unlockedBy("has_fogo", has(NexusBlocks.FOGO_PLANKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NexusBlocks.FOGO_SLAB.get(), NexusBlocks.FOGO_PLANKS.get());
        buttonBuilder(NexusBlocks.FOGO_BUTTON.get(), Ingredient.of(NexusBlocks.FOGO_PLANKS.get())).group("fogo")
                .unlockedBy("has_fogo", has(NexusBlocks.FOGO_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, NexusBlocks.FOGO_PRESSURE_PLATE.get(), NexusBlocks.FOGO_PLANKS.get());
        fenceBuilder(NexusBlocks.FOGO_FENCE.get(), Ingredient.of(NexusBlocks.FOGO_PLANKS.get())).group("fogo")
                .unlockedBy("has_fogo", has(NexusBlocks.FOGO_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(NexusBlocks.FOGO_FENCE_GATE.get(), Ingredient.of(NexusBlocks.FOGO_PLANKS.get())).group("fogo")
                .unlockedBy("has_fogo", has(NexusBlocks.FOGO_PLANKS.get())).save(recipeOutput);
        doorBuilder(NexusBlocks.FOGO_DOOR.get(), Ingredient.of(NexusBlocks.FOGO_PLANKS.get())).group("fogo")
                .unlockedBy("has_fogo", has(NexusBlocks.FOGO_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(NexusBlocks.FOGO_TRAPDOOR.get(), Ingredient.of(NexusBlocks.FOGO_PLANKS.get())).group("fogo")
                .unlockedBy("has_fogo", has(NexusBlocks.FOGO_PLANKS.get())).save(recipeOutput);
        stairBuilder(NexusBlocks.LUZ_STAIRS.get(), Ingredient.of(NexusBlocks.LUZ_PLANKS.get())).group("luz")
                .unlockedBy("has_luz", has(NexusBlocks.LUZ_PLANKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NexusBlocks.LUZ_SLAB.get(), NexusBlocks.LUZ_PLANKS.get());
        buttonBuilder(NexusBlocks.LUZ_BUTTON.get(), Ingredient.of(NexusBlocks.LUZ_PLANKS.get())).group("luz")
                .unlockedBy("has_luz", has(NexusBlocks.LUZ_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, NexusBlocks.LUZ_PRESSURE_PLATE.get(), NexusBlocks.LUZ_PLANKS.get());
        fenceBuilder(NexusBlocks.LUZ_FENCE.get(), Ingredient.of(NexusBlocks.LUZ_PLANKS.get())).group("luz")
                .unlockedBy("has_luz", has(NexusBlocks.LUZ_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(NexusBlocks.LUZ_FENCE_GATE.get(), Ingredient.of(NexusBlocks.LUZ_PLANKS.get())).group("luz")
                .unlockedBy("has_luz", has(NexusBlocks.LUZ_PLANKS.get())).save(recipeOutput);
        doorBuilder(NexusBlocks.LUZ_DOOR.get(), Ingredient.of(NexusBlocks.LUZ_PLANKS.get())).group("luz")
                .unlockedBy("has_luz", has(NexusBlocks.LUZ_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(NexusBlocks.LUZ_TRAPDOOR.get(), Ingredient.of(NexusBlocks.LUZ_PLANKS.get())).group("luz")
                .unlockedBy("has_luz", has(NexusBlocks.LUZ_PLANKS.get())).save(recipeOutput);
        stairBuilder(NexusBlocks.METAL_STAIRS.get(), Ingredient.of(NexusBlocks.METAL_PLANKS.get())).group("metal")
                .unlockedBy("has_metal", has(NexusBlocks.METAL_PLANKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NexusBlocks.METAL_SLAB.get(), NexusBlocks.METAL_PLANKS.get());
        buttonBuilder(NexusBlocks.METAL_BUTTON.get(), Ingredient.of(NexusBlocks.METAL_PLANKS.get())).group("metal")
                .unlockedBy("has_metal", has(NexusBlocks.METAL_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, NexusBlocks.METAL_PRESSURE_PLATE.get(), NexusBlocks.METAL_PLANKS.get());
        fenceBuilder(NexusBlocks.METAL_FENCE.get(), Ingredient.of(NexusBlocks.METAL_PLANKS.get())).group("metal")
                .unlockedBy("has_metal", has(NexusBlocks.METAL_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(NexusBlocks.METAL_FENCE_GATE.get(), Ingredient.of(NexusBlocks.METAL_PLANKS.get())).group("metal")
                .unlockedBy("has_metal", has(NexusBlocks.METAL_PLANKS.get())).save(recipeOutput);
        doorBuilder(NexusBlocks.METAL_DOOR.get(), Ingredient.of(NexusBlocks.METAL_PLANKS.get())).group("metal")
                .unlockedBy("has_metal", has(NexusBlocks.METAL_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(NexusBlocks.METAL_TRAPDOOR.get(), Ingredient.of(NexusBlocks.METAL_PLANKS.get())).group("metal")
                .unlockedBy("has_metal", has(NexusBlocks.METAL_PLANKS.get())).save(recipeOutput);
        stairBuilder(NexusBlocks.NATUREZA_STAIRS.get(), Ingredient.of(NexusBlocks.NATUREZA_PLANKS.get())).group("natureza")
                .unlockedBy("has_natureza", has(NexusBlocks.NATUREZA_PLANKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NexusBlocks.NATUREZA_SLAB.get(), NexusBlocks.NATUREZA_PLANKS.get());
        buttonBuilder(NexusBlocks.NATUREZA_BUTTON.get(), Ingredient.of(NexusBlocks.NATUREZA_PLANKS.get())).group("natureza")
                .unlockedBy("has_natureza", has(NexusBlocks.NATUREZA_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, NexusBlocks.NATUREZA_PRESSURE_PLATE.get(), NexusBlocks.NATUREZA_PLANKS.get());
        fenceBuilder(NexusBlocks.NATUREZA_FENCE.get(), Ingredient.of(NexusBlocks.NATUREZA_PLANKS.get())).group("natureza")
                .unlockedBy("has_natureza", has(NexusBlocks.NATUREZA_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(NexusBlocks.NATUREZA_FENCE_GATE.get(), Ingredient.of(NexusBlocks.NATUREZA_PLANKS.get())).group("natureza")
                .unlockedBy("has_natureza", has(NexusBlocks.NATUREZA_PLANKS.get())).save(recipeOutput);
        doorBuilder(NexusBlocks.NATUREZA_DOOR.get(), Ingredient.of(NexusBlocks.NATUREZA_PLANKS.get())).group("natureza")
                .unlockedBy("has_natureza", has(NexusBlocks.NATUREZA_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(NexusBlocks.NATUREZA_TRAPDOOR.get(), Ingredient.of(NexusBlocks.NATUREZA_PLANKS.get())).group("natureza")
                .unlockedBy("has_natureza", has(NexusBlocks.NATUREZA_PLANKS.get())).save(recipeOutput);
        stairBuilder(NexusBlocks.ROCHA_STAIRS.get(), Ingredient.of(NexusBlocks.ROCHA_PLANKS.get())).group("rocha")
                .unlockedBy("has_rocha", has(NexusBlocks.ROCHA_PLANKS.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NexusBlocks.ROCHA_SLAB.get(), NexusBlocks.ROCHA_PLANKS.get());
        buttonBuilder(NexusBlocks.ROCHA_BUTTON.get(), Ingredient.of(NexusBlocks.ROCHA_PLANKS.get())).group("rocha")
                .unlockedBy("has_rocha", has(NexusBlocks.ROCHA_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, NexusBlocks.ROCHA_PRESSURE_PLATE.get(), NexusBlocks.ROCHA_PLANKS.get());
        fenceBuilder(NexusBlocks.ROCHA_FENCE.get(), Ingredient.of(NexusBlocks.ROCHA_PLANKS.get())).group("rocha")
                .unlockedBy("has_rocha", has(NexusBlocks.ROCHA_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(NexusBlocks.ROCHA_FENCE_GATE.get(), Ingredient.of(NexusBlocks.ROCHA_PLANKS.get())).group("rocha")
                .unlockedBy("has_rocha", has(NexusBlocks.ROCHA_PLANKS.get())).save(recipeOutput);
        doorBuilder(NexusBlocks.ROCHA_DOOR.get(), Ingredient.of(NexusBlocks.ROCHA_PLANKS.get())).group("rocha")
                .unlockedBy("has_rocha", has(NexusBlocks.ROCHA_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(NexusBlocks.ROCHA_TRAPDOOR.get(), Ingredient.of(NexusBlocks.ROCHA_PLANKS.get())).group("rocha")
                .unlockedBy("has_rocha", has(NexusBlocks.ROCHA_PLANKS.get())).save(recipeOutput);
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
