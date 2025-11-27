package org.dantesys.nexus.gui.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.dantesys.nexus.gui.menu.ExtratorMenu;
import org.jetbrains.annotations.NotNull;

public class ExtratorScreen  extends AbstractContainerScreen<ExtratorMenu> {
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath("nexus", "textures/gui/extrator_gui.png");
    private static final ResourceLocation ARROW = ResourceLocation.fromNamespaceAndPath("nexus", "textures/gui/extrator_arrow_gui.png");
    private final int guiWidth = 176;
    private final int guiHeight = 166;

    public ExtratorScreen(ExtratorMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = guiWidth;
        this.imageHeight = guiHeight;
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics,mouseX,mouseY,partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        // desenhar textura de fundo
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(BACKGROUND, x, y, 0, 0, this.imageWidth, this.imageHeight);
        renderProgressArrow(guiGraphics,x,y);
    }
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(ARROW,x + 64, y + 38, 0, 0, menu.getLoading(),4, 49, 4);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // título
        guiGraphics.drawString(this.font, this.title, 8, 6, 4210752, false);

        // inventário do jogador
        guiGraphics.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 94, 4210752, false);
    }
}
