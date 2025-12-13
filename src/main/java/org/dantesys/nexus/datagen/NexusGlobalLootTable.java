package org.dantesys.nexus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.loot.AddItem;

import java.util.concurrent.CompletableFuture;

public class NexusGlobalLootTable  extends GlobalLootModifierProvider {
    public NexusGlobalLootTable(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Nexus.MODID);
    }

    @Override
    protected void start() {
            addChest("dungeon",
                    "chests/simple_dungeon");
            addChest("desert_pyramid",
                    "chests/desert_pyramid");
            addChest("jungle_temple",
                    "chests/jungle_temple");
            addChest("buried_treasure",
                    "chests/buried_treasure");
        }

        private void addChest(String name, String table) {
            add("desbloqueio_elemental_" + name,
                    new AddItem(
                            new LootItemCondition[] {
                                    LootTableIdCondition.builder(
                                            ResourceLocation.withDefaultNamespace(table)
                                    ).build(),
                                    LootItemRandomChanceCondition.randomChance(0.05f).build()
                            }
                    )
            );
        }
}
