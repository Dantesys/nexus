package org.dantesys.nexus.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.dantesys.nexus.data.NexusAttachmentType;
import org.dantesys.nexus.magic.ElementalStatics;
import org.dantesys.nexus.magic.MagicStats;
import org.dantesys.nexus.network.PacketSender;
import org.dantesys.nexus.util.Elementos;

import static org.dantesys.nexus.Nexus.MODID;

@EventBusSubscriber(modid = MODID)
public class NexusEvents {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide() || player.getFoodData().needsFood()) return;
        MagicStats cap = player.getData(NexusAttachmentType.MAGIC_STATS);
        regenMana(player, cap);
    }
    private static void regenMana(Player player, MagicStats cap) {
        long gameTime = player.level().getGameTime();
        if (gameTime % 20 != 0) return;
        boolean mudou = false;
        for (Elementos el : Elementos.values()) {
            ElementalStatics stat = cap.get(el);
            if (stat == null || !stat.isUnlocked()) continue;
            int mana = stat.getMana();
            int max = stat.getMaxMana();
            int regen = stat.getRegeneracao()/20;
            if (mana >= max || regen <= 0) continue;
            int novaMana = Math.min(max, mana + regen);
            if (novaMana != mana) {
                stat.setMana(novaMana);
                mudou = true;
            }
        }
        if (mudou) {
            PacketSender.syncMagicStats((ServerPlayer) player, cap);
        }
    }
}
