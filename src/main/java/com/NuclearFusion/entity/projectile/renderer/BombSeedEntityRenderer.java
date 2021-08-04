package com.NuclearFusion.entity.projectile.renderer;

import com.NuclearFusion.entity.projectile.BombSeedEntity;
import com.NuclearFusion.entity.projectile.NimlosilverCrossbowArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * @author DustW
 */
public class BombSeedEntityRenderer extends ArrowRenderer<BombSeedEntity> {
    public BombSeedEntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(BombSeedEntity entity) {
        // todo 爆炸种子的renderer
        return TippedArrowRenderer.RES_ARROW;
    }
}
