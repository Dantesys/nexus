package org.dantesys.nexus.blocks;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import static org.dantesys.nexus.Nexus.MODID;

public class NexusBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static void register(IEventBus bus){
        BLOCKS.register(bus);
    }
}
