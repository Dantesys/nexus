package org.dantesys.nexus.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.magic.MagicStats;

public record MagicStatsPayload(MagicStats stats) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<MagicStatsPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Nexus.MODID, "magic_stats"));

    public static final StreamCodec<ByteBuf, MagicStatsPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.COMPOUND_TAG,                  // 1) Como ler/escrever
                    payload -> payload.stats().toNBT(),
                    tag -> new MagicStatsPayload(
                            MagicStats.fromNBT(tag)
                    )
            );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
