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
        // base transforms: desloca para o centro do bloco
        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.scale(0.5f, 0.5f, 0.5f);
       // tempo contínuo para rotação suave
        if(Minecraft.getInstance().level == null)return;
        double time = (Minecraft.getInstance().level.getGameTime() + partialTicks);
        // desenhar 4 pedestais ao redor (posições em um quadrado)
        double radius = 0.35;
        int seed = (int) (be.getBlockPos().asLong()); // seed determinística por posição (ou use outro inteiro)
        net.minecraft.world.level.Level level = Minecraft.getInstance().level;
        for (int i = 0; i < 5; i++) {
            double angle = time * 0.02 + i * Math.PI / 2.0;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;

            poseStack.pushPose();
            poseStack.translate(x, -0.25, z); // y ajustável para altura do pedestal
            poseStack.scale(0.5f, 0.5f, 0.5f);
            float bob = (float)Math.sin(time * 0.1 + i) * 0.05f; // sobe e desce
            poseStack.translate(0, bob, 0);
            poseStack.mulPose(Axis.YP.rotationDegrees((float)(time * 4 + i * 90)));
            // desenha um item flutuando girando sobre o pedestal (se houver)
            ItemStack stack = be.inventory.getStackInSlot(i);
            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, getLightLevel(be.getLevel(),
                    be.getBlockPos()), OverlayTexture.NO_OVERLAY, poseStack, bufferSource, be.getLevel(), 1);
            poseStack.popPose();
        }
        // Se todos os 4 presentes: anima convergência ao centro
        boolean all = true;
        for (int i = 0; i < 4; i++) if (be.inventory.getStackInSlot(i).isEmpty()) all = false;
        if (all) {
            float progress = (float)be.getProgress() / be.getMaxProgress(); // 0..1
            // lerp radius de 0.35 -> 0.05
            double curRadius = 0.35 * (1 - progress) + 0.05 * progress;
            // desenhar 4 itens em nova posição convergindo
            for (int i = 0; i < 4; i++) {
                double angle = time * 0.02 + i * Math.PI / 2.0;
                double x = Math.cos(angle) * curRadius;
                double z = Math.sin(angle) * curRadius;

                poseStack.pushPose();
                poseStack.translate(x, -0.15 + progress * 0.25, z); // sobem levemente conforme convergem
                poseStack.scale(0.28f * (1 - progress * 0.5f), 0.28f * (1 - progress * 0.5f), 0.28f * (1 - progress * 0.5f));
                poseStack.mulPose(Axis.YP.rotationDegrees((float)(time * 6 + i * 90)));
                ItemStack stack = be.inventory.getStackInSlot(i);
                itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND,
                        combinedLight, combinedOverlay, poseStack, bufferSource, level,seed);
                poseStack.popPose();
            }

            // se complete e sucesso: desenha o núcleo no centro
            if (be.getSuccess()) {
                poseStack.pushPose();
                float bob = (float)Math.sin(time * 0.1) * 0.07f;
                poseStack.translate(0, -0.15 + 0.4 + bob, 0); // central + elevação
                poseStack.mulPose(Axis.YP.rotationDegrees((float)(time * 10)));
                ItemStack core = be.inventory.getStackInSlot(4);
                if (!core.isEmpty()) {
                    poseStack.scale(0.6f, 0.6f, 0.6f);
                    itemRenderer.renderStatic(core, ItemDisplayContext.GROUND,
                            combinedLight, combinedOverlay, poseStack, bufferSource, level,seed);
                }
                poseStack.popPose();
            }
        }

        poseStack.popPose();
    }
    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
