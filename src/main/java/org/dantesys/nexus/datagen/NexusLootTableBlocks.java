package org.dantesys.nexus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.dantesys.nexus.blocks.NexusBlocks;
import org.dantesys.nexus.items.NexusItems;

import java.util.Set;

public class NexusLootTableBlocks extends BlockLootSubProvider {
    public NexusLootTableBlocks(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return NexusBlocks.BLOCKS.getEntries()
                .stream()
                .map(e -> (Block) e.value())
                .toList();
    }

    // Actually add our loot tables.
    @Override
    protected void generate() {
        dropSelf(NexusBlocks.INFUSOR.get());
        dropSelf(NexusBlocks.EXTRATOR.get());
        dropSelf(NexusBlocks.BOLA_CRISTAL.get());
        dropSelf(NexusBlocks.FORJA.get());
        dropSelf(NexusBlocks.TIJOLO_FORJA.get());
        dropSelf(NexusBlocks.ACO_BLOCK.get());
        //Agua
        dropSelf(NexusBlocks.AGUA_STAIRS.get());
        add(NexusBlocks.AGUA_SLAB.get(),
                block -> createSlabItemTable(NexusBlocks.AGUA_SLAB.get()));
        dropSelf(NexusBlocks.AGUA_PRESSURE_PLATE.get());
        dropSelf(NexusBlocks.AGUA_BUTTON.get());
        dropSelf(NexusBlocks.AGUA_FENCE.get());
        dropSelf(NexusBlocks.AGUA_FENCE_GATE.get());
        dropSelf(NexusBlocks.AGUA_TRAPDOOR.get());
        add(NexusBlocks.AGUA_DOOR.get(),
                block -> createDoorTable(NexusBlocks.AGUA_DOOR.get()));
        dropSelf(NexusBlocks.AGUA_LOG.get());
        dropSelf(NexusBlocks.STRIPPED_AGUA_LOG.get());
        dropSelf(NexusBlocks.AGUA_PLANKS.get());
        dropSelf(NexusBlocks.AGUA_SAPLING.get());
        add(NexusBlocks.AGUA_LEAVES.get(), block ->
                createLeavesDrops(block, NexusBlocks.AGUA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(NexusItems.FRAGMENTO_AGUA.get())
                                .when(LootItemRandomChanceCondition.randomChance(0.05f))
                        )
                ));
        //Eletrico
        dropSelf(NexusBlocks.ELETRICO_STAIRS.get());
        add(NexusBlocks.ELETRICO_SLAB.get(),
                block -> createSlabItemTable(NexusBlocks.ELETRICO_SLAB.get()));
        dropSelf(NexusBlocks.ELETRICO_PRESSURE_PLATE.get());
        dropSelf(NexusBlocks.ELETRICO_BUTTON.get());
        dropSelf(NexusBlocks.ELETRICO_FENCE.get());
        dropSelf(NexusBlocks.ELETRICO_FENCE_GATE.get());
        dropSelf(NexusBlocks.ELETRICO_TRAPDOOR.get());
        add(NexusBlocks.ELETRICO_DOOR.get(),
                block -> createDoorTable(NexusBlocks.ELETRICO_DOOR.get()));
        dropSelf(NexusBlocks.ELETRICO_LOG.get());
        dropSelf(NexusBlocks.STRIPPED_ELETRICO_LOG.get());
        dropSelf(NexusBlocks.ELETRICO_PLANKS.get());
        dropSelf(NexusBlocks.ELETRICO_SAPLING.get());
        add(NexusBlocks.ELETRICO_LEAVES.get(), block ->
                createLeavesDrops(block, NexusBlocks.ELETRICO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(NexusItems.FRAGMENTO_ELETRICO.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                )
                        ));
        //Escuro
        dropSelf(NexusBlocks.ESCURO_STAIRS.get());
        add(NexusBlocks.ESCURO_SLAB.get(),
                block -> createSlabItemTable(NexusBlocks.ESCURO_SLAB.get()));
        dropSelf(NexusBlocks.ESCURO_PRESSURE_PLATE.get());
        dropSelf(NexusBlocks.ESCURO_BUTTON.get());
        dropSelf(NexusBlocks.ESCURO_FENCE.get());
        dropSelf(NexusBlocks.ESCURO_FENCE_GATE.get());
        dropSelf(NexusBlocks.ESCURO_TRAPDOOR.get());
        add(NexusBlocks.ESCURO_DOOR.get(),
                block -> createDoorTable(NexusBlocks.ESCURO_DOOR.get()));
        dropSelf(NexusBlocks.ESCURO_LOG.get());
        dropSelf(NexusBlocks.STRIPPED_ESCURO_LOG.get());
        dropSelf(NexusBlocks.ESCURO_PLANKS.get());
        dropSelf(NexusBlocks.ESCURO_SAPLING.get());
        add(NexusBlocks.ESCURO_LEAVES.get(), block ->
                createLeavesDrops(block, NexusBlocks.ESCURO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(NexusItems.FRAGMENTO_ESCURO.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                )
                        ));
        //Fogo
        dropSelf(NexusBlocks.FOGO_STAIRS.get());
        add(NexusBlocks.FOGO_SLAB.get(),
                block -> createSlabItemTable(NexusBlocks.FOGO_SLAB.get()));
        dropSelf(NexusBlocks.FOGO_PRESSURE_PLATE.get());
        dropSelf(NexusBlocks.FOGO_BUTTON.get());
        dropSelf(NexusBlocks.FOGO_FENCE.get());
        dropSelf(NexusBlocks.FOGO_FENCE_GATE.get());
        dropSelf(NexusBlocks.FOGO_TRAPDOOR.get());
        add(NexusBlocks.FOGO_DOOR.get(),
                block -> createDoorTable(NexusBlocks.FOGO_DOOR.get()));
        dropSelf(NexusBlocks.FOGO_LOG.get());
        dropSelf(NexusBlocks.STRIPPED_FOGO_LOG.get());
        dropSelf(NexusBlocks.FOGO_PLANKS.get());
        dropSelf(NexusBlocks.FOGO_SAPLING.get());
        add(NexusBlocks.FOGO_LEAVES.get(), block ->
                createLeavesDrops(block, NexusBlocks.FOGO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(NexusItems.FRAGMENTO_FOGO.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                )
                        ));
        //Luz
        dropSelf(NexusBlocks.LUZ_STAIRS.get());
        add(NexusBlocks.LUZ_SLAB.get(),
                block -> createSlabItemTable(NexusBlocks.LUZ_SLAB.get()));
        dropSelf(NexusBlocks.LUZ_PRESSURE_PLATE.get());
        dropSelf(NexusBlocks.LUZ_BUTTON.get());
        dropSelf(NexusBlocks.LUZ_FENCE.get());
        dropSelf(NexusBlocks.LUZ_FENCE_GATE.get());
        dropSelf(NexusBlocks.LUZ_TRAPDOOR.get());
        add(NexusBlocks.LUZ_DOOR.get(),
                block -> createDoorTable(NexusBlocks.LUZ_DOOR.get()));
        dropSelf(NexusBlocks.LUZ_LOG.get());
        dropSelf(NexusBlocks.STRIPPED_LUZ_LOG.get());
        dropSelf(NexusBlocks.LUZ_PLANKS.get());
        dropSelf(NexusBlocks.LUZ_SAPLING.get());
        add(NexusBlocks.LUZ_LEAVES.get(), block ->
                createLeavesDrops(block, NexusBlocks.LUZ_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(NexusItems.FRAGMENTO_LUZ.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                )
                        ));
        //Metal
        dropSelf(NexusBlocks.METAL_STAIRS.get());
        add(NexusBlocks.METAL_SLAB.get(),
                block -> createSlabItemTable(NexusBlocks.METAL_SLAB.get()));
        dropSelf(NexusBlocks.METAL_PRESSURE_PLATE.get());
        dropSelf(NexusBlocks.METAL_BUTTON.get());
        dropSelf(NexusBlocks.METAL_FENCE.get());
        dropSelf(NexusBlocks.METAL_FENCE_GATE.get());
        dropSelf(NexusBlocks.METAL_TRAPDOOR.get());
        add(NexusBlocks.METAL_DOOR.get(),
                block -> createDoorTable(NexusBlocks.METAL_DOOR.get()));
        dropSelf(NexusBlocks.METAL_LOG.get());
        dropSelf(NexusBlocks.STRIPPED_METAL_LOG.get());
        dropSelf(NexusBlocks.METAL_PLANKS.get());
        dropSelf(NexusBlocks.METAL_SAPLING.get());
        add(NexusBlocks.METAL_LEAVES.get(), block ->
                createLeavesDrops(block, NexusBlocks.METAL_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(NexusItems.FRAGMENTO_METAL.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                )
                        ));
        //Natureza
        dropSelf(NexusBlocks.NATUREZA_STAIRS.get());
        add(NexusBlocks.NATUREZA_SLAB.get(),
                block -> createSlabItemTable(NexusBlocks.NATUREZA_SLAB.get()));
        dropSelf(NexusBlocks.NATUREZA_PRESSURE_PLATE.get());
        dropSelf(NexusBlocks.NATUREZA_BUTTON.get());
        dropSelf(NexusBlocks.NATUREZA_FENCE.get());
        dropSelf(NexusBlocks.NATUREZA_FENCE_GATE.get());
        dropSelf(NexusBlocks.NATUREZA_TRAPDOOR.get());
        add(NexusBlocks.NATUREZA_DOOR.get(),
                block -> createDoorTable(NexusBlocks.NATUREZA_DOOR.get()));
        dropSelf(NexusBlocks.NATUREZA_LOG.get());
        dropSelf(NexusBlocks.STRIPPED_NATUREZA_LOG.get());
        dropSelf(NexusBlocks.NATUREZA_PLANKS.get());
        dropSelf(NexusBlocks.NATUREZA_SAPLING.get());
        add(NexusBlocks.NATUREZA_LEAVES.get(), block ->
                createLeavesDrops(block, NexusBlocks.NATUREZA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(NexusItems.FRAGMENTO_NATUREZA.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                )
                        ));
        //Rocha
        dropSelf(NexusBlocks.ROCHA_STAIRS.get());
        add(NexusBlocks.ROCHA_SLAB.get(),
                block -> createSlabItemTable(NexusBlocks.ROCHA_SLAB.get()));
        dropSelf(NexusBlocks.ROCHA_PRESSURE_PLATE.get());
        dropSelf(NexusBlocks.ROCHA_BUTTON.get());
        dropSelf(NexusBlocks.ROCHA_FENCE.get());
        dropSelf(NexusBlocks.ROCHA_FENCE_GATE.get());
        dropSelf(NexusBlocks.ROCHA_TRAPDOOR.get());
        add(NexusBlocks.ROCHA_DOOR.get(),
                block -> createDoorTable(NexusBlocks.ROCHA_DOOR.get()));
        dropSelf(NexusBlocks.ROCHA_LOG.get());
        dropSelf(NexusBlocks.STRIPPED_ROCHA_LOG.get());
        dropSelf(NexusBlocks.ROCHA_PLANKS.get());
        dropSelf(NexusBlocks.ROCHA_SAPLING.get());
        add(NexusBlocks.ROCHA_LEAVES.get(), block ->
                createLeavesDrops(block, NexusBlocks.ROCHA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(NexusItems.FRAGMENTO_ROCHA.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                )
                        ));
        
    }
}
