package org.dantesys.nexus.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.blocks.NexusBlocks;

public class NexusBlockStateProvider extends BlockStateProvider {
    public NexusBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(NexusBlocks.ACO_BLOCK);
        blockWithItem(NexusBlocks.TIJOLO_FORJA);
        //Agua
        blockWithItem(NexusBlocks.AGUA_PLANKS);
        stairsBlock(NexusBlocks.AGUA_STAIRS.get(), blockTexture(NexusBlocks.AGUA_PLANKS.get()));
        slabBlock(NexusBlocks.AGUA_SLAB.get(), blockTexture(NexusBlocks.AGUA_PLANKS.get()), blockTexture(NexusBlocks.AGUA_PLANKS.get()));
        buttonBlock(NexusBlocks.AGUA_BUTTON.get(), blockTexture(NexusBlocks.AGUA_PLANKS.get()));
        pressurePlateBlock(NexusBlocks.AGUA_PRESSURE_PLATE.get(), blockTexture(NexusBlocks.AGUA_PLANKS.get()));
        fenceBlock(NexusBlocks.AGUA_FENCE.get(), blockTexture(NexusBlocks.AGUA_PLANKS.get()));
        fenceGateBlock(NexusBlocks.AGUA_FENCE_GATE.get(), blockTexture(NexusBlocks.AGUA_PLANKS.get()));
        doorBlockWithRenderType(NexusBlocks.AGUA_DOOR.get(), modLoc("block/agua_door_bottom"), modLoc("block/agua_door_top"), "cutout");
        trapdoorBlockWithRenderType(NexusBlocks.AGUA_TRAPDOOR.get(), modLoc("block/agua_trapdoor"), true, "cutout");
        blockItem(NexusBlocks.AGUA_STAIRS);
        blockItem(NexusBlocks.AGUA_SLAB);
        blockItem(NexusBlocks.AGUA_PRESSURE_PLATE);
        blockItem(NexusBlocks.AGUA_FENCE_GATE);
        blockItem(NexusBlocks.AGUA_TRAPDOOR,"_bottom");
        logBlock(NexusBlocks.AGUA_LOG.get());
        logBlock(NexusBlocks.STRIPPED_AGUA_LOG.get());
        blockItem(NexusBlocks.AGUA_LOG);
        blockItem(NexusBlocks.STRIPPED_AGUA_LOG);
        leavesBlock(NexusBlocks.AGUA_LEAVES);
        saplingBlock(NexusBlocks.AGUA_SAPLING);
        //Eletrico
        blockWithItem(NexusBlocks.ELETRICO_PLANKS);
        stairsBlock(NexusBlocks.ELETRICO_STAIRS.get(), blockTexture(NexusBlocks.ELETRICO_PLANKS.get()));
        slabBlock(NexusBlocks.ELETRICO_SLAB.get(), blockTexture(NexusBlocks.ELETRICO_PLANKS.get()), blockTexture(NexusBlocks.ELETRICO_PLANKS.get()));
        buttonBlock(NexusBlocks.ELETRICO_BUTTON.get(), blockTexture(NexusBlocks.ELETRICO_PLANKS.get()));
        pressurePlateBlock(NexusBlocks.ELETRICO_PRESSURE_PLATE.get(), blockTexture(NexusBlocks.ELETRICO_PLANKS.get()));
        fenceBlock(NexusBlocks.ELETRICO_FENCE.get(), blockTexture(NexusBlocks.ELETRICO_PLANKS.get()));
        fenceGateBlock(NexusBlocks.ELETRICO_FENCE_GATE.get(), blockTexture(NexusBlocks.ELETRICO_PLANKS.get()));
        doorBlockWithRenderType(NexusBlocks.ELETRICO_DOOR.get(), modLoc("block/eletrico_door_bottom"), modLoc("block/eletrico_door_top"), "cutout");
        trapdoorBlockWithRenderType(NexusBlocks.ELETRICO_TRAPDOOR.get(), modLoc("block/eletrico_trapdoor"), true, "cutout");
        blockItem(NexusBlocks.ELETRICO_STAIRS);
        blockItem(NexusBlocks.ELETRICO_SLAB);
        blockItem(NexusBlocks.ELETRICO_PRESSURE_PLATE);
        blockItem(NexusBlocks.ELETRICO_FENCE_GATE);
        blockItem(NexusBlocks.ELETRICO_TRAPDOOR,"_bottom");
        logBlock(NexusBlocks.ELETRICO_LOG.get());
        logBlock(NexusBlocks.STRIPPED_ELETRICO_LOG.get());
        blockItem(NexusBlocks.ELETRICO_LOG);
        blockItem(NexusBlocks.STRIPPED_ELETRICO_LOG);
        leavesBlock(NexusBlocks.ELETRICO_LEAVES);
        saplingBlock(NexusBlocks.ELETRICO_SAPLING);
        //Escuro
        blockWithItem(NexusBlocks.ESCURO_PLANKS);
        stairsBlock(NexusBlocks.ESCURO_STAIRS.get(), blockTexture(NexusBlocks.ESCURO_PLANKS.get()));
        slabBlock(NexusBlocks.ESCURO_SLAB.get(), blockTexture(NexusBlocks.ESCURO_PLANKS.get()), blockTexture(NexusBlocks.ESCURO_PLANKS.get()));
        buttonBlock(NexusBlocks.ESCURO_BUTTON.get(), blockTexture(NexusBlocks.ESCURO_PLANKS.get()));
        pressurePlateBlock(NexusBlocks.ESCURO_PRESSURE_PLATE.get(), blockTexture(NexusBlocks.ESCURO_PLANKS.get()));
        fenceBlock(NexusBlocks.ESCURO_FENCE.get(), blockTexture(NexusBlocks.ESCURO_PLANKS.get()));
        fenceGateBlock(NexusBlocks.ESCURO_FENCE_GATE.get(), blockTexture(NexusBlocks.ESCURO_PLANKS.get()));
        doorBlockWithRenderType(NexusBlocks.ESCURO_DOOR.get(), modLoc("block/escuro_door_bottom"), modLoc("block/escuro_door_top"), "cutout");
        trapdoorBlockWithRenderType(NexusBlocks.ESCURO_TRAPDOOR.get(), modLoc("block/escuro_trapdoor"), true, "cutout");
        blockItem(NexusBlocks.ESCURO_STAIRS);
        blockItem(NexusBlocks.ESCURO_SLAB);
        blockItem(NexusBlocks.ESCURO_PRESSURE_PLATE);
        blockItem(NexusBlocks.ESCURO_FENCE_GATE);
        blockItem(NexusBlocks.ESCURO_TRAPDOOR,"_bottom");
        logBlock(NexusBlocks.ESCURO_LOG.get());
        logBlock(NexusBlocks.STRIPPED_ESCURO_LOG.get());
        blockItem(NexusBlocks.ESCURO_LOG);
        blockItem(NexusBlocks.STRIPPED_ESCURO_LOG);
        leavesBlock(NexusBlocks.ESCURO_LEAVES);
        saplingBlock(NexusBlocks.ESCURO_SAPLING);
        //Fogo
        blockWithItem(NexusBlocks.FOGO_PLANKS);
        stairsBlock(NexusBlocks.FOGO_STAIRS.get(), blockTexture(NexusBlocks.FOGO_PLANKS.get()));
        slabBlock(NexusBlocks.FOGO_SLAB.get(), blockTexture(NexusBlocks.FOGO_PLANKS.get()), blockTexture(NexusBlocks.FOGO_PLANKS.get()));
        buttonBlock(NexusBlocks.FOGO_BUTTON.get(), blockTexture(NexusBlocks.FOGO_PLANKS.get()));
        pressurePlateBlock(NexusBlocks.FOGO_PRESSURE_PLATE.get(), blockTexture(NexusBlocks.FOGO_PLANKS.get()));
        fenceBlock(NexusBlocks.FOGO_FENCE.get(), blockTexture(NexusBlocks.FOGO_PLANKS.get()));
        fenceGateBlock(NexusBlocks.FOGO_FENCE_GATE.get(), blockTexture(NexusBlocks.FOGO_PLANKS.get()));
        doorBlockWithRenderType(NexusBlocks.FOGO_DOOR.get(), modLoc("block/fogo_door_bottom"), modLoc("block/fogo_door_top"), "cutout");
        trapdoorBlockWithRenderType(NexusBlocks.FOGO_TRAPDOOR.get(), modLoc("block/fogo_trapdoor"), true, "cutout");
        blockItem(NexusBlocks.FOGO_STAIRS);
        blockItem(NexusBlocks.FOGO_SLAB);
        blockItem(NexusBlocks.FOGO_PRESSURE_PLATE);
        blockItem(NexusBlocks.FOGO_FENCE_GATE);
        blockItem(NexusBlocks.FOGO_TRAPDOOR,"_bottom");
        logBlock(NexusBlocks.FOGO_LOG.get());
        logBlock(NexusBlocks.STRIPPED_FOGO_LOG.get());
        blockItem(NexusBlocks.FOGO_LOG);
        blockItem(NexusBlocks.STRIPPED_FOGO_LOG);
        leavesBlock(NexusBlocks.FOGO_LEAVES);
        saplingBlock(NexusBlocks.FOGO_SAPLING);
        //Luz
        blockWithItem(NexusBlocks.LUZ_PLANKS);
        stairsBlock(NexusBlocks.LUZ_STAIRS.get(), blockTexture(NexusBlocks.LUZ_PLANKS.get()));
        slabBlock(NexusBlocks.LUZ_SLAB.get(), blockTexture(NexusBlocks.LUZ_PLANKS.get()), blockTexture(NexusBlocks.LUZ_PLANKS.get()));
        buttonBlock(NexusBlocks.LUZ_BUTTON.get(), blockTexture(NexusBlocks.LUZ_PLANKS.get()));
        pressurePlateBlock(NexusBlocks.LUZ_PRESSURE_PLATE.get(), blockTexture(NexusBlocks.LUZ_PLANKS.get()));
        fenceBlock(NexusBlocks.LUZ_FENCE.get(), blockTexture(NexusBlocks.LUZ_PLANKS.get()));
        fenceGateBlock(NexusBlocks.LUZ_FENCE_GATE.get(), blockTexture(NexusBlocks.LUZ_PLANKS.get()));
        doorBlockWithRenderType(NexusBlocks.LUZ_DOOR.get(), modLoc("block/luz_door_bottom"), modLoc("block/luz_door_top"), "cutout");
        trapdoorBlockWithRenderType(NexusBlocks.LUZ_TRAPDOOR.get(), modLoc("block/luz_trapdoor"), true, "cutout");
        blockItem(NexusBlocks.LUZ_STAIRS);
        blockItem(NexusBlocks.LUZ_SLAB);
        blockItem(NexusBlocks.LUZ_PRESSURE_PLATE);
        blockItem(NexusBlocks.LUZ_FENCE_GATE);
        blockItem(NexusBlocks.LUZ_TRAPDOOR,"_bottom");
        logBlock(NexusBlocks.LUZ_LOG.get());
        logBlock(NexusBlocks.STRIPPED_LUZ_LOG.get());
        blockItem(NexusBlocks.LUZ_LOG);
        blockItem(NexusBlocks.STRIPPED_LUZ_LOG);
        leavesBlock(NexusBlocks.LUZ_LEAVES);
        saplingBlock(NexusBlocks.LUZ_SAPLING);
        //Metal
        blockWithItem(NexusBlocks.METAL_PLANKS);
        stairsBlock(NexusBlocks.METAL_STAIRS.get(), blockTexture(NexusBlocks.METAL_PLANKS.get()));
        slabBlock(NexusBlocks.METAL_SLAB.get(), blockTexture(NexusBlocks.METAL_PLANKS.get()), blockTexture(NexusBlocks.METAL_PLANKS.get()));
        buttonBlock(NexusBlocks.METAL_BUTTON.get(), blockTexture(NexusBlocks.METAL_PLANKS.get()));
        pressurePlateBlock(NexusBlocks.METAL_PRESSURE_PLATE.get(), blockTexture(NexusBlocks.METAL_PLANKS.get()));
        fenceBlock(NexusBlocks.METAL_FENCE.get(), blockTexture(NexusBlocks.METAL_PLANKS.get()));
        fenceGateBlock(NexusBlocks.METAL_FENCE_GATE.get(), blockTexture(NexusBlocks.METAL_PLANKS.get()));
        doorBlockWithRenderType(NexusBlocks.METAL_DOOR.get(), modLoc("block/metal_door_bottom"), modLoc("block/metal_door_top"), "cutout");
        trapdoorBlockWithRenderType(NexusBlocks.METAL_TRAPDOOR.get(), modLoc("block/metal_trapdoor"), true, "cutout");
        blockItem(NexusBlocks.METAL_STAIRS);
        blockItem(NexusBlocks.METAL_SLAB);
        blockItem(NexusBlocks.METAL_PRESSURE_PLATE);
        blockItem(NexusBlocks.METAL_FENCE_GATE);
        blockItem(NexusBlocks.METAL_TRAPDOOR,"_bottom");
        logBlock(NexusBlocks.METAL_LOG.get());
        logBlock(NexusBlocks.STRIPPED_METAL_LOG.get());
        blockItem(NexusBlocks.METAL_LOG);
        blockItem(NexusBlocks.STRIPPED_METAL_LOG);
        leavesBlock(NexusBlocks.METAL_LEAVES);
        saplingBlock(NexusBlocks.METAL_SAPLING);
        //Natureza
        blockWithItem(NexusBlocks.NATUREZA_PLANKS);
        stairsBlock(NexusBlocks.NATUREZA_STAIRS.get(), blockTexture(NexusBlocks.NATUREZA_PLANKS.get()));
        slabBlock(NexusBlocks.NATUREZA_SLAB.get(), blockTexture(NexusBlocks.NATUREZA_PLANKS.get()), blockTexture(NexusBlocks.NATUREZA_PLANKS.get()));
        buttonBlock(NexusBlocks.NATUREZA_BUTTON.get(), blockTexture(NexusBlocks.NATUREZA_PLANKS.get()));
        pressurePlateBlock(NexusBlocks.NATUREZA_PRESSURE_PLATE.get(), blockTexture(NexusBlocks.NATUREZA_PLANKS.get()));
        fenceBlock(NexusBlocks.NATUREZA_FENCE.get(), blockTexture(NexusBlocks.NATUREZA_PLANKS.get()));
        fenceGateBlock(NexusBlocks.NATUREZA_FENCE_GATE.get(), blockTexture(NexusBlocks.NATUREZA_PLANKS.get()));
        doorBlockWithRenderType(NexusBlocks.NATUREZA_DOOR.get(), modLoc("block/natureza_door_bottom"), modLoc("block/natureza_door_top"), "cutout");
        trapdoorBlockWithRenderType(NexusBlocks.NATUREZA_TRAPDOOR.get(), modLoc("block/natureza_trapdoor"), true, "cutout");
        blockItem(NexusBlocks.NATUREZA_STAIRS);
        blockItem(NexusBlocks.NATUREZA_SLAB);
        blockItem(NexusBlocks.NATUREZA_PRESSURE_PLATE);
        blockItem(NexusBlocks.NATUREZA_FENCE_GATE);
        blockItem(NexusBlocks.NATUREZA_TRAPDOOR,"_bottom");
        logBlock(NexusBlocks.NATUREZA_LOG.get());
        logBlock(NexusBlocks.STRIPPED_NATUREZA_LOG.get());
        blockItem(NexusBlocks.NATUREZA_LOG);
        blockItem(NexusBlocks.STRIPPED_NATUREZA_LOG);
        leavesBlock(NexusBlocks.NATUREZA_LEAVES);
        saplingBlock(NexusBlocks.NATUREZA_SAPLING);
        //Rocha
        blockWithItem(NexusBlocks.ROCHA_PLANKS);
        stairsBlock(NexusBlocks.ROCHA_STAIRS.get(), blockTexture(NexusBlocks.ROCHA_PLANKS.get()));
        slabBlock(NexusBlocks.ROCHA_SLAB.get(), blockTexture(NexusBlocks.ROCHA_PLANKS.get()), blockTexture(NexusBlocks.ROCHA_PLANKS.get()));
        buttonBlock(NexusBlocks.ROCHA_BUTTON.get(), blockTexture(NexusBlocks.ROCHA_PLANKS.get()));
        pressurePlateBlock(NexusBlocks.ROCHA_PRESSURE_PLATE.get(), blockTexture(NexusBlocks.ROCHA_PLANKS.get()));
        fenceBlock(NexusBlocks.ROCHA_FENCE.get(), blockTexture(NexusBlocks.ROCHA_PLANKS.get()));
        fenceGateBlock(NexusBlocks.ROCHA_FENCE_GATE.get(), blockTexture(NexusBlocks.ROCHA_PLANKS.get()));
        doorBlockWithRenderType(NexusBlocks.ROCHA_DOOR.get(), modLoc("block/rocha_door_bottom"), modLoc("block/rocha_door_top"), "cutout");
        trapdoorBlockWithRenderType(NexusBlocks.ROCHA_TRAPDOOR.get(), modLoc("block/rocha_trapdoor"), true, "cutout");
        blockItem(NexusBlocks.ROCHA_STAIRS);
        blockItem(NexusBlocks.ROCHA_SLAB);
        blockItem(NexusBlocks.ROCHA_PRESSURE_PLATE);
        blockItem(NexusBlocks.ROCHA_FENCE_GATE);
        blockItem(NexusBlocks.ROCHA_TRAPDOOR,"_bottom");
        logBlock(NexusBlocks.ROCHA_LOG.get());
        logBlock(NexusBlocks.STRIPPED_ROCHA_LOG.get());
        blockItem(NexusBlocks.ROCHA_LOG);
        blockItem(NexusBlocks.STRIPPED_ROCHA_LOG);
        leavesBlock(NexusBlocks.ROCHA_LEAVES);
        saplingBlock(NexusBlocks.ROCHA_SAPLING);
    }
    private void saplingBlock(DeferredBlock<SaplingBlock> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<LeavesBlock> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile(Nexus.MODID+":block/" + deferredBlock.getId().getPath()));
    }
    private void blockItem(DeferredBlock<?> deferredBlock,String addition) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile(Nexus.MODID+":block/" + deferredBlock.getId().getPath()+addition));
    }
}
