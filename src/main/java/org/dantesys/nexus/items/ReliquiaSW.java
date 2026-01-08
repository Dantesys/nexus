package org.dantesys.nexus.items;

import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.dantesys.nexus.data.NexusDataComponent;
import org.dantesys.nexus.util.Elementos;
import org.dantesys.nexus.util.IHabilidade;
import org.dantesys.nexus.util.RaridadeMolde;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public abstract class ReliquiaSW extends SwordItem implements IHabilidade {
    protected final RaridadeMolde raridade;
    protected final Elementos elemento;
    protected ReliquiaSW(Tier tier, Properties properties, RaridadeMolde raridade, Elementos elemento) {
        super(tier,properties.rarity(mapRarity(raridade)).stacksTo(1));
        this.raridade = raridade;
        this.elemento = elemento;
    }
    public RaridadeMolde getRaridade() {
        return raridade;
    }

    public boolean isUnica() {
        return raridade == RaridadeMolde.UNICO;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isUnica() || raridade == RaridadeMolde.LENDARIO;
    }
    public Elementos getElemento(ItemStack stack) {
        return stack.getOrDefault(
                NexusDataComponent.ELEMENTO.get(),
                elemento
        );
    }

    public void setElemento(ItemStack stack) {
        stack.set(NexusDataComponent.ELEMENTO.get(), elemento);
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        float bonus = stack.getOrDefault(
                NexusDataComponent.DANO_BONUS.get(),
                0F
        );

        if (bonus > 0) {
            target.hurt(
                    attacker.damageSources().mobAttack(attacker),
                    bonus
            );
        }

        return super.hurtEnemy(stack, target, attacker);
    }


    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        tooltip.add(Component.translatable(
                "raridade.nexus." + raridade.name().toLowerCase()
        ));
        tooltip.add(getElemento(stack).getTooltip());
        tooltip.add(Component.translatable("tooltip.nexus.passiva"));
        tooltip.add(Component.translatable("tooltip.nexus.ativa"));
    }

    private static Rarity mapRarity(RaridadeMolde raridade) {
        return switch (raridade) {
            case COMUM -> Rarity.COMMON;
            case INCOMUM -> Rarity.UNCOMMON;
            case RARO -> Rarity.RARE;
            case EPICO, MISTICO, LENDARIO, UNICO -> Rarity.EPIC;
        };
    }
    public boolean passivaAtiva(ItemStack stack) {
        return stack.getOrDefault(
                NexusDataComponent.PASSIVA_ATIVA.get(),
                false
        );
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> onBroken) {
        int dur = stack.getOrDefault(NexusDataComponent.DURABILIDADE_BONUS,0);
        if(amount>=dur){
            stack.remove(NexusDataComponent.DURABILIDADE_BONUS);
            amount-=dur;
        }else{
            stack.set(NexusDataComponent.DURABILIDADE_BONUS,dur-amount);
            amount=0;
        }
        return super.damageItem(stack, amount, entity, onBroken);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        int dur = stack.getOrDefault(NexusDataComponent.DURABILIDADE_BONUS,0);
        return super.getMaxDamage(stack)+dur;
    }

    public void alternarPassiva(ItemStack stack, Player player) {
        boolean nova = !passivaAtiva(stack);
        stack.set(NexusDataComponent.PASSIVA_ATIVA.get(), nova);

        if (!player.level().isClientSide) {
            player.displayClientMessage(
                    Component.translatable(
                            nova ? "msg.nexus.passiva_on" : "msg.nexus.passiva_off"
                    ),
                    true
            );
        }
    }


    @Override
    public void inventoryTick(
            ItemStack stack,
            Level level,
            Entity entity,
            int slot,
            boolean selected
    ) {
        if (level.isClientSide) return;

        if(emCooldown(stack))reduzirCooldown(stack);

        if (entity instanceof LivingEntity living && passivaAtiva(stack)) {
            passivaTick(stack, level, living);
        }
    }

    public boolean emCooldown(ItemStack stack) {
        return stack.getOrDefault(
                NexusDataComponent.COOLDOWN.get(),
                0
        ) > 0;
    }

    public void iniciarCooldown(ItemStack stack, int ticks) {
        stack.set(NexusDataComponent.COOLDOWN.get(), ticks);
    }

    public void reduzirCooldown(ItemStack stack) {
        int cd = stack.getOrDefault(
                NexusDataComponent.COOLDOWN.get(),
                0
        );

        if (cd > 0) {
            stack.set(NexusDataComponent.COOLDOWN.get(), cd - 1);
        }
    }
}
