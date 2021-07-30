package com.NuclearFusion.entity.projectile.renderer;

import com.NuclearFusion.entity.projectile.NimlosilverCrossbowArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * @author DustW
 */
public class NimlosilverCrossbowArrowRenderer extends ArrowRenderer<NimlosilverCrossbowArrowEntity> {
    public NimlosilverCrossbowArrowRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(NimlosilverCrossbowArrowEntity entity) {
        return TippedArrowRenderer.RES_ARROW;
    }
}
