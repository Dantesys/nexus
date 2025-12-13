package org.dantesys.nexus.gui.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import org.dantesys.nexus.gui.menu.BolaCristalMenu;
import org.jetbrains.annotations.NotNull;

public class BolaCristalScreen extends AbstractContainerScreen<BolaCristalMenu> {
    private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath("nexus", "textures/gui/bola_cristal_gui.png");
    private final int guiWidth = 176;
    private final int guiHeight = 166;

    public BolaCristalScreen(BolaCristalMenu menu, Inventory inv, Component title) {
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
        guiGraphics.blit(ResourceLocation.fromNamespaceAndPath("nexus",menu.getBgStats()),x + 7, y + 13, 0, 0, 161, 58, 161, 58);
        draw28(guiGraphics,x+11,y+50,menu.getStatus(0),menu.getTextColor());
        draw28(guiGraphics,x+105,y+50,menu.getStatus(2),menu.getTextColor());
        draw28(guiGraphics,x+43,y+50,menu.getStatus(1),menu.getTextColor());
        draw28(guiGraphics,x+138,y+50,menu.getStatus(3),menu.getTextColor());
    }
    private void draw28(GuiGraphics guiGraphics, int x, int y, Component comp, int cor) {

        final float scale = 20f / 8f; // altura final aproximada = 20px

        int areaW = 28;   // largura fixa da área
        int textW = font.width(comp.getString());

        // largura final após escala
        float scaledW = textW * scale;

        // posição X centralizada
        float centeredX = x + (areaW - scaledW) / 2f;

        // Y original (ou você pode somar um offset se quiser centralizar verticalmente)
        float drawY = y;

        PoseStack pose = guiGraphics.pose();
        pose.pushPose();

        pose.scale(scale, scale, scale);

        guiGraphics.drawString(
                font,
                comp,
                (int)(centeredX / scale),
                (int)(drawY / scale),
                cor
        );

        pose.popPose();
    }


    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {}
}