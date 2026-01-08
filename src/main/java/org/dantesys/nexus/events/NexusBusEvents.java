package org.dantesys.nexus.events;

import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import org.dantesys.nexus.data.NexusAttachmentType;
import org.dantesys.nexus.magic.MagicStats;
import org.dantesys.nexus.network.HabilidadePayload;
import org.dantesys.nexus.network.MagicStatsPayload;
import org.dantesys.nexus.network.PassivaPayload;

import static org.dantesys.nexus.Nexus.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class NexusBusEvents {
    @SubscribeEvent // on the mod event bus
    public static void register(final RegisterPayloadHandlersEvent event) {
        event.registrar(MODID)
                .playToClient(
                        MagicStatsPayload.TYPE,
                        MagicStatsPayload.STREAM_CODEC,
                        (payload, context) -> {
                            Minecraft mc = Minecraft.getInstance();
                            if (mc.player == null) return;
                            mc.execute(() -> {
                                MagicStats clientStats = mc.player.getData(NexusAttachmentType.MAGIC_STATS);
                                clientStats.copyFrom(payload.stats());
                            });
                        }
                );
        event.registrar(MODID).playToServer(
                HabilidadePayload.TYPE,
                HabilidadePayload.STREAM_CODEC,
                HabilidadePayload::handle
        );

        event.registrar(MODID).playToServer(
                PassivaPayload.TYPE,
                PassivaPayload.STREAM_CODEC,
                PassivaPayload::handle
        );
    }
}