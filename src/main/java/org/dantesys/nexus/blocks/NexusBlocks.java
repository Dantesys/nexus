package org.dantesys.nexus.blocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.dantesys.nexus.blocks.entity.InfusorBlockEntity;

import java.util.function.Supplier;

import static org.dantesys.nexus.Nexus.MODID;

public class NexusBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE,MODID);

    public static final DeferredBlock<InfusorBlock> INFUSOR = BLOCKS.registerBlock("infusor", InfusorBlock::new,
            BlockBehaviour.Properties.of().explosionResistance(1000f).requiresCorrectToolForDrops().noOcclusion());
    public static final DeferredBlock<Block> ACO_BLOCK = BLOCKS.registerBlock("aco_block", Block::new,
            BlockBehaviour.Properties.of().explosionResistance(1000f).requiresCorrectToolForDrops().noOcclusion());
    public static final Supplier<BlockEntityType<InfusorBlockEntity>> INFUSOR_BE =
            BLOCK_ENTITIES.register("infusor",
                    () -> BlockEntityType.Builder.of(InfusorBlockEntity::new,INFUSOR.get())
                    .build(null));

    public static void register(IEventBus bus){
        BLOCKS.register(bus);
        BLOCK_ENTITIES.register(bus);
    }
}
