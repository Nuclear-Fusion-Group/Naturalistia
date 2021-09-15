package com.NuclearFusion.entity.projectile.renderer;

import com.NuclearFusion.entity.projectile.HookClawEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraft.util.ResourceLocation;

public class HookClawEntityRenderer extends ArrowRenderer<HookClawEntity> {

    public HookClawEntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(HookClawEntity entity) {
        //TODO 钩爪的renderer
        return TippedArrowRenderer.RES_ARROW;
    }
}
