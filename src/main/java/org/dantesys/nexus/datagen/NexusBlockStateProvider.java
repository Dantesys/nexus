package org.dantesys.nexus.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.dantesys.nexus.blocks.NexusBlocks;

public class NexusBlockStateProvider extends BlockStateProvider {
    public NexusBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModelFile nexusModel = models().withExistingParent("infusor", this.mcLoc("nexus:block/infusor"));
        Block block = NexusBlocks.INFUSOR.get();
        // Shorthand for calling #simpleBlock() (model file overload) and #simpleBlockItem.
        simpleBlockItem(block, nexusModel);
    }
}
