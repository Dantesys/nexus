package org.dantesys.nexus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.blocks.NexusBlocks;
import org.dantesys.nexus.util.NexusTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class NexusTagBlockProvider extends BlockTagsProvider {
    public NexusTagBlockProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Nexus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup. Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(NexusBlocks.INFUSOR.get())
                .add(NexusBlocks.EXTRATOR.get())
                .add(NexusBlocks.BOLA_CRISTAL.get());
        tag(NexusTags.Blocks.NEED_ACO)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .add(NexusBlocks.INFUSOR.get())
                .add(NexusBlocks.EXTRATOR.get())
                .add(NexusBlocks.ACO_BLOCK.get())
                .add(NexusBlocks.BOLA_CRISTAL.get())
                .add(NexusBlocks.FORJA.get());
        tag(NexusTags.Blocks.INCORRECT_ACO).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL).remove(NexusTags.Blocks.NEED_ACO);

        tag(BlockTags.FENCES)
                .add(NexusBlocks.AGUA_FENCE.get())
                .add(NexusBlocks.ELETRICO_FENCE.get())
                .add(NexusBlocks.ESCURO_FENCE.get())
                .add(NexusBlocks.FOGO_FENCE.get())
                .add(NexusBlocks.LUZ_FENCE.get())
                .add(NexusBlocks.METAL_FENCE.get())
                .add(NexusBlocks.NATUREZA_FENCE.get())
                .add(NexusBlocks.ROCHA_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(NexusBlocks.AGUA_FENCE_GATE.get())
                .add(NexusBlocks.ELETRICO_FENCE_GATE.get())
                .add(NexusBlocks.ESCURO_FENCE_GATE.get())
                .add(NexusBlocks.FOGO_FENCE_GATE.get())
                .add(NexusBlocks.LUZ_FENCE_GATE.get())
                .add(NexusBlocks.METAL_FENCE_GATE.get())
                .add(NexusBlocks.NATUREZA_FENCE_GATE.get())
                .add(NexusBlocks.ROCHA_FENCE_GATE.get());
        tag(BlockTags.LOGS_THAT_BURN)
                .add(NexusBlocks.AGUA_LOG.get())
                .add(NexusBlocks.STRIPPED_AGUA_LOG.get())
                .add(NexusBlocks.ELETRICO_LOG.get())
                .add(NexusBlocks.STRIPPED_ELETRICO_LOG.get())
                .add(NexusBlocks.ESCURO_LOG.get())
                .add(NexusBlocks.STRIPPED_ESCURO_LOG.get())
                .add(NexusBlocks.FOGO_LOG.get())
                .add(NexusBlocks.STRIPPED_FOGO_LOG.get())
                .add(NexusBlocks.LUZ_LOG.get())
                .add(NexusBlocks.STRIPPED_LUZ_LOG.get())
                .add(NexusBlocks.METAL_LOG.get())
                .add(NexusBlocks.STRIPPED_METAL_LOG.get())
                .add(NexusBlocks.NATUREZA_LOG.get())
                .add(NexusBlocks.STRIPPED_NATUREZA_LOG.get())
                .add(NexusBlocks.ROCHA_LOG.get())
                .add(NexusBlocks.STRIPPED_ROCHA_LOG.get());
    }
}
