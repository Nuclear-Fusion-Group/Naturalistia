package com.NuclearFusion.item.arms.tool.shovel;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ChlorophyllShovel extends ShovelItem {
    private boolean num = true;
    public ChlorophyllShovel() {
        super(new ModItemTier(3, 1951, 10.0F, 8, 30, ItemRegister.ITEM_INGOT_CHLOROPHYLL.get()), 0, -2.4F, ItemRegister.defaultBuilder());
    }

    //TODO 配置文件黑名单
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (state.getHarvestLevel() <= 3 && state.getHarvestTool() == ToolType.SHOVEL) {
            if (num) {
                if (player instanceof ServerPlayerEntity) {
                    num = !num;
                    BlockPos.getProximitySortedBoxPositions(pos, 2, 2, 2).filter(blockPos1 -> worldIn.getBlockState(blockPos1) == state && blockPos1 != pos).forEach(((ServerPlayerEntity) player).interactionManager::tryHarvestBlock);
                    num = !num;
                    return true;
                }
            }
        }
        return super.canPlayerBreakBlockWhileHolding(state, worldIn, pos, player);
    }
}
