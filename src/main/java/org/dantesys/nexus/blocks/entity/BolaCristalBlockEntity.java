package org.dantesys.nexus.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.dantesys.nexus.blocks.NexusBlocks;
import org.dantesys.nexus.data.NexusAttachmentType;
import org.dantesys.nexus.enchantment.NexusEnchants;
import org.dantesys.nexus.gui.menu.BolaCristalMenu;
import org.dantesys.nexus.items.NexusItems;
import org.dantesys.nexus.magic.ElementalStatics;
import org.dantesys.nexus.magic.MagicStats;
import org.dantesys.nexus.network.PacketSender;
import org.dantesys.nexus.util.Elementos;
import org.dantesys.nexus.util.Modos;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BolaCristalBlockEntity extends BlockEntity implements MenuProvider {
    public int mana = 0;
    public int regen = 0;
    public int afinidade = 0;
    public int sinergia = 0;
    public final ItemStackHandler inventory = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                updateFromEmerald(level.getNearestPlayer(getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 8, false));
                if(slot==1){
                    Player player = getPlayerUsingThis();
                    if (player != null) {
                        processarNucleo(player);
                    }
                }
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    public BolaCristalBlockEntity(BlockPos pos, BlockState blockState) {
        super(NexusBlocks.BOLA_CRISTAL_BE.get(), pos, blockState);
    }
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
    }
    @Override
    public Component getDisplayName() {
        return Component.translatable("container.nexus.bola_cristal");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new BolaCristalMenu(i,inventory,this);
    }
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }
    public void updateFromEmerald(Player player) {
        if (player == null) {
            mana = regen = afinidade = sinergia = 0;
            return;
        }

        ItemStack stack = inventory.getStackInSlot(0);

        if (stack.isEmpty()) {
            mana = regen = afinidade = sinergia = 0;
            return;
        }

        int id = getElemento();
        if (id == -1) {
            mana = regen = afinidade = sinergia = 0;
            return;
        }

        MagicStats cap = player.getData(NexusAttachmentType.MAGIC_STATS);

        // 🔥 SOMENTE AGORA sincroniza
        PacketSender.syncMagicStats((ServerPlayer) player, cap);

        var elemento = Elementos.getId(id);
        var stat = cap.get(elemento);

        if (stat != null && stat.isUnlocked()) {
            mana = stat.getMaxMana();
            regen = stat.getRegeneracao();
            afinidade = stat.getAfinidade();
            sinergia = stat.getSinergia();
        } else {
            mana = regen = afinidade = sinergia = 0;
        }
    }

    public int getElemento(){
        ItemStack stack = inventory.getStackInSlot(0);
        return stack.is(NexusItems.ESMERALDA_AGUA.get()) ? 0 :
                stack.is(NexusItems.ESMERALDA_ELETRICO.get()) ? 1 :
                        stack.is(NexusItems.ESMERALDA_ESCURO.get()) ? 2 :
                                stack.is(NexusItems.ESMERALDA_FOGO.get()) ? 3 :
                                        stack.is(NexusItems.ESMERALDA_LUZ.get()) ? 4 :
                                                stack.is(NexusItems.ESMERALDA_METAL.get()) ? 5 :
                                                        stack.is(NexusItems.ESMERALDA_NATUREZA.get()) ? 6 :
                                                                stack.is(NexusItems.ESMERALDA_ROCHA.get()) ? 7 : -1;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;
        // Verifica se algum jogador está com o menu aberto para esse bloco
        if (hasOpenContainer()) {
            Player player = getPlayerUsingThis();
            if (player != null) {
                updateFromEmerald(player);
                syncMenu();
            }
        }
    }
    private boolean aplicarDesbloqueio(ElementalStatics stat) {
        if (stat.isUnlocked()) return false;
        Random r = new Random();
        stat.setUnlocked(true);
        stat.setMaxMana(r.nextInt(1, 1001));
        stat.setAfinidade(r.nextInt(1, 1001));
        stat.setRegeneracao(r.nextInt(1, 1001));
        stat.setSinergia(r.nextInt(1, 1001));
        return true;
    }

    private void processarNucleo(Player player) {
        if (level == null || level.isClientSide()) return;

        ItemStack nucleo = inventory.getStackInSlot(1);
        if (nucleo.isEmpty()) return;

        int id = getElemento();
        if (id == -1) return;

        MagicStats cap = player.getData(NexusAttachmentType.MAGIC_STATS);
        var stat = cap.get(Elementos.getId(id));
        if (stat == null) return;

        boolean aplicou = false;

        int desbloqueio = getNucleoEnchantLevel(NexusEnchants.DESBLOQUEIO_ELEMENTAL);
        int disturbio  = getNucleoEnchantLevel(NexusEnchants.DISTURBIO_ELEMENTAL);

        if (desbloqueio > 0) {
            aplicou = aplicarDesbloqueio(stat);
        }

        if (disturbio > 0) {
            aplicou = aplicarDisturbio(stat, disturbio);
        }

        if (aplicou) {
            inventory.extractItem(1, 1, false); // 🔥 CONSOME O NÚCLEO
            PacketSender.syncMagicStats((ServerPlayer) player, cap);
            setChanged();
        }
    }
    private boolean aplicarDisturbio(ElementalStatics stat, int nivel) {
        if (!stat.isUnlocked()) return false;

        Random r = new Random();

        // 🔒 Chance de bloquear
        float chanceBloqueio = Math.max(0f, 0.45f - (nivel - 1) * 0.05f);
        if (nivel < 10 && r.nextFloat() < chanceBloqueio) {
            stat.setUnlocked(false);
            stat.setMaxMana(0);
            stat.setAfinidade(0);
            stat.setRegeneracao(0);
            stat.setSinergia(0);
            return true;
        }

        stat.setMaxMana(evoluirStatus(stat.getMaxMana(), nivel, r));
        stat.setAfinidade(evoluirStatus(stat.getAfinidade(), nivel, r));
        stat.setRegeneracao(evoluirStatus(stat.getRegeneracao(), nivel, r));
        stat.setSinergia(evoluirStatus(stat.getSinergia(), nivel, r));

        return true;
    }
    private int evoluirStatus(int atual, int nivel, Random r) {
        if (atual < 170) {
            int min = (int)(765f * (nivel / 10f));
            return r.nextInt(min, 1001);
        }

        int ganhoMax = (int)((1000 - atual) * (nivel / 10f));
        if (ganhoMax <= 0) return atual;

        return Math.min(1000, atual + r.nextInt(1, ganhoMax + 1));
    }

    public boolean hasOpenContainer() {
        if (level == null || level.players().isEmpty()) return false;

        for (Player p : level.players()) {
            if (p.containerMenu instanceof BolaCristalMenu menu &&
                    menu.getBlockEntity() == this) {
                return true;
            }
        }
        return false;
    }

    public Player getPlayerUsingThis() {
        if (level == null) return null;
        for (Player p : level.players()) {
            if (p.containerMenu instanceof BolaCristalMenu menu &&
                    menu.getBlockEntity() == this) {
                return p;
            }
        }
        return null;
    }
    public void syncMenu() {
        if (level == null) return;

        for (Player p : level.players()) {
            if (p.containerMenu instanceof BolaCristalMenu menu &&
                    menu.getBlockEntity() == this) {

                menu.setStats(mana, regen, afinidade, sinergia);
                menu.broadcastChanges();
            }
        }
    }
    public int getNucleoEnchantLevel(ResourceKey<Enchantment> enchant) {
        ItemStack stack = inventory.getStackInSlot(1);
        if (stack.isEmpty()) return 0;

        return stack.getEnchantmentLevel(
                level.registryAccess()
                        .registryOrThrow(Registries.ENCHANTMENT)
                        .getHolderOrThrow(enchant)
        );
    }
    public Modos getMode() {
        if (getNucleoEnchantLevel(NexusEnchants.DESBLOQUEIO_ELEMENTAL) > 0)
            return Modos.DESBLOQUEIO;

        if (getNucleoEnchantLevel(NexusEnchants.DISTURBIO_ELEMENTAL) > 0)
            return Modos.DISTURBIO;

        return Modos.NORMAL;
    }

}
