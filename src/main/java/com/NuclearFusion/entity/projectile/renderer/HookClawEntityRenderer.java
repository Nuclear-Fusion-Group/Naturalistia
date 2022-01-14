package com.NuclearFusion.entity.projectile.renderer;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.entity.projectile.HookClawEntity;
import com.NuclearFusion.entity.projectile.model.HookClawEntityModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.util.ResourceLocation;

public class HookClawEntityRenderer extends EntityRenderer<HookClawEntity> {
    private static final ResourceLocation textureLoc = Naturalistia.getModelTexture("hook_claw_entity.png");
    private final Model model = new HookClawEntityModel();

    public HookClawEntityRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(HookClawEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn,entityYaw,partialTicks,matrixStackIn,bufferIn,packedLightIn);
        //TODO 添加粒子效果
    }

    @Override
    public ResourceLocation getEntityTexture(HookClawEntity entity) {
        return TippedArrowRenderer.RES_ARROW;
//        return textureLoc;
    }
}
