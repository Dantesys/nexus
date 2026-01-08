package org.dantesys.nexus.items;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.dantesys.nexus.blocks.NexusBlocks;
import org.dantesys.nexus.util.Elementos;
import org.dantesys.nexus.util.NexusTags;
import org.dantesys.nexus.util.RaridadeMolde;

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
    public static final Supplier<Item> NUCLEO = ITEMS.register("nucleo",
            () -> new NucleoItem(new Item.Properties()));
    public static final Supplier<Item> ACO = ITEMS.registerSimpleItem("aco",
            new Item.Properties());
    public static final Supplier<SwordItem> ACO_SWORD = ITEMS.register("aco_sword",
            () -> new SwordItem(NexusToolTier.ACO,new Item.Properties().attributes(SwordItem.createAttributes(NexusToolTier.ACO,3,-2.4F))));
    public static final Supplier<PickaxeItem> ACO_PICKAXE = ITEMS.register("aco_pickaxe",
            () -> new PickaxeItem(NexusToolTier.ACO,new Item.Properties().attributes(PickaxeItem.createAttributes(NexusToolTier.ACO,1,-2.8F))));
    public static final Supplier<AxeItem> ACO_AXE = ITEMS.register("aco_axe",
            () -> new AxeItem(NexusToolTier.ACO,new Item.Properties().attributes(AxeItem.createAttributes(NexusToolTier.ACO,6,-3.1F))));
    public static final Supplier<HoeItem> ACO_HOE = ITEMS.register("aco_hoe",
            () -> new HoeItem(NexusToolTier.ACO,new Item.Properties().attributes(HoeItem.createAttributes(NexusToolTier.ACO,-2.0F,-1F))));
    public static final Supplier<ShovelItem> ACO_SHOVEL = ITEMS.register("aco_shovel",
            () -> new ShovelItem(NexusToolTier.ACO,new Item.Properties().attributes(ShovelItem.createAttributes(NexusToolTier.ACO,1.5F,-3F))));
    public static final DeferredItem<ArmorItem> ACO_HELMET = ITEMS.register("aco_helmet",
            () -> new ArmorItem(NexusArmorMaterial.ACO_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(17))));
    public static final DeferredItem<ArmorItem> ACO_CHESTPLATE = ITEMS.register("aco_chestplate",
            () -> new ArmorItem(NexusArmorMaterial.ACO_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(17))));
    public static final DeferredItem<ArmorItem> ACO_LEGGINGS = ITEMS.register("aco_leggings",
            () -> new ArmorItem(NexusArmorMaterial.ACO_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(17))));
    public static final DeferredItem<ArmorItem> ACO_BOOTS = ITEMS.register("aco_boots",
            () -> new ArmorItem(NexusArmorMaterial.ACO_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(17))));
    public static final Supplier<SwordItem> AGUA_SWORD = ITEMS.register("espada_agua",
            () -> new EspadaElemental(NexusToolTier.AGUA,new Item.Properties().attributes(SwordItem.createAttributes(NexusToolTier.AGUA,3,-2.4F)), Elementos.AGUA));
    public static final Supplier<SwordItem> ELETRICO_SWORD = ITEMS.register("espada_eletrico",
            () -> new EspadaElemental(NexusToolTier.ELETRICO,new Item.Properties().attributes(SwordItem.createAttributes(NexusToolTier.ELETRICO,3,-2.4F)), Elementos.ELETRICO));
    public static final Supplier<SwordItem> ESCURO_SWORD = ITEMS.register("espada_escuro",
            () -> new EspadaElemental(NexusToolTier.SOMBRA,new Item.Properties().attributes(SwordItem.createAttributes(NexusToolTier.SOMBRA,3,-2.4F)), Elementos.SOMBRA));
    public static final Supplier<SwordItem> FOGO_SWORD = ITEMS.register("espada_fogo",
            () -> new EspadaElemental(NexusToolTier.FOGO,new Item.Properties().attributes(SwordItem.createAttributes(NexusToolTier.FOGO,3,-2.4F)), Elementos.FOGO));
    public static final Supplier<SwordItem> LUZ_SWORD = ITEMS.register("espada_luz",
            () -> new EspadaElemental(NexusToolTier.LUZ,new Item.Properties().attributes(SwordItem.createAttributes(NexusToolTier.LUZ,3,-2.4F)), Elementos.LUZ));
    public static final Supplier<SwordItem> METAL_SWORD = ITEMS.register("espada_metal",
            () -> new EspadaElemental(NexusToolTier.METAL,new Item.Properties().attributes(SwordItem.createAttributes(NexusToolTier.METAL,3,-2.4F)), Elementos.METAL));
    public static final Supplier<SwordItem> NATUREZA_SWORD = ITEMS.register("espada_natureza",
            () -> new EspadaElemental(NexusToolTier.NATUREZA,new Item.Properties().attributes(SwordItem.createAttributes(NexusToolTier.NATUREZA,3,-2.4F)), Elementos.NATUREZA));
    public static final Supplier<SwordItem> ROCHA_SWORD = ITEMS.register("espada_rocha",
            () -> new EspadaElemental(NexusToolTier.ROCHA,new Item.Properties().attributes(SwordItem.createAttributes(NexusToolTier.ROCHA,3,-2.4F)), Elementos.ROCHA));
    public static final Supplier<Item> MOLDE_ESPADA = ITEMS.register("molde_espada",
            () -> new ModeloForja(new Item.Properties(), NexusTags.Items.ESPADA_ELEMENTAL,0, RaridadeMolde.COMUM,2000));
    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
