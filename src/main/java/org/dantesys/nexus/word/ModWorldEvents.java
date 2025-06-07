package org.dantesys.nexus.word;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.word.gen.ModOreGeneration;

@Mod.EventBusSubscriber(modid = Nexus.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModOreGeneration.generateOres(event);
    }
}
