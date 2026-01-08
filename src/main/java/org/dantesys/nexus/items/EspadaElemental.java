package org.dantesys.nexus.items;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.dantesys.nexus.data.NexusAttachmentType;
import org.dantesys.nexus.data.NexusDataComponent;
import org.dantesys.nexus.magic.ElementalStatics;
import org.dantesys.nexus.magic.MagicStats;
import org.dantesys.nexus.network.PacketSender;
import org.dantesys.nexus.util.Elementos;
import org.dantesys.nexus.util.RaridadeMolde;

import java.util.List;

public class EspadaElemental extends ReliquiaSW{
    private final int dfCooldown=1200;
    private final int custo=100;
    private final int custopassiva=10;
    private static final double DISTANCIA = 5.0;
    private static final double VELOCIDADE = 0.6;
    private static final float DAMAGE = 1F;
    protected EspadaElemental(Tier tier, Properties properties, Elementos elemento) {
        super(tier,properties, RaridadeMolde.COMUM,elemento);
    }

    @Override
    public void ativar(ItemStack stack, Level level, Player player) {
        if(!emCooldown(stack)){
            Elementos elementos = getElemento(stack);
            MagicStats stats = player.getData(NexusAttachmentType.MAGIC_STATS);
            if(stats.isCreated()){
                ElementalStatics estats = stats.get(elementos);
                if(estats.isUnlocked()){
                    int afinidade = stack.getOrDefault(NexusDataComponent.AFINIDADE,0);
                    int custa = (int) (custo-(custo*0.5*((double) estats.getAfinidade() /1000))-(custo*0.25*((double) afinidade /3)));
                    if(estats.canUse(custa)){
                        estats.use(custa);
                        PacketSender.syncMagicStats((ServerPlayer) player, stats);
                        int cd = (int) (dfCooldown-(dfCooldown*0.5*((double) estats.getSinergia() /1000)));
                        double speed = (VELOCIDADE+(VELOCIDADE*0.5*((double) estats.getAfinidade() /1000)));
                        double dist = (DISTANCIA+(DISTANCIA*0.5*((double) estats.getAfinidade() /1000)));
                        float dmg = (float) (DAMAGE+(DAMAGE*0.5*((double) estats.getAfinidade() /1000))+(DAMAGE*0.25*((double) afinidade /3)));
                        iniciarCooldown(stack, cd);
                        Vec3 look = player.getLookAngle().normalize();
                        Vec3 dash = look.scale(speed);
                        player.setDeltaMovement(dash);
                        player.hurtMarked = true;
                        aplicarCorte(level, player, look, dist, dmg);
                    }
                }
            }
        }
    }

    private void aplicarCorte(Level level, Player player, Vec3 dir, double dist, float dmg) {
        Vec3 inicio = player.position();
        Vec3 fim = inicio.add(dir.scale(dist));
        AABB area = new AABB(inicio, fim).inflate(1.5);
        List<LivingEntity> alvos = level.getEntitiesOfClass(
                LivingEntity.class,
                area,
                e -> e != player
        );
        for (LivingEntity alvo : alvos) {
            Vec3 rel = alvo.position().subtract(player.position()).normalize();
            if (rel.dot(dir) > 0.3) {
                alvo.hurt(
                        level.damageSources().playerAttack(player),
                        dmg
                );
            }
        }
    }

    @Override
    public void passivaTick(ItemStack stack, Level level, LivingEntity entity) {
        if (level.isClientSide) return;

        // 🔒 Se a passiva NÃO está ativa, não faz NADA
        if (!passivaAtiva(stack)) return;

        // 🔁 Só executa lógica pesada a cada 1s
        if (level.getGameTime() % 20 != 0) return;

        Elementos elemento = getElemento(stack);
        MagicStats stats = entity.getData(NexusAttachmentType.MAGIC_STATS);

        if (!stats.isCreated()) {
            desativarPassiva(stack, (Player) entity);
            return;
        }

        ElementalStatics estats = stats.get(elemento);
        if (estats == null || !estats.isUnlocked()) {
            desativarPassiva(stack, (Player) entity);
            return;
        }

        int custo = (int) (custopassiva -
                (custopassiva * 0.5 * ((double) estats.getAfinidade() / 1000)));

        // ❌ SOMENTE AQUI pode desligar por mana
        if (!estats.canUse(custo)) {
            desativarPassiva(stack, (Player) entity);
            return;
        }

        // ✅ Consome mana
        estats.use(custo);

        aplicarEfeitos(entity, elemento);

        PacketSender.syncMagicStats((ServerPlayer) entity, stats);
    }

    private void aplicarEfeitos(LivingEntity entity, Elementos elementos) {
        int duracao = 20*15;
        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duracao, 0, true, false));
        switch (elementos) {
            case LUZ -> entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duracao, 1));
            case AGUA -> entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, duracao));
            case FOGO -> entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duracao));
            case METAL -> entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duracao, 1));
            case ROCHA -> entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duracao));
            case SOMBRA -> entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, duracao));
            case ELETRICO -> entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duracao));
            case NATUREZA -> entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duracao));
        }
    }


    @Override
    public int getCusto(ItemStack stack) {
        return custo;
    }

    private void desativarPassiva(ItemStack stack, Player player) {
        stack.set(NexusDataComponent.PASSIVA_ATIVA.get(), false);

        if (!player.level().isClientSide) {
            player.displayClientMessage(
                    Component.translatable("msg.nexus.passiva_off"),
                    true
            );
        }
    }

    public int getCooldown(ItemStack stack){
        return stack.getOrDefault(
                NexusDataComponent.COOLDOWN.get(),
                dfCooldown
        );
    }
}
