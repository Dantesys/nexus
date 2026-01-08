package org.dantesys.nexus.blocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.dantesys.nexus.blocks.entity.BolaCristalBlockEntity;
import org.dantesys.nexus.blocks.entity.ExtratorBlockEntity;
import org.dantesys.nexus.blocks.entity.ForjaBlockEntity;
import org.dantesys.nexus.blocks.entity.InfusorBlockEntity;
import org.dantesys.nexus.worldgen.tree.NexusTreeGrowers;
import org.dantesys.nexus.items.NexusItems;

import java.util.function.Supplier;

import static org.dantesys.nexus.Nexus.MODID;

public class NexusBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE,MODID);

    //Block
    public static final DeferredBlock<InfusorBlock> INFUSOR = registerBlock("infusor",
            () -> new InfusorBlock(BlockBehaviour.Properties.of().explosionResistance(1000f).requiresCorrectToolForDrops().noOcclusion()));
    public static final DeferredBlock<ExtratorBlock> EXTRATOR = registerBlock("extrator",
            () -> new ExtratorBlock(BlockBehaviour.Properties.of().explosionResistance(1000f).requiresCorrectToolForDrops().noOcclusion()));
    public static final DeferredBlock<BolaCristal> BOLA_CRISTAL = registerBlock("bola_cristal",
            () -> new BolaCristal(BlockBehaviour.Properties.of().explosionResistance(1000f).requiresCorrectToolForDrops().dynamicShape().noOcclusion()));
    public static final DeferredBlock<Block> ACO_BLOCK = registerBlock("aco_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> FORJA = registerBlock("forja",
            () -> new ForjaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> TIJOLO_FORJA = registerBlock("tijolo_forja",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()));
    //Madeiras
    //Agua
    public static final DeferredBlock<LeavesBlock> AGUA_LEAVES = registerBlock("agua_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<RotatedPillarBlock> AGUA_LOG = registerBlock("agua_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> AGUA_PLANKS = registerBlock("agua_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<SaplingBlock> AGUA_SAPLING = registerBlock("agua_sapling",
            () -> new SaplingBlock(NexusTreeGrowers.AGUA,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<DoorBlock> AGUA_DOOR = registerBlock("agua_door",
            () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<TrapDoorBlock> AGUA_TRAPDOOR = registerBlock("agua_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<FenceBlock> AGUA_FENCE = registerBlock("agua_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<FenceGateBlock> AGUA_FENCE_GATE = registerBlock("agua_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<ButtonBlock> AGUA_BUTTON = registerBlock("agua_button",
            () -> new ButtonBlock(BlockSetType.OAK,20,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AGUA_LOG = registerBlock("stripped_agua_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<StairBlock> AGUA_STAIRS = registerBlock("agua_stairs",
            () -> new StairBlock(AGUA_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<SlabBlock> AGUA_SLAB = registerBlock("agua_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<PressurePlateBlock> AGUA_PRESSURE_PLATE = registerBlock("agua_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    //Escuro
    public static final DeferredBlock<LeavesBlock> ESCURO_LEAVES = registerBlock("escuro_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<RotatedPillarBlock> ESCURO_LOG = registerBlock("escuro_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> ESCURO_PLANKS = registerBlock("escuro_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<SaplingBlock> ESCURO_SAPLING = registerBlock("escuro_sapling",
            () -> new SaplingBlock(NexusTreeGrowers.ESCURO,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<DoorBlock> ESCURO_DOOR = registerBlock("escuro_door",
            () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<TrapDoorBlock> ESCURO_TRAPDOOR = registerBlock("escuro_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<FenceBlock> ESCURO_FENCE = registerBlock("escuro_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<FenceGateBlock> ESCURO_FENCE_GATE = registerBlock("escuro_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<ButtonBlock> ESCURO_BUTTON = registerBlock("escuro_button",
            () -> new ButtonBlock(BlockSetType.OAK,20,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ESCURO_LOG = registerBlock("stripped_escuro_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<StairBlock> ESCURO_STAIRS = registerBlock("escuro_stairs",
            () -> new StairBlock(ESCURO_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<SlabBlock> ESCURO_SLAB = registerBlock("escuro_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<PressurePlateBlock> ESCURO_PRESSURE_PLATE = registerBlock("escuro_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    //Eletrico
    public static final DeferredBlock<LeavesBlock> ELETRICO_LEAVES = registerBlock("eletrico_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<RotatedPillarBlock> ELETRICO_LOG = registerBlock("eletrico_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> ELETRICO_PLANKS = registerBlock("eletrico_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<SaplingBlock> ELETRICO_SAPLING = registerBlock("eletrico_sapling",
            () -> new SaplingBlock(NexusTreeGrowers.ELETRICO,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<DoorBlock> ELETRICO_DOOR = registerBlock("eletrico_door",
            () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<TrapDoorBlock> ELETRICO_TRAPDOOR = registerBlock("eletrico_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<FenceBlock> ELETRICO_FENCE = registerBlock("eletrico_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<FenceGateBlock> ELETRICO_FENCE_GATE = registerBlock("eletrico_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<ButtonBlock> ELETRICO_BUTTON = registerBlock("eletrico_button",
            () -> new ButtonBlock(BlockSetType.OAK,20,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ELETRICO_LOG = registerBlock("stripped_eletrico_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<StairBlock> ELETRICO_STAIRS = registerBlock("eletrico_stairs",
            () -> new StairBlock(ELETRICO_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<SlabBlock> ELETRICO_SLAB = registerBlock("eletrico_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<PressurePlateBlock> ELETRICO_PRESSURE_PLATE = registerBlock("eletrico_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    //Fogo
    public static final DeferredBlock<LeavesBlock> FOGO_LEAVES = registerBlock("fogo_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<RotatedPillarBlock> FOGO_LOG = registerBlock("fogo_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> FOGO_PLANKS = registerBlock("fogo_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<SaplingBlock> FOGO_SAPLING = registerBlock("fogo_sapling",
            () -> new SaplingBlock(NexusTreeGrowers.FOGO,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<DoorBlock> FOGO_DOOR = registerBlock("fogo_door",
            () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<TrapDoorBlock> FOGO_TRAPDOOR = registerBlock("fogo_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<FenceBlock> FOGO_FENCE = registerBlock("fogo_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<FenceGateBlock> FOGO_FENCE_GATE = registerBlock("fogo_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<ButtonBlock> FOGO_BUTTON = registerBlock("fogo_button",
            () -> new ButtonBlock(BlockSetType.OAK,20,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_FOGO_LOG = registerBlock("stripped_fogo_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<StairBlock> FOGO_STAIRS = registerBlock("fogo_stairs",
            () -> new StairBlock(FOGO_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<SlabBlock> FOGO_SLAB = registerBlock("fogo_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<PressurePlateBlock> FOGO_PRESSURE_PLATE = registerBlock("fogo_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    //Luz
    public static final DeferredBlock<LeavesBlock> LUZ_LEAVES = registerBlock("luz_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<RotatedPillarBlock> LUZ_LOG = registerBlock("luz_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> LUZ_PLANKS = registerBlock("luz_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<SaplingBlock> LUZ_SAPLING = registerBlock("luz_sapling",
            () -> new SaplingBlock(NexusTreeGrowers.LUZ,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<DoorBlock> LUZ_DOOR = registerBlock("luz_door",
            () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<TrapDoorBlock> LUZ_TRAPDOOR = registerBlock("luz_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<FenceBlock> LUZ_FENCE = registerBlock("luz_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<FenceGateBlock> LUZ_FENCE_GATE = registerBlock("luz_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<ButtonBlock> LUZ_BUTTON = registerBlock("luz_button",
            () -> new ButtonBlock(BlockSetType.OAK,20,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_LUZ_LOG = registerBlock("stripped_luz_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<StairBlock> LUZ_STAIRS = registerBlock("luz_stairs",
            () -> new StairBlock(LUZ_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<SlabBlock> LUZ_SLAB = registerBlock("luz_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<PressurePlateBlock> LUZ_PRESSURE_PLATE = registerBlock("luz_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    //Metal
    public static final DeferredBlock<LeavesBlock> METAL_LEAVES = registerBlock("metal_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<RotatedPillarBlock> METAL_LOG = registerBlock("metal_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> METAL_PLANKS = registerBlock("metal_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<SaplingBlock> METAL_SAPLING = registerBlock("metal_sapling",
            () -> new SaplingBlock(NexusTreeGrowers.METAL,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<DoorBlock> METAL_DOOR = registerBlock("metal_door",
            () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<TrapDoorBlock> METAL_TRAPDOOR = registerBlock("metal_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<FenceBlock> METAL_FENCE = registerBlock("metal_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<FenceGateBlock> METAL_FENCE_GATE = registerBlock("metal_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<ButtonBlock> METAL_BUTTON = registerBlock("metal_button",
            () -> new ButtonBlock(BlockSetType.OAK,20,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_METAL_LOG = registerBlock("stripped_metal_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<StairBlock> METAL_STAIRS = registerBlock("metal_stairs",
            () -> new StairBlock(METAL_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<SlabBlock> METAL_SLAB = registerBlock("metal_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<PressurePlateBlock> METAL_PRESSURE_PLATE = registerBlock("metal_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    //Natureza
    public static final DeferredBlock<LeavesBlock> NATUREZA_LEAVES = registerBlock("natureza_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<RotatedPillarBlock> NATUREZA_LOG = registerBlock("natureza_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> NATUREZA_PLANKS = registerBlock("natureza_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<SaplingBlock> NATUREZA_SAPLING = registerBlock("natureza_sapling",
            () -> new SaplingBlock(NexusTreeGrowers.NATUREZA,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<DoorBlock> NATUREZA_DOOR = registerBlock("natureza_door",
            () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<TrapDoorBlock> NATUREZA_TRAPDOOR = registerBlock("natureza_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<FenceBlock> NATUREZA_FENCE = registerBlock("natureza_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<FenceGateBlock> NATUREZA_FENCE_GATE = registerBlock("natureza_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<ButtonBlock> NATUREZA_BUTTON = registerBlock("natureza_button",
            () -> new ButtonBlock(BlockSetType.OAK,20,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_NATUREZA_LOG = registerBlock("stripped_natureza_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<StairBlock> NATUREZA_STAIRS = registerBlock("natureza_stairs",
            () -> new StairBlock(NATUREZA_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<SlabBlock> NATUREZA_SLAB = registerBlock("natureza_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<PressurePlateBlock> NATUREZA_PRESSURE_PLATE = registerBlock("natureza_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    //Rocha
    public static final DeferredBlock<LeavesBlock> ROCHA_LEAVES = registerBlock("rocha_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<RotatedPillarBlock> ROCHA_LOG = registerBlock("rocha_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> ROCHA_PLANKS = registerBlock("rocha_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<SaplingBlock> ROCHA_SAPLING = registerBlock("rocha_sapling",
            () -> new SaplingBlock(NexusTreeGrowers.ROCHA,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<DoorBlock> ROCHA_DOOR = registerBlock("rocha_door",
            () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<TrapDoorBlock> ROCHA_TRAPDOOR = registerBlock("rocha_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final DeferredBlock<FenceBlock> ROCHA_FENCE = registerBlock("rocha_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<FenceGateBlock> ROCHA_FENCE_GATE = registerBlock("rocha_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<ButtonBlock> ROCHA_BUTTON = registerBlock("rocha_button",
            () -> new ButtonBlock(BlockSetType.OAK,20,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_ROCHA_LOG = registerBlock("stripped_rocha_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<StairBlock> ROCHA_STAIRS = registerBlock("rocha_stairs",
            () -> new StairBlock(ROCHA_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<SlabBlock> ROCHA_SLAB = registerBlock("rocha_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredBlock<PressurePlateBlock> ROCHA_PRESSURE_PLATE = registerBlock("rocha_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    //Block Entity
    public static final Supplier<BlockEntityType<InfusorBlockEntity>> INFUSOR_BE =
            BLOCK_ENTITIES.register("infusor",
                    () -> BlockEntityType.Builder.of(InfusorBlockEntity::new,INFUSOR.get())
                    .build(null));
    public static final Supplier<BlockEntityType<ExtratorBlockEntity>> EXTRATOR_BE =
            BLOCK_ENTITIES.register("extrator",
                    () -> BlockEntityType.Builder.of(ExtratorBlockEntity::new,EXTRATOR.get())
                            .build(null));
    public static final Supplier<BlockEntityType<BolaCristalBlockEntity>> BOLA_CRISTAL_BE =
            BLOCK_ENTITIES.register("bola_cristal",
                    () -> BlockEntityType.Builder.of(BolaCristalBlockEntity::new,BOLA_CRISTAL.get())
                            .build(null));
    public static final Supplier<BlockEntityType<ForjaBlockEntity>> FORJA_BE =
            BLOCK_ENTITIES.register("forja",
                    () -> BlockEntityType.Builder.of(ForjaBlockEntity::new,FORJA.get())
                            .build(null));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        NexusItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    public static void register(IEventBus bus){
        BLOCKS.register(bus);
        BLOCK_ENTITIES.register(bus);
    }
}
