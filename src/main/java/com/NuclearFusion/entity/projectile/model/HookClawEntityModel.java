package com.NuclearFusion.entity.projectile.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HookClawEntityModel extends EntityModel<Entity> {
    private final ModelRenderer bone;
    private final ModelRenderer bone_r1;
    private final ModelRenderer bone_r2;

    public HookClawEntityModel() {
        textureWidth = 64;
        textureHeight = 64;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.setTextureOffset(0, 0).addBox(-1.0F, -4.0F, -2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

        bone_r1 = new ModelRenderer(this);
        bone_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone.addChild(bone_r1);
        setRotationAngle(bone_r1, 0.7854F, 0.0F, 0.0F);
        bone_r1.setTextureOffset(0, 5).addBox(-1.5F, -4.0303F, 0.2981F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone_r1.setTextureOffset(0, 7).addBox(0.5F, -4.0303F, 0.2981F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        bone_r2 = new ModelRenderer(this);
        bone_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone.addChild(bone_r2);
        setRotationAngle(bone_r2, 0.0F, -0.7854F, 0.0F);
        bone_r2.setTextureOffset(4, 5).addBox(-1.7374F, -4.5F, -1.8232F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone_r2.setTextureOffset(4, 7).addBox(-1.7374F, -2.5F, -1.8232F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
