package com.NuclearFusion.entity.projectile.renderer;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.entity.projectile.HookClawEntity;
import com.NuclearFusion.entity.projectile.model.HookClawEntityModel;
import com.NuclearFusion.item.ItemRegister;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class HookClawEntityRenderer extends EntityRenderer<HookClawEntity> {
    private final Model model = new HookClawEntityModel();

    public HookClawEntityRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(HookClawEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        PlayerEntity playerEntity = (PlayerEntity) entityIn.getShooter();
        matrixStackIn.push();
        matrixStackIn.push();
        //方向控制
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 180));
        //桶形翻滚
//        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
        //仰俯控制
//        matrixStackIn.rotate(Vector3f.XP.rotation(limSwing));
        //将模型向下一格
        matrixStackIn.translate(0.0D, -1.0D, 0.0D);

        IVertexBuilder iVertexBuilder = bufferIn.getBuffer(this.model.getRenderType(this.getEntityTexture(entityIn)));
        this.model.render(matrixStackIn, iVertexBuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStackIn.pop();
        //锁链渲染
        if (playerEntity != null) {
            int i = 1;
            ItemStack itemstack = playerEntity.getHeldItemMainhand();
            if (itemstack.getItem() != ItemRegister.THORNY_HOOK.get()) {
                i = -i;
            }

            //摆动幅度
            float swingProgress = playerEntity.getSwingProgress(partialTicks);
            //计算摆动后的位置
            float newSwingProgress = MathHelper.sin(MathHelper.sqrt(swingProgress) * (float) Math.PI);
            //将画面反转并将偏移量平滑处理
            float newYawOffset = MathHelper.lerp(partialTicks, playerEntity.prevRenderYawOffset, playerEntity.renderYawOffset) * ((float) Math.PI / 180F);
            double yawSinOffset = MathHelper.sin(newYawOffset);
            double yawCosOffset = MathHelper.cos(newYawOffset);
            //手持物品偏移量
            double itemOffset = (double) i * 0.35D;
            double playerX;
            double playerZ;
            double playerY;
            float eyeHeight;
            //当看向其他玩家时执行
            if ((this.renderManager.options == null || this.renderManager.options.getPointOfView().func_243192_a()) && playerEntity == Minecraft.getInstance().player) {
                //视角大小
                double fov = this.renderManager.options.fov / 100.0D;
                Vector3d vector3d = new Vector3d((double) i * -0.5D * fov, -0.045D * fov, 0.4D);
                //画面反转并取平滑值
                vector3d = vector3d.rotatePitch(-MathHelper.lerp(partialTicks, playerEntity.prevRotationPitch, playerEntity.rotationPitch) * ((float) Math.PI / 180F));
                vector3d = vector3d.rotateYaw(-MathHelper.lerp(partialTicks, playerEntity.prevRotationYaw, playerEntity.rotationYaw) * ((float) Math.PI / 180F));
                //跟随玩家的摇晃来渲染
                vector3d = vector3d.rotateYaw(newSwingProgress * 0.5F);
                vector3d = vector3d.rotatePitch(-newSwingProgress * 0.7F);
                //取将要移动的坐标与现在坐标的平滑值
                playerX = MathHelper.lerp(partialTicks, playerEntity.prevPosX, playerEntity.getPosX()) + vector3d.x;
                playerY = MathHelper.lerp(partialTicks, playerEntity.prevPosY, playerEntity.getPosY()) + vector3d.y;
                playerZ = MathHelper.lerp(partialTicks, playerEntity.prevPosZ, playerEntity.getPosZ()) + vector3d.z;
                //看向的高度
                eyeHeight = playerEntity.getEyeHeight();
            } else {
                //取将要移动的坐标与现在坐标的平滑值
                playerX = MathHelper.lerp(partialTicks, playerEntity.prevPosX, playerEntity.getPosX()) - yawCosOffset * itemOffset - yawSinOffset * 0.8D;
                playerY = playerEntity.prevPosY + (double) playerEntity.getEyeHeight() + (playerEntity.getPosY() - playerEntity.prevPosY) * (double) partialTicks - 0.45D;
                playerZ = MathHelper.lerp(partialTicks, playerEntity.prevPosZ, playerEntity.getPosZ()) - yawSinOffset * itemOffset + yawCosOffset * 0.8D;
                eyeHeight = playerEntity.isCrouching() ? -0.1875F : 0.0F;
            }
            //获取本实体的平滑坐标值
            double entityX = MathHelper.lerp(partialTicks, entityIn.prevPosX, entityIn.getPosX());
            double entityY = MathHelper.lerp(partialTicks, entityIn.prevPosY, entityIn.getPosY()) + 0.25D;
            double entityZ = MathHelper.lerp(partialTicks, entityIn.prevPosZ, entityIn.getPosZ());
            float x = (float) (playerX - entityX);
            float y = (float) (playerY - entityY) + eyeHeight;
            float z = (float) (playerZ - entityZ);
            IVertexBuilder ivertexbuilder1 = bufferIn.getBuffer(RenderType.getLines());
            Matrix4f matrix4f1 = matrixStackIn.getLast().getMatrix();
            //渲染锁链
            for (int k = 0; k < 16; ++k) {
                chainsBuilder(x, y, z, ivertexbuilder1, matrix4f1, shear(k));
                chainsBuilder(x, y, z, ivertexbuilder1, matrix4f1, shear(k + 1));
            }

        }
        matrixStackIn.pop();

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(HookClawEntity entity) {
        return new ResourceLocation(Naturalistia.MOD_ID, "textures/entity/thorny_hook.png");
    }

    private static float shear(int offset) {
        return (float) offset / 16;
    }

    private static void chainsBuilder(float x, float y, float z, IVertexBuilder ivertexbuilder, Matrix4f matrix4f, float offset) {
        ivertexbuilder.pos(matrix4f, x * offset, y * (offset * offset + offset) * 0.5F + 0.25F, z * offset).color(153, 204, 102, 255).endVertex();
    }
}
