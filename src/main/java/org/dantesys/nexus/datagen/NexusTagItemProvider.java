package org.dantesys.nexus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.dantesys.nexus.Nexus;
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
    }
}