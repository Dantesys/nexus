package org.dantesys.nexus.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.dantesys.nexus.blocks.NexusBlocks;

import static org.dantesys.nexus.Nexus.MODID;

public class NexusCreativeTab {
    public static final DeferredRegister<CreativeModeTab> NEXUS_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NEXUS_TAB = NEXUS_TABS.register("nexus_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.nexus_itens"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> NexusItems.FRAGMENTO_FOGO.get().getDefaultInstance())
            .displayItems((parameters, output) -> NexusItems.ITEMS.getEntries().forEach(item -> {
                if(NexusBlocks.BLOCKS.getEntries().stream().noneMatch(block -> item.getId().equals(block.getId()))) {
                    output.accept(item.get());
                }
            })).build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NEXUS_BLOCK_TAB = NEXUS_TABS.register("nexus_block_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.nexus_blocks"))
            .withTabsBefore(NEXUS_TAB.getId())
            .icon(() -> NexusBlocks.INFUSOR.get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                NexusBlocks.BLOCKS.getEntries().forEach(item -> output.accept(item.get()));
            }).build());

    public static void register(IEventBus bus){
        NEXUS_TABS.register(bus);
    }
}
