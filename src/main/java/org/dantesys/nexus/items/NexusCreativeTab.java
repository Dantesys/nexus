package org.dantesys.nexus.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static org.dantesys.nexus.Nexus.MODID;

public class NexusCreativeTab {
    public static final DeferredRegister<CreativeModeTab> NEXUS_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NEXUS_TAB = NEXUS_TABS.register("nexus_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.nexus"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> NexusItems.FRAGMENTO_FOGO.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                NexusItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
            }).build());

    public static void register(IEventBus bus){
        NEXUS_TABS.register(bus);
    }
}
