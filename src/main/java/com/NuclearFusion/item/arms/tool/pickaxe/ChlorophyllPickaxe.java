package com.NuclearFusion.item.arms.tool.pickaxe;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ChlorophyllPickaxe extends PickaxeItem {

    public ChlorophyllPickaxe() {
        super(new ModItemTier(3, 1951, 3, 8, 10, ItemRegister.ITEM_INGOT_CHLOROPHYLL.get()), 0, -2.4F, ItemRegister.defaultBuilder());
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        worldIn.destroyBlock(pos, true, entityLiving);
        //TODO 添加经验与时运支持
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    //TODO 添加配置文件黑名单
    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (state.getHarvestLevel() <= 3 && state.getHarvestTool() == ToolType.PICKAXE) {
            BlockPos.getProximitySortedBoxPositions(pos, 1, 1, 1).filter(blockPos1 -> worldIn.getBlockState(blockPos1) == state).forEach(blockPos1 -> this.onBlockDestroyed(player.getHeldItemMainhand().getStack(), worldIn, state, blockPos1, player));
            return true;
        }
        return super.canPlayerBreakBlockWhileHolding(state, worldIn, pos, player);
    }
}
