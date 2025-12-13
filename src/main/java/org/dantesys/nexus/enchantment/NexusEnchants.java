package org.dantesys.nexus.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.util.NexusTags;

public class NexusEnchants {
    public static final ResourceKey<Enchantment> DISTURBIO_ELEMENTAL = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Nexus.MODID, "disturbio_elemental"));
    public static final ResourceKey<Enchantment> DESBLOQUEIO_ELEMENTAL = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Nexus.MODID, "desbloqueio_elemental"));
    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);
        var nucleo = items.getOrThrow(NexusTags.Items.NUCLEO_ELEMENTAL);
        register(context, DISTURBIO_ELEMENTAL, Enchantment.enchantment(
                Enchantment.definition(
                        nucleo,
                        nucleo,
                        5,                      // weight → frequência base
                        10,                     // maxLevel
                        Enchantment.dynamicCost(12, 4),  // minCost
                        Enchantment.dynamicCost(30, 6),  // maxCost
                        2,
                        EquipmentSlotGroup.ANY
                )
        ));
        register(context, DESBLOQUEIO_ELEMENTAL, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(NexusTags.Items.NUCLEO_ELEMENTAL),
                        items.getOrThrow(NexusTags.Items.NUCLEO_ELEMENTAL),
                        1,                     // weight → raríssimo
                        1,                     // maxLevel
                        Enchantment.dynamicCost(45, 0),  // minCost
                        Enchantment.dynamicCost(70, 0),  // maxCost
                        5,
                        EquipmentSlotGroup.ANY
                )
        ));
    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key,
                                 Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}
