package org.dantesys.nexus.util;

import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;

public enum Elementos implements StringRepresentable{

    AGUA("agua", ChatFormatting.BLUE),
    ELETRICO("eletrico", ChatFormatting.YELLOW),
    SOMBRA("sombra", ChatFormatting.DARK_PURPLE),
    FOGO("fogo", ChatFormatting.RED),
    LUZ("luz", ChatFormatting.GOLD),
    NATUREZA("natureza", ChatFormatting.GREEN),
    METAL("metal", ChatFormatting.GRAY),
    ROCHA("rocha", ChatFormatting.DARK_GRAY);

    private final String id;
    private final ChatFormatting cor;

    Elementos(String id, ChatFormatting cor) {
        this.id = id;
        this.cor = cor;
    }

    @Override
    public String getSerializedName() {
        return id;
    }

    public ChatFormatting getCor() {
        return cor;
    }

    public Component getTooltip() {
        return Component.translatable("elemento.nexus." + id)
                .withStyle(cor);
    }

    /** Compatibilidade se você ainda precisar de int */
    public static Elementos fromId(int id) {
        return values()[Math.max(0, Math.min(id, values().length - 1))];
    }

    public static final Codec<Elementos> CODEC =
            StringRepresentable.fromEnum(Elementos::values);

    public static final StreamCodec<RegistryFriendlyByteBuf, Elementos> STREAM_CODEC =
            StreamCodec.of(
                    (buf, elemento) -> buf.writeUtf(elemento.getSerializedName()),
                    buf -> Elementos.valueOf(buf.readUtf().toUpperCase())
            );
}