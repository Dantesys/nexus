package org.dantesys.nexus.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.item.ModCreativeTab;
import org.dantesys.nexus.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Nexus.MOD_ID);

    public static final RegistryObject<Block> CARREGADOR = registerBlock("carregador_nexus",() -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(10f).requiresCorrectToolForDrops()), ModCreativeTab.NEXUS_TAB);

    private  static <T extends Block> RegistryObject<T> registerBlock(String nome, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(nome,block);
        registerBlockItem(nome,toReturn,tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String nome, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(nome, () -> new BlockItem(block.get(),new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
