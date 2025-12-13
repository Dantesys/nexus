package org.dantesys.nexus.data;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.magic.MagicStats;

import java.util.function.Supplier;

public class NexusAttachmentType {
    // Create the DeferredRegister for attachment types
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Nexus.MODID);

    // Serialization via INBTSerializable
    public static final Supplier<AttachmentType<MagicStats>> MAGIC_STATS = ATTACHMENT_TYPES.register(
            "magic_stats", () -> AttachmentType.serializable(MagicStats::new).build()
    );

    public static void register(IEventBus bus) {
        // In your mod constructor, don't forget to register the DeferredRegister to your mod bus:
        ATTACHMENT_TYPES.register(bus);
    }
}
