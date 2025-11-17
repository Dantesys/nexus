package org.dantesys.nexus.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.dantesys.nexus.Config;
import org.dantesys.nexus.gui.menu.InfusorMenu;
import org.jetbrains.annotations.Nullable;

import static org.dantesys.nexus.blocks.NexusBlocks.INFUSOR_BE;

public class InfusorBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler inventory = new ItemStackHandler(5) {
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 1;
        }
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    private int progress = 0;
    private boolean success = false;
    public InfusorBlockEntity(BlockPos pos, BlockState blockState) {
        super(INFUSOR_BE.get(), pos, blockState);
    }
    public int getProgress(){
        return this.progress;
    }
    public void setProgress(int p){
        this.progress=p;
    }
    public int getMaxProgress(){
        return 100;
    }
    public boolean getSuccess(){
        return this.success;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putInt("progresso",progress);
        tag.putBoolean("sucesso",success);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        this.progress=tag.getInt("progresso");
        this.success=tag.getBoolean("sucesso");
    }


    @Override
    public Component getDisplayName() {
        return Component.translatable("container.nexus.infusor");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new InfusorMenu(i,inventory,this);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
    public double getCurrentChance() {
        java.util.Map<Item, Integer> freq = new java.util.HashMap<>();
        for (int i = 0; i < 4; i++) {
            ItemStack s = this.inventory.getStackInSlot(i);
            if (!s.isEmpty()) {
                Item it = s.getItem();
                freq.put(it, freq.getOrDefault(it, 0) + 1);
            }
        }

        int maxGroup = 0;
        for (int v : freq.values()) if (v > maxGroup) maxGroup = v;

        if (maxGroup == 4) return 1.0; // garantia

        double base = Config.COMMON.baseChance.get();
        double per = Config.COMMON.perMatchIncrease.get();

        double calc = base + (Math.max(0, maxGroup - 1) * per);
        return Math.max(0.0, Math.min(1.0, calc));
    }
    private boolean canStartInfuse() {
        for (int i = 0; i < 4; i++) {
            if (this.inventory.getStackInSlot(i).isEmpty()) return false;
        }
        return this.inventory.getStackInSlot(4).isEmpty();
    }
    private void markDirtyAndSync() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    private void attemptComplete() {
        if (level == null || level.isClientSide) return;

        // conta frequência por Item (slots 0..3)
        java.util.Map<net.minecraft.world.item.Item, Integer> freq = new java.util.HashMap<>();
        for (int i = 0; i < 4; i++) {
            net.minecraft.world.item.ItemStack s = this.inventory.getStackInSlot(i);
            if (!s.isEmpty()) {
                net.minecraft.world.item.Item it = s.getItem();
                freq.put(it, freq.getOrDefault(it, 0) + 1);
            }
        }

        // se não houver itens suficientes, cancela
        if (freq.isEmpty()) {
            this.progress = 0;
            markDirtyAndSync();
            return;
        }

        int maxGroup = 0;
        for (int v : freq.values()) if (v > maxGroup) maxGroup = v;

        // Se os 4 forem iguais => sucesso garantido
        if (maxGroup == 4) {
            applySuccess();
            return;
        }

        // calcula chance usando config NeoForge (Config.COMMON)
        double base = Config.COMMON.baseChance.get();
        double per = Config.COMMON.perMatchIncrease.get();
        double chance = base + (Math.max(0, maxGroup - 1) * per);
        chance = Math.max(0.0, Math.min(1.0, chance));

        boolean got = this.level.random.nextDouble() < chance;

        if (got) {
            applySuccess();
        } else {
            // falha: consome inputs (ajuste se quiser devolver parcialmente)
            for (int i = 0; i < 4; i++) this.inventory.extractItem(i, 1,false).setCount(0);
            this.success = false;
        }

        this.progress = 0;
        markDirtyAndSync();
    }

    // helper de sucesso (usa seu item de núcleo registrado)
    private void applySuccess() {
        for (int i = 0; i < 4; i++) this.inventory.extractItem(i, 1,false).setCount(0);
        this.inventory.insertItem(4, new ItemStack(org.dantesys.nexus.items.NexusItems.NUCLEO.get()),false);
        this.success = true;
    }
    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        if (level.isClientSide || !(t instanceof InfusorBlockEntity be)) return;
        // exemplo simples: avança progress se condições ok
        boolean can = be.canStartInfuse();
        if (can) {
            be.progress++;
            if (be.progress >= 100) {
                be.attemptComplete();
                be.progress = 0;
            }
            be.markDirtyAndSync();
        } else if (be.progress != 0) {
            be.progress = 0;
            be.markDirtyAndSync();
        }
    }
}
