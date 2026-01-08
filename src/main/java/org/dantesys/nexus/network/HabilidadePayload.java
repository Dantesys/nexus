package org.dantesys.nexus.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.util.IHabilidade;

public record HabilidadePayload() implements CustomPacketPayload {
    public static final Type<HabilidadePayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Nexus.MODID, "usar_habilidade"));

    public static final StreamCodec<FriendlyByteBuf, HabilidadePayload> STREAM_CODEC =
            StreamCodec.unit(new HabilidadePayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(HabilidadePayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) ctx.player();
            ItemStack stack = player.getMainHandItem();
            if (stack.getItem() instanceof IHabilidade habilidade) {
                habilidade.ativar(stack, player.level(), player);
            }
        });
    }
}