package org.dantesys.nexus;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.dantesys.nexus.blocks.NexusBlocks;
import org.dantesys.nexus.blocks.entity.InfusorBlockEntityRenderer;
import org.dantesys.nexus.datagen.NexusDatagen;
import org.dantesys.nexus.gui.NexusMenus;
import org.dantesys.nexus.gui.menu.InfusorMenu;
import org.dantesys.nexus.gui.screen.InfusorScreen;
import org.dantesys.nexus.items.NexusCreativeTab;
import org.dantesys.nexus.items.NexusItems;
import org.slf4j.Logger;

import static org.dantesys.nexus.gui.NexusMenus.INFUSOR_MENU;


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Nexus.MODID)
public class Nexus
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "nexus";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Nexus(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        NexusMenus.register(modEventBus);
        // Register the Deferred Register to the mod event bus so blocks get registered
        NexusBlocks.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        NexusItems.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        NexusCreativeTab.register(modEventBus);
        modEventBus.addListener(NexusDatagen::gatherData);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Nexus) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            BlockEntityRenderers.register(NexusBlocks.INFUSOR_BE.get(), InfusorBlockEntityRenderer::new);
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
        @SubscribeEvent // on the mod event bus only on the physical client
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(INFUSOR_MENU.get(), InfusorScreen::new);
        }
        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(NexusBlocks.INFUSOR_BE.get(), InfusorBlockEntityRenderer::new);
        }
    }
}
