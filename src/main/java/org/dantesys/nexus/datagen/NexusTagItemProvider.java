package org.dantesys.nexus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.blocks.NexusBlocks;
import org.dantesys.nexus.items.NexusItems;
import org.dantesys.nexus.util.NexusTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class NexusTagItemProvider extends ItemTagsProvider {
    public NexusTagItemProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Nexus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(NexusTags.Items.NUCLEO_ELEMENTAL).add(NexusItems.NUCLEO.get());
        tag(NexusTags.Items.EMERALD_ELEMENTAL)
                .add(NexusItems.ESMERALDA_AGUA.get())
                .add(NexusItems.ESMERALDA_ELETRICO.get())
                .add(NexusItems.ESMERALDA_ESCURO.get())
                .add(NexusItems.ESMERALDA_FOGO.get())
                .add(NexusItems.ESMERALDA_LUZ.get())
                .add(NexusItems.ESMERALDA_METAL.get())
                .add(NexusItems.ESMERALDA_NATUREZA.get())
                .add(NexusItems.ESMERALDA_ROCHA.get());
        tag(ItemTags.AXES).add(NexusItems.ACO_AXE.get());
        tag(ItemTags.HOES).add(NexusItems.ACO_HOE.get());
        tag(ItemTags.PICKAXES).add(NexusItems.ACO_PICKAXE.get());
        tag(ItemTags.SWORDS).add(NexusItems.ACO_SWORD.get());
        tag(ItemTags.SHOVELS).add(NexusItems.ACO_SHOVEL.get());
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(NexusItems.ACO_HELMET.get())
                .add(NexusItems.ACO_CHESTPLATE.get())
                .add(NexusItems.ACO_LEGGINGS.get())
                .add(NexusItems.ACO_BOOTS.get());
        tag(ItemTags.LOGS_THAT_BURN)
                .add(NexusBlocks.AGUA_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_AGUA_LOG.get().asItem())
                .add(NexusBlocks.ELETRICO_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_ELETRICO_LOG.get().asItem())
                .add(NexusBlocks.ESCURO_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_ESCURO_LOG.get().asItem())
                .add(NexusBlocks.FOGO_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_FOGO_LOG.get().asItem())
                .add(NexusBlocks.LUZ_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_LUZ_LOG.get().asItem())
                .add(NexusBlocks.METAL_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_METAL_LOG.get().asItem())
                .add(NexusBlocks.NATUREZA_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_NATUREZA_LOG.get().asItem())
                .add(NexusBlocks.ROCHA_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_ROCHA_LOG.get().asItem());
        tag(ItemTags.PLANKS)
                .add(NexusBlocks.AGUA_PLANKS.asItem())
                .add(NexusBlocks.ELETRICO_PLANKS.asItem())
                .add(NexusBlocks.ESCURO_PLANKS.asItem())
                .add(NexusBlocks.FOGO_PLANKS.asItem())
                .add(NexusBlocks.LUZ_PLANKS.asItem())
                .add(NexusBlocks.METAL_PLANKS.asItem())
                .add(NexusBlocks.NATUREZA_PLANKS.asItem())
                .add(NexusBlocks.ROCHA_PLANKS.asItem());
        tag(ItemTags.SAPLINGS)
                .add(NexusBlocks.AGUA_SAPLING.asItem())
                .add(NexusBlocks.ELETRICO_SAPLING.asItem())
                .add(NexusBlocks.ESCURO_SAPLING.asItem())
                .add(NexusBlocks.FOGO_SAPLING.asItem())
                .add(NexusBlocks.LUZ_SAPLING.asItem())
                .add(NexusBlocks.METAL_SAPLING.asItem())
                .add(NexusBlocks.NATUREZA_SAPLING.asItem())
                .add(NexusBlocks.ROCHA_SAPLING.asItem());
        tag(NexusTags.Items.AGUA_LOGS)
                .add(NexusBlocks.AGUA_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_AGUA_LOG.get().asItem());
        tag(NexusTags.Items.ELETRICO_LOGS)
                .add(NexusBlocks.ELETRICO_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_ELETRICO_LOG.get().asItem());
        tag(NexusTags.Items.ESCURO_LOGS)
                .add(NexusBlocks.ESCURO_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_ESCURO_LOG.get().asItem());
        tag(NexusTags.Items.FOGO_LOGS)
                .add(NexusBlocks.FOGO_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_FOGO_LOG.get().asItem());
        tag(NexusTags.Items.LUZ_LOGS)
                .add(NexusBlocks.LUZ_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_LUZ_LOG.get().asItem());
        tag(NexusTags.Items.METAL_LOGS)
                .add(NexusBlocks.METAL_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_METAL_LOG.get().asItem());
        tag(NexusTags.Items.NATUREZA_LOGS)
                .add(NexusBlocks.NATUREZA_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_NATUREZA_LOG.get().asItem());
        tag(NexusTags.Items.ROCHA_LOGS)
                .add(NexusBlocks.ROCHA_LOG.get().asItem())
                .add(NexusBlocks.STRIPPED_ROCHA_LOG.get().asItem());
    }
}