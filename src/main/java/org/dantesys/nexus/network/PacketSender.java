package org.dantesys.nexus.network;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import org.dantesys.nexus.magic.MagicStats;

public class PacketSender {
    public static void syncMagicStats(ServerPlayer player, MagicStats stats) {
        PacketDistributor.sendToPlayer(player, new MagicStatsPayload(stats));
    }
}
