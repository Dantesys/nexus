package org.dantesys.nexus.items;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import static org.dantesys.nexus.Nexus.MODID;

public class NexusItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
