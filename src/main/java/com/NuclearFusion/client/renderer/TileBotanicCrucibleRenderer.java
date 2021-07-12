package com.NuclearFusion.client.renderer;

import com.NuclearFusion.block.tileentity.TileEntityBotanicCrucible;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.FluidBlockRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.FluidContainerColorer;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.EmptyFluidHandler;
import org.lwjgl.opengl.GL11;
import slimeknights.mantle.client.model.fluid.FluidCuboid;
import slimeknights.mantle.client.render.FluidRenderer;

import java.util.HashMap;

public class TileBotanicCrucibleRenderer extends TileEntityRenderer<TileEntityBotanicCrucible> {

    public TileBotanicCrucibleRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityBotanicCrucible tileEntity, float partialTicks, MatrixStack matrices, IRenderTypeBuffer buffer, int light, int combinedOverlayIn) {
        IFluidHandler fluidHandler = tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(EmptyFluidHandler.INSTANCE);
        FluidStack fluid = fluidHandler.getFluidInTank(0);
        int capacity =fluidHandler.getTankCapacity(0);
        if (!fluid.isEmpty()) {

            // capacity for gives us the minimum amount to start rendering in this segement
            // render nothing beyond the base capacity
            int amount = fluid.getAmount() - capacity;
            if (amount <= 0) {
                // get the model pair, if the capacity is above the capacity per cistern, use the overfull model (no top face)
                BlockState state = tileEntity.getBlockState();

                FluidAttributes attributes = fluid.getFluid().getAttributes();
                TextureAtlasSprite still = FluidRenderer.getBlockSprite(attributes.getStillTexture(fluid));
                IVertexBuilder builder = buffer.getBuffer(FluidRenderer.RENDER_TYPE);
                int color = attributes.getColor(fluid);
                light = FluidRenderer.withBlockLight(light, attributes.getLuminosity(fluid));

                FluidCuboid center = new FluidCuboid(new Vector3f(0.0625f, 0.0625f, 0.0625f), new Vector3f(1-0.0625f, ((0.5f-0.0625f)*((float) fluid.getAmount())/((float) capacity)) + 0.0625f, 1-0.0625f), new HashMap<Direction, FluidCuboid.FluidFace>(){
                    @Override
                    public FluidCuboid.FluidFace get(Object key) {
                        return FluidCuboid.FluidFace.NORMAL;
                    }
                });

                FluidRenderer.renderCuboid(matrices, builder, center, still, still, center.getFrom(), center.getTo(), color, light, false);
            }
        }
    }
}
