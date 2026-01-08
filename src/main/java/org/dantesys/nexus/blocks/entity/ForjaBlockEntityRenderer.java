package org.dantesys.nexus.blocks.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.dantesys.nexus.blocks.ForjaBlock;
import org.jetbrains.annotations.NotNull;

import static org.dantesys.nexus.blocks.entity.ForjaBlockEntity.SLOT_MOLDE;

public class ForjaBlockEntityRenderer implements BlockEntityRenderer<ForjaBlockEntity> {
    private final ItemRenderer itemRenderer;

    public ForjaBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(@NotNull ForjaBlockEntity be, float partialTicks, PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        if(be.getBlockState().getValue(ForjaBlock.FORMADO)){
            //poseStack.translate(1, 0.325, 0.2578125);
            ItemStack molde = be.inventory.getStackInSlot(SLOT_MOLDE);
            Direction dir = be.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
            poseStack.pushPose();
            poseStack.translate(0.5, 0, 0.5);
            poseStack.mulPose(Axis.YP.rotationDegrees(-dir.toYRot()));
            poseStack.translate(
                    0,
                    0.5,
                    0.505
            );
            poseStack.mulPose(Axis.YP.rotationDegrees(180));
            poseStack.scale(0.5F, 0.5F, 0.5F);
            itemRenderer.renderStatic(
                    molde,
                    ItemDisplayContext.FIXED,
                    getLightLevel(be.getLevel(), be.getBlockPos()),
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    bufferSource,
                    be.getLevel(),
                    0
            );
            poseStack.popPose();
        }
    }
    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
