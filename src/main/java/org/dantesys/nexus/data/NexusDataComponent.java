package org.dantesys.nexus.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.util.Elementos;

public class NexusDataComponent {
    public static final DeferredRegister<DataComponentType<?>> COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Nexus.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> PASSIVA_ATIVA =
            COMPONENTS.register("passiva_ativa", () ->
                    DataComponentType.<Boolean>builder()
                            .persistent(Codec.BOOL)
                            .networkSynchronized(ByteBufCodecs.BOOL)
                            .build()
            );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> COOLDOWN =
            COMPONENTS.register("cooldown", () ->
                    DataComponentType.<Integer>builder()
                            .persistent(Codec.INT)
                            .networkSynchronized(ByteBufCodecs.INT)
                            .build()
            );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Elementos>> ELEMENTO =
            COMPONENTS.register(
                    "elemento",
                    () -> DataComponentType.<Elementos>builder()
                            .persistent(Elementos.CODEC)
                            .networkSynchronized(Elementos.STREAM_CODEC)
                            .build()
            );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> DANO_BONUS =
            COMPONENTS.register("dano_bonus", () ->
                    DataComponentType.<Float>builder()
                            .persistent(Codec.FLOAT)
                            .networkSynchronized(ByteBufCodecs.FLOAT)
                            .build()
            );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> AFINIDADE =
            COMPONENTS.register("afinidade", () ->
                    DataComponentType.<Integer>builder()
                            .persistent(Codec.INT)
                            .networkSynchronized(ByteBufCodecs.INT)
                            .build()
            );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> DURABILIDADE_BONUS =
            COMPONENTS.register("durabilidade_bonus", () ->
                    DataComponentType.<Integer>builder()
                            .persistent(Codec.INT)
                            .networkSynchronized(ByteBufCodecs.INT)
                            .build()
            );


    public static void register(IEventBus bus) {
        COMPONENTS.register(bus);
    }
}