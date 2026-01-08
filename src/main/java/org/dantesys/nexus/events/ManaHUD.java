package org.dantesys.nexus.events;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.data.NexusAttachmentType;
import org.dantesys.nexus.magic.ElementalStatics;
import org.dantesys.nexus.magic.MagicStats;
import org.dantesys.nexus.util.Elementos;

@EventBusSubscriber(modid = Nexus.MODID, value = Dist.CLIENT)
public class ManaHUD {

    private static final ResourceLocation BAR =
            ResourceLocation.fromNamespaceAndPath(Nexus.MODID, "textures/gui/mana_bar.png");

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || mc.options.hideGui) return;
        MagicStats stats = player.getData(NexusAttachmentType.MAGIC_STATS);
        GuiGraphics gui = event.getGuiGraphics();
        int x = 10;
        int y = mc.getWindow().getGuiScaledHeight() - 50;
        for (Elementos el : Elementos.values()) {
            ElementalStatics stat = stats.get(el);
            if (stat != null && stat.isUnlocked()) {
                renderManaBar(gui, x, y, el, stat);
                y -= 10;
            }
        }
    }
    private static void renderManaBar(
            GuiGraphics gui,
            int x,
            int y,
            Elementos el,
            ElementalStatics stat
    ) {
        int mana = stat.getMana();
        int max = stat.getMaxMana();

        float pct = Math.min(1f, mana / (float) max);
        int width = Math.min(74, (int)(pct * 74));
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        // Fundo
        gui.blit(BAR, x, y,0, 0,80, 8,80, 8);
        // Barra preenchida
        gui.blit(ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"textures/gui/mana_bar_"+el.getSerializedName()+".png"), x+3, y+2, 0, 0, width, 4);
    }
}