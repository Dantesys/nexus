package org.dantesys.nexus.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.util.IHabilidade;

public record PassivaPayload() implements CustomPacketPayload {
    public static final Type<PassivaPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Nexus.MODID, "trocar_passiva"));

    public static final StreamCodec<FriendlyByteBuf, PassivaPayload> STREAM_CODEC =
            StreamCodec.unit(new PassivaPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(PassivaPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) ctx.player();
            ItemStack stack = player.getMainHandItem();
            if (stack.getItem() instanceof IHabilidade habilidade) {
                habilidade.alternarPassiva(stack, player);
            }
        });
    }
}