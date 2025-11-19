package org.dantesys.nexus.blocks.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.NotNull;

public class InfusorBlockEntityRenderer implements BlockEntityRenderer<InfusorBlockEntity> {
    private final ItemRenderer itemRenderer;

    public InfusorBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(@NotNull InfusorBlockEntity be, float partialTicks, PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        double time = (Minecraft.getInstance().level.getGameTime() + partialTicks)/10;
        double time2 = (Minecraft.getInstance().level.getGameTime() + partialTicks);
        for (int i = 0; i < 4; i++) {
            double raio = 0.35-(be.getProgress()*(0.35/(be.getMaxProgress()-1)));
            double angle = time2 * 0.02 + i * Math.PI / 2.0;
            double x = Math.cos(angle)*raio;
            double z = Math.sin(angle)*raio;
            int seed = (int) (be.getBlockPos().asLong());
            poseStack.pushPose();
            poseStack.translate(0.5f, 1.25f, 0.5f);
            poseStack.translate(x, 0, z); // y ajustável para altura do pedestal
            poseStack.scale(0.35f, 0.35f, 0.35f);
            float bob = (float)Math.sin(time2 * 0.1 + i) * 0.05f; // sobe e desce
            poseStack.translate(x, bob, z);
            poseStack.mulPose(Axis.YP.rotationDegrees((float)(time2 * 4 + i * 90)));
            // desenha um item flutuando girando sobre o pedestal (se houver)
            ItemStack stack = be.inventory.getStackInSlot(i);
            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, getLightLevel(be.getLevel(),
                    be.getBlockPos()), OverlayTexture.NO_OVERLAY, poseStack, bufferSource, be.getLevel(), seed);
            poseStack.popPose();
        }
        poseStack.pushPose();
        poseStack.translate(0.5f, 1.25f, 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        float bob = (float)Math.sin(time * 0.1) * 0.05f; // sobe e desce
        poseStack.translate(0, bob, 0);
        poseStack.mulPose(Axis.YP.rotationDegrees((float)(time * 90)));
        itemRenderer.renderStatic(be.inventory.getStackInSlot(4), ItemDisplayContext.FIXED, getLightLevel(be.getLevel(),
                be.getBlockPos()), OverlayTexture.NO_OVERLAY, poseStack, bufferSource, be.getLevel(), 1);
        poseStack.popPose();
    }
    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
