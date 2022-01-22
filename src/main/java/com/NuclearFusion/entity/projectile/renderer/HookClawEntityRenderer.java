package com.NuclearFusion.entity.projectile.renderer;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.entity.projectile.HookClawEntity;
import com.NuclearFusion.entity.projectile.model.HookClawEntityModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class HookClawEntityRenderer extends EntityRenderer<HookClawEntity> {
    private final Model model = new HookClawEntityModel();

    public HookClawEntityRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(HookClawEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        //TODO 实现叶绿锁链
//        PlayerEntity playerentity = (PlayerEntity) entityIn.getShooter();

//        if (playerentity != null) {
//            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getLines());
//            Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
//            Vector3d vector3d = new Vector3d((double) -1 * -0.36D * this.renderManager.options.fov / 100D, -0.045D * this.renderManager.options.fov / 100D, 0.4D);
//            vector3d = vector3d.rotatePitch(-MathHelper.lerp(partialTicks, playerentity.prevRotationPitch, playerentity.rotationPitch) * ((float) Math.PI / 180F));
//            vector3d = vector3d.rotateYaw(-MathHelper.lerp(partialTicks, playerentity.prevRotationYaw, playerentity.rotationYaw) * ((float) Math.PI / 180F));
//            vector3d = vector3d.rotateYaw(MathHelper.sin(MathHelper.sqrt(playerentity.getSwingProgress(partialTicks)) * (float) Math.PI) * 0.5F);
//            vector3d = vector3d.rotatePitch(-MathHelper.sin(MathHelper.sqrt(playerentity.getSwingProgress(partialTicks)) * (float) Math.PI) * 0.7F);
//            float f4 = (float) (MathHelper.lerp(partialTicks, playerentity.prevPosX, playerentity.getPosX()) + vector3d.x - MathHelper.lerp(partialTicks, entityIn.prevPosX, entityIn.getPosX()));
//            float f5 = (float) (MathHelper.lerp(partialTicks, playerentity.prevPosY, playerentity.getPosY()) + vector3d.y - MathHelper.lerp(partialTicks, entityIn.prevPosY, entityIn.getPosY()) + 0.25D) + playerentity.getEyeHeight();
//            float f6 = (float) (MathHelper.lerp(partialTicks, playerentity.prevPosZ, playerentity.getPosZ()) + vector3d.z - MathHelper.lerp(partialTicks, entityIn.prevPosZ, entityIn.getPosZ()));
//            for (int k = 0; k < 16; ++k) {
//                builder(f4, f5, f6, ivertexbuilder, matrix4f, (float) k / (float) 16);
//                builder(f4, f5, f6, ivertexbuilder, matrix4f, (float) k + 1 / (float) 16);
//            }
//        }
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(this.getEntityTexture(entityIn)));
        this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 0F, 0F, 0F, 0F);
        matrixStackIn.pop();

    }

    @Override
    public ResourceLocation getEntityTexture(HookClawEntity entity) {
        return new ResourceLocation(Naturalistia.MOD_ID, "textures/entity/thorny_hook.png");
    }
//
//    private static void builder(float f1, float f2, float f3, IVertexBuilder ivertexBuilder, Matrix4f matrix4f, float f5) {
//        ivertexBuilder.pos(matrix4f, f1 * f5, f2 * (f5 * f5 + f5) * 0.5F + 0.25F, f3 * f5).color(153, 204, 102, 255).endVertex();
//    }
}
