package org.dantesys.nexus.events;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.keys.NexusKeyBindings;
import org.dantesys.nexus.network.HabilidadePayload;
import org.dantesys.nexus.network.PassivaPayload;

@EventBusSubscriber(modid = Nexus.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NexusClientEvents {
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (Minecraft.getInstance().player == null) return;

        if (NexusKeyBindings.ATIVAR_HABILIDADE.consumeClick()) {
            Minecraft.getInstance().getConnection().send(
                    new HabilidadePayload()
            );
        }
        if (NexusKeyBindings.TOGGLE_PASSIVA.consumeClick()) {
            Minecraft.getInstance().getConnection().send(
                    new PassivaPayload()
            );
        }
    }
}