package org.dantesys.nexus.magic;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.dantesys.nexus.util.Elementos;

import java.util.EnumMap;

public class MagicStats implements INBTSerializable<CompoundTag> {
    private final EnumMap<Elementos, ElementalStatics> stats =
            new EnumMap<>(Elementos.class);

    public MagicStats() {
        for (Elementos type : Elementos.values()) {
            stats.put(type, new ElementalStatics());
        }
    }
    private boolean created = false;

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean v) {
        created = v;
    }

    public ElementalStatics get(Elementos type) {
        return stats.get(type);
    }

    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        for (var entry : stats.entrySet()) {
            tag.put(entry.getKey().name(), entry.getValue().toNBT());
        }
        tag.putBoolean("isCreated", created);
        return tag;
    }

    public static MagicStats fromNBT(CompoundTag tag) {
        MagicStats stats = new MagicStats();
        for (Elementos type : Elementos.values()) {
            if (tag.contains(type.name())) {
                stats.stats.put(type, ElementalStatics.fromNBT(tag.getCompound(type.name())));
            }
        }
        stats.setCreated(tag.getBoolean("isCreated"));
        return stats;
    }

    public void copyFrom(MagicStats other) {
        for (Elementos type : Elementos.values()) {
            this.stats.get(type).copyFrom(other.stats.get(type));
        }
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();

        // salva se foi criado
        tag.putBoolean("created", created);

        // salva todos os stats
        CompoundTag statsTag = new CompoundTag();
        for (var entry : stats.entrySet()) {
            statsTag.put(entry.getKey().name(), entry.getValue().serializeNBT(provider));
        }

        tag.put("stats", statsTag);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        // lê se já foi criado
        this.created = tag.getBoolean("created");

        // lê os stats
        if (tag.contains("stats")) {
            CompoundTag statsTag = tag.getCompound("stats");

            for (Elementos type : Elementos.values()) {
                if (statsTag.contains(type.name())) {
                    ElementalStatics s = stats.get(type);
                    s.deserializeNBT(provider,statsTag.getCompound(type.name()));
                }
            }
        }
    }
}
