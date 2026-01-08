package org.dantesys.nexus.keys;

import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.dantesys.nexus.Nexus;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Nexus.MODID)
public class NexusKeyBindings {
    public static final KeyMapping ATIVAR_HABILIDADE = new KeyMapping(
            "key.nexus.ativar_habilidade",
            GLFW.GLFW_KEY_R,
            "key.categories.nexus"
    );

    public static final KeyMapping TOGGLE_PASSIVA = new KeyMapping(
            "key.nexus.toggle_passiva",
            GLFW.GLFW_KEY_G,
            "key.categories.nexus"
    );
    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(ATIVAR_HABILIDADE);
        event.register(TOGGLE_PASSIVA);
    }
}
