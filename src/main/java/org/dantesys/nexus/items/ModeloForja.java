package org.dantesys.nexus.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import org.dantesys.nexus.util.RaridadeMolde;

import java.util.List;

public class ModeloForja extends Item {
    private final TagKey<Item> RESULT;
    private final int MAX_CATALIZADOR;
    private final RaridadeMolde raridade;
    private final int aco_cost;
    public ModeloForja(Properties properties,TagKey<Item> result) {
        this(properties,result,0,RaridadeMolde.COMUM,1000);
    }
    public ModeloForja(Properties properties,TagKey<Item> result,int maxCatalador,RaridadeMolde raridade,int cost) {
        super(properties.rarity(mapRarity(raridade)));
        RESULT = result;
        MAX_CATALIZADOR = maxCatalador;
        this.raridade = raridade;
        aco_cost = cost;
    }
    public TagKey<Item> getResult() {
        return this.RESULT;
    }
    public int getMaxCatalador() {
        return this.MAX_CATALIZADOR;
    }
    public boolean isUnique() {
        return raridade == RaridadeMolde.UNICO;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isUnique() || raridade == RaridadeMolde.LENDARIO;
    }
    public int getAco_cost() {
        return aco_cost;
    }
    private String formatNome() {
        return raridade.name().substring(0, 1)
                + raridade.name().substring(1).toLowerCase();
    }
    @Override
    public void appendHoverText(ItemStack stack,
                                TooltipContext context,
                                List<Component> tooltip,
                                TooltipFlag flag) {
        tooltip.add(
                Component.translatable("tooltip.nexus.molde.rarity")
                        .withStyle(ChatFormatting.GRAY)
                        .append(
                                Component.translatable("raridade.nexus." + raridade.name().toLowerCase())
                                        .withStyle(corRaridade(), ChatFormatting.BOLD)
                        )
        );
        if (MAX_CATALIZADOR > 0) {
            tooltip.add(
                    Component.translatable("tooltip.nexus.molde.catalysts")
                            .withStyle(ChatFormatting.GRAY)
                            .append(
                                    Component.literal(String.valueOf(MAX_CATALIZADOR))
                                            .withStyle(ChatFormatting.YELLOW)
                            )
            );
        }
        if (aco_cost > 0) {
            tooltip.add(
                    Component.translatable("tooltip.nexus.molde.aco")
                            .withStyle(ChatFormatting.GRAY)
                            .append(
                                    Component.literal(String.valueOf(aco_cost))
                                            .withStyle(ChatFormatting.YELLOW)
                            )
            );
        }
        if (isUnique()) {
            tooltip.add(
                    Component.translatable("tooltip.nexus.molde.unique")
                            .withStyle(ChatFormatting.RED, ChatFormatting.BOLD)
            );
            tooltip.add(
                    Component.translatable("tooltip.nexus.molde.unique_desc")
                            .withStyle(ChatFormatting.GOLD)
            );
        }
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack stack, net.minecraft.world.entity.player.Player player) {
        return !isUnique();
    }
    @Override
    public boolean canFitInsideContainerItems() {
        return !isUnique();
    }
    private ChatFormatting corRaridade() {
        return switch (raridade) {
            case COMUM -> ChatFormatting.WHITE;
            case INCOMUM -> ChatFormatting.GREEN;
            case RARO -> ChatFormatting.BLUE;
            case EPICO -> ChatFormatting.DARK_PURPLE;
            case MISTICO -> ChatFormatting.AQUA;
            case LENDARIO -> ChatFormatting.GOLD;
            case UNICO -> ChatFormatting.RED;
        };
    }
    private static Rarity mapRarity(RaridadeMolde raridade) {
        return switch (raridade) {
            case COMUM -> Rarity.COMMON;
            case INCOMUM -> Rarity.UNCOMMON;
            case RARO -> Rarity.RARE;
            case EPICO, MISTICO, LENDARIO, UNICO -> Rarity.EPIC;
        };
    }
}
