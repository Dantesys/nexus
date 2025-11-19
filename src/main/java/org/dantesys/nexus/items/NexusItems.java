package org.dantesys.nexus.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.dantesys.nexus.blocks.NexusBlocks;

import java.util.function.Supplier;

import static org.dantesys.nexus.Nexus.MODID;

public class NexusItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final Supplier<Item> FRAGMENTO_FOGO = ITEMS.registerSimpleItem("fragmento_fogo",
            new Item.Properties().fireResistant());
    public static final Supplier<Item> FRAGMENTO_AGUA = ITEMS.registerSimpleItem("fragmento_agua",
            new Item.Properties());
    public static final Supplier<Item> FRAGMENTO_NATUREZA = ITEMS.registerSimpleItem("fragmento_natureza",
            new Item.Properties());
    public static final Supplier<Item> FRAGMENTO_ROCHA = ITEMS.registerSimpleItem("fragmento_rocha",
            new Item.Properties());
    public static final Supplier<Item> FRAGMENTO_ELETRICO = ITEMS.registerSimpleItem("fragmento_eletrico",
            new Item.Properties());
    public static final Supplier<Item> FRAGMENTO_METAL = ITEMS.registerSimpleItem("fragmento_metal",
            new Item.Properties());
    public static final Supplier<Item> FRAGMENTO_LUZ = ITEMS.registerSimpleItem("fragmento_luz",
            new Item.Properties());
    public static final Supplier<Item> FRAGMENTO_ESCURO = ITEMS.registerSimpleItem("fragmento_escuro",
            new Item.Properties());
    public static final Supplier<Item> ESMERALDA_FOGO = ITEMS.registerSimpleItem("esmeralda_fogo",
            new Item.Properties().fireResistant());
    public static final Supplier<Item> ESMERALDA_AGUA = ITEMS.registerSimpleItem("esmeralda_agua",
            new Item.Properties());
    public static final Supplier<Item> ESMERALDA_NATUREZA = ITEMS.registerSimpleItem("esmeralda_natureza",
            new Item.Properties());
    public static final Supplier<Item> ESMERALDA_ROCHA = ITEMS.registerSimpleItem("esmeralda_rocha",
            new Item.Properties());
    public static final Supplier<Item> ESMERALDA_ELETRICO = ITEMS.registerSimpleItem("esmeralda_eletrico",
            new Item.Properties());
    public static final Supplier<Item> ESMERALDA_METAL = ITEMS.registerSimpleItem("esmeralda_metal",
            new Item.Properties());
    public static final Supplier<Item> ESMERALDA_LUZ = ITEMS.registerSimpleItem("esmeralda_luz",
            new Item.Properties());
    public static final Supplier<Item> ESMERALDA_ESCURO = ITEMS.registerSimpleItem("esmeralda_escuro",
            new Item.Properties());
    public static final Supplier<Item> NUCLEO = ITEMS.registerSimpleItem("nucleo",
            new Item.Properties());
    public static final Supplier<Item> ACO = ITEMS.registerSimpleItem("aco",
            new Item.Properties());

    public static final Supplier<BlockItem> INFUSOR = ITEMS.registerSimpleBlockItem(NexusBlocks.INFUSOR);
    public static final Supplier<BlockItem> ACO_BLOCK = ITEMS.registerSimpleBlockItem(NexusBlocks.ACO_BLOCK);

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
