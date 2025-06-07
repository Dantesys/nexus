package org.dantesys.nexus.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Nexus.MOD_ID);

    public static final RegistryObject<BlockEntityType<ColetorNexusBlockEntity>> COLETOR_NEXUS_BE = BLOCK_ENTITIES.register("coletor_nexus_be",
            () -> BlockEntityType.Builder.of(ColetorNexusBlockEntity::new,ModBlocks.COLETOR.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
