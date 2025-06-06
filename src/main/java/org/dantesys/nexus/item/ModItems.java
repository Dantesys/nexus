package org.dantesys.nexus.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.dantesys.nexus.Nexus;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Nexus.MOD_ID);

    public static final RegistryObject<Item> GEMA_NEXUS_VAZIA = ITEMS.register("gema_nexus_vazia", () -> new Item(new Item.Properties().tab(ModCreativeTab.NEXUS_TAB)));
    public static final RegistryObject<Item> GEMA_NEXUS_SOLAR = ITEMS.register("gema_nexus_solar", () -> new Item(new Item.Properties().tab(ModCreativeTab.NEXUS_TAB)));
    public static final RegistryObject<Item> GEMA_NEXUS_LUNAR = ITEMS.register("gema_nexus_lunar", () -> new Item(new Item.Properties().tab(ModCreativeTab.NEXUS_TAB)));
    public static final RegistryObject<Item> GEMA_NEXUS_NETHER = ITEMS.register("gema_nexus_nether", () -> new Item(new Item.Properties().tab(ModCreativeTab.NEXUS_TAB)));
    public static final RegistryObject<Item> GEMA_NEXUS_END = ITEMS.register("gema_nexus_end", () -> new Item(new Item.Properties().tab(ModCreativeTab.NEXUS_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
