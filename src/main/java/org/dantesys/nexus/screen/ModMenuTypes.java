package org.dantesys.nexus.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.dantesys.nexus.Nexus;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Nexus.MOD_ID);

    public static final RegistryObject<MenuType<ColetorMenu>> COLETOR_MENU = registerMenuType(ColetorMenu::new,"coletor_menu");

    public static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> containerFactory,String nome){
        return MENUS.register(nome, () -> IForgeMenuType.create(containerFactory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
