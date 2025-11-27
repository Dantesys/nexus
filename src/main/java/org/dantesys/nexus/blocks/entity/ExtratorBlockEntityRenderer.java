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

public class ExtratorBlockEntityRenderer  implements BlockEntityRenderer<ExtratorBlockEntity> {
    private final ItemRenderer itemRenderer;

    public ExtratorBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(@NotNull ExtratorBlockEntity be, float partialTicks, PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        int seed = (int) (be.getBlockPos().asLong());
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.55f, 0.5f);
        float tamanho = (float) (0.75-(0.75*((double) be.getProgress() /be.getMaxProgress())));
        poseStack.scale(tamanho, tamanho, tamanho);
        ItemStack stack = be.inventory.getStackInSlot(0);
        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, getLightLevel(be.getLevel(),
                be.getBlockPos()), OverlayTexture.NO_OVERLAY, poseStack, bufferSource, be.getLevel(), seed);
        poseStack.popPose();
    }
    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
