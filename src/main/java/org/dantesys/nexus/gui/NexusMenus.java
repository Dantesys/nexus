package org.dantesys.nexus.gui;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.gui.menu.ExtratorMenu;
import org.dantesys.nexus.gui.menu.InfusorMenu;

import java.util.function.Supplier;

public class NexusMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, Nexus.MODID);

    public static final Supplier<MenuType<InfusorMenu>> INFUSOR_MENU = MENUS.register("infusor_menu", () -> IMenuTypeExtension.create(InfusorMenu::fromNetwork));
    public static final Supplier<MenuType<ExtratorMenu>> EXTRATOR_MENU = MENUS.register("extrator_menu", () -> IMenuTypeExtension.create(ExtratorMenu::fromNetwork));

    public static void register(IEventBus bus){
        MENUS.register(bus);
    }
}
