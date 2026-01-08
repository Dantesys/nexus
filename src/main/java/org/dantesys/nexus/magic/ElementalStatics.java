package org.dantesys.nexus.magic;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class ElementalStatics implements INBTSerializable<CompoundTag> {

    private boolean unlocked = false;

    private int mana;
    private int maxMana;
    private int afinidade;
    private int regeneracao;
    private int sinergia;

    public boolean isUnlocked() { return unlocked; }
    public void setUnlocked(boolean unlocked) { this.unlocked = unlocked; }

    public int getMana() { return mana; }
    public void setMana(int v) { this.mana = v; }

    public int getAfinidade() { return afinidade; }
    public void setAfinidade(int v) { this.afinidade = v; }

    public int getRegeneracao() { return regeneracao; }
    public void setRegeneracao(int v) { this.regeneracao = v; }

    public int getMaxMana() { return maxMana; }
    public void setMaxMana(int maxMana) { this.maxMana = maxMana; }

    public int getSinergia() { return sinergia; }
    public void setSinergia(int v) { this.sinergia = v; }

    //--- Salvamento antigo ---
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("Unlocked", unlocked);
        tag.putInt("Mana", mana);
        tag.putInt("MaxMana", maxMana);
        tag.putInt("Afinidade", afinidade);
        tag.putInt("Regen", regeneracao);
        tag.putInt("Sinergia", sinergia);
        return tag;
    }

    public static ElementalStatics fromNBT(CompoundTag tag) {
        ElementalStatics es = new ElementalStatics();
        es.unlocked = tag.getBoolean("Unlocked");
        es.mana = tag.getInt("Mana");
        es.maxMana = tag.getInt("MaxMana");
        es.afinidade = tag.getInt("Afinidade");
        es.regeneracao = tag.getInt("Regen");
        es.sinergia = tag.getInt("Sinergia");
        return es;
    }

    public boolean canUse(int mana){
        return this.mana>=mana;
    }
    public void use(int valor){
        setMana(mana-valor);
    }

    public void copyFrom(ElementalStatics other) {
        this.unlocked = other.unlocked;
        this.mana = other.mana;
        this.maxMana = other.maxMana;
        this.afinidade = other.afinidade;
        this.regeneracao = other.regeneracao;
        this.sinergia = other.sinergia;
    }

    //--- NeoForge 1.21.7 NBT ---
    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        return toNBT();
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        this.copyFrom(fromNBT(tag));
    }
}