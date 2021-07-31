package com.NuclearFusion.client.handler;

import com.NuclearFusion.block.tileentity.TileEntityBotanicCrucible;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.profiler.IProfiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Part of the codes are written by Vazkii, in Botania repository on GitHub.
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public final class HUDHandler {
    private HUDHandler(){}

    @SubscribeEvent
    public static void onDrawScreenPost(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        IProfiler profiler = mc.getProfiler();
        ItemStack main = mc.player.getHeldItemMainhand();
        ItemStack offhand = mc.player.getHeldItemOffhand();
        MatrixStack ms = event.getMatrixStack();

        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            profiler.startSection("naturalistia-hud");
            RayTraceResult pos = mc.objectMouseOver;

            if(pos != null){
                BlockPos bpos = pos.getType() == RayTraceResult.Type.BLOCK ? ((BlockRayTraceResult) pos).getPos() : null;
                BlockState state = bpos != null ? mc.world.getBlockState(bpos) : null;
                Block block = state == null ? null : state.getBlock();
                TileEntity tile = bpos != null ? mc.world.getTileEntity(bpos) : null;

                if(tile instanceof TileEntityBotanicCrucible) ((TileEntityBotanicCrucible) tile).renderHUD(mc, ms);
            }

        }
    }
}