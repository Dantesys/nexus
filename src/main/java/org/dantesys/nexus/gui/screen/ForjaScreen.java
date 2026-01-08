package org.dantesys.nexus.gui.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.dantesys.nexus.gui.menu.ForjaMenu;
import org.jetbrains.annotations.NotNull;

public class ForjaScreen extends AbstractContainerScreen<ForjaMenu> {
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath("nexus", "textures/gui/forja_gui.png");
    private static final ResourceLocation LAVA = ResourceLocation.fromNamespaceAndPath("nexus", "textures/gui/lava.png");
    private static final ResourceLocation ACO = ResourceLocation.fromNamespaceAndPath("nexus", "textures/gui/aco.png");
    private static final ResourceLocation DERRETER = ResourceLocation.fromNamespaceAndPath("nexus", "textures/gui/derreter.png");
    private static final ResourceLocation FORJANDO = ResourceLocation.fromNamespaceAndPath("nexus", "textures/gui/forjando.png");
    private final int guiWidth = 176;
    private final int guiHeight = 166;
    public ForjaScreen(ForjaMenu menu, Inventory inv, Component title) {
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
        guiGraphics.blit(LAVA,x + 11, y + 69-menu.getLavaBar(), 0, 0, 10,menu.getLavaBar(), 10, 33);
        guiGraphics.blit(ACO,x + 29, y + 69-menu.getAcoBar(), 0, 0, 10,menu.getAcoBar(), 10, 33);
        if(menu.isDerretendo()){
            guiGraphics.blit(DERRETER,x + 26, y + 16, 0, 0, 16,menu.getDerreteBar(), 16, 16);

        }
        if(menu.isForjando()){
            guiGraphics.blit(FORJANDO,x + 118, y + 40, 0, 0, menu.getForjando(),8, 30, 8);

        }
    }
    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // título
        guiGraphics.drawString(this.font, this.title, 8, 6, 4210752, false);

        // inventário do jogador
        guiGraphics.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 94, 4210752, false);
    }
    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderTooltip(guiGraphics, mouseX, mouseY);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // 🔥 Barra de Lava
        if (isMouseOver(mouseX, mouseY,
                x + 11, y + 36,
                10, 33)) {

            guiGraphics.renderTooltip(
                    this.font,
                    Component.literal(
                            menu.getLava() + " / " + menu.getMaxLava()
                    ),
                    mouseX, mouseY
            );
        }

        // 🧱 Barra de Aço
        if (isMouseOver(
                mouseX, mouseY,
                x + 29, y + 36,
                10, 33)) {

            guiGraphics.renderTooltip(
                    this.font,
                    Component.literal(
                                    menu.getAcoDerretido() + " / " + menu.getMaxAcoDerretido()
                    ),
                    mouseX, mouseY
            );
        }
    }
    private boolean isMouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width
                && mouseY >= y && mouseY <= y + height;
    }

}
