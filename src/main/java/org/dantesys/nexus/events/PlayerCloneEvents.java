package org.dantesys.nexus.events;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.dantesys.nexus.data.NexusAttachmentType;
import org.dantesys.nexus.enchantment.NexusEnchants;
import org.dantesys.nexus.magic.MagicStats;
import org.dantesys.nexus.network.PacketSender;

import java.util.List;
import java.util.Map;

import static org.dantesys.nexus.Nexus.MODID;

@EventBusSubscriber(modid = MODID)
public class PlayerCloneEvents {

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;

        var oldP = event.getOriginal();
        var newP = event.getEntity();
        MagicStats oldStats = oldP.getData(NexusAttachmentType.MAGIC_STATS);
        MagicStats newStats = newP.getData(NexusAttachmentType.MAGIC_STATS);

        if (oldStats.isCreated()) {
            newStats.copyFrom(oldStats);
            newStats.setCreated(oldStats.isCreated());
        }

        if (newP instanceof ServerPlayer sp) {
            PacketSender.syncMagicStats(sp, newStats);
        }
    }
    @SubscribeEvent
    public static void addWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();
        Holder<Enchantment> desbloqueio = event.getRegistryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolderOrThrow(NexusEnchants.DESBLOQUEIO_ELEMENTAL);
        ItemStack livro = EnchantedBookItem.createForEnchantment(
                new EnchantmentInstance(desbloqueio, 1));

        rareTrades.add((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 32),
                livro, 4, 100, 0.2f));
    }
}
