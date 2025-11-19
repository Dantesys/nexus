package org.dantesys.nexus.gui.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.dantesys.nexus.gui.menu.InfusorMenu;
import org.jetbrains.annotations.NotNull;

public class InfusorScreen extends AbstractContainerScreen<InfusorMenu> {
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath("nexus", "textures/gui/infusor_gui.png");
    private final int guiWidth = 176;
    private final int guiHeight = 166;

    public InfusorScreen(InfusorMenu menu, Inventory inv, Component title) {
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
        // barra de progresso
        renderProgressArrow(guiGraphics,x,y);
    }
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(menu.getTextureFromSlot(0),x + 61, y + 15, 0, 0, menu.getLoading(), menu.getLoading(), 27, 27);
            guiGraphics.blit(menu.getTextureFromSlot(1),x + 114, y + 15, 27, 0, -menu.getLoading(), menu.getLoading(), 27, 27);
            guiGraphics.blit(menu.getTextureFromSlot(2),x + 61, y + 68, 0, 27, menu.getLoading(), -menu.getLoading(), 27, 27);
            guiGraphics.blit(menu.getTextureFromSlot(3),x + 114, y + 68, 27, 27, -menu.getLoading(), -menu.getLoading(), 27, 27);
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