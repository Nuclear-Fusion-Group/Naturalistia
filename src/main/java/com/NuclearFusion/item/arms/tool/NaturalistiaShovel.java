package com.NuclearFusion.item.arms.tool;

import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import java.util.Collections;

//参考了mek的实现
public class NaturalistiaShovel extends ToolItem {

    private static Item.Properties addHarvestLevel(Item.Properties properties, int harvestLevel) {
        return properties.addToolType(ToolType.HOE, harvestLevel).addToolType(ToolType.PICKAXE, harvestLevel)
                .addToolType(ToolType.SHOVEL, harvestLevel);
    }

    private int harvestLevel;

    public NaturalistiaShovel(float attackDamageIn, float attackSpeedIn, IItemTier tier, int harvestLevel, Properties builderIn) {
        super(attackDamageIn, attackSpeedIn, tier, Collections.emptySet(), addHarvestLevel(builderIn, harvestLevel));
        this.harvestLevel = harvestLevel;
    }

    @Override
    public boolean canHarvestBlock(BlockState state) {
        ToolType harvestTool = state.getHarvestTool();
        if (harvestTool == ToolType.HOE || harvestTool == ToolType.PICKAXE || harvestTool == ToolType.SHOVEL) {
            if (getHarvestLevel() >= state.getHarvestLevel()) {
                return true;
            }
        }
        return super.canHarvestBlock(state);
    }

    @Nonnull
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getPos();
        PlayerEntity player = context.getPlayer();
        ItemStack itemStack = context.getItem();
        BlockState blockState = world.getBlockState(blockPos);
        BlockState resultToSet = blockState.getToolModifiedState(world, blockPos, player, itemStack, ToolType.HOE);

        //潜行右键变草径
        if (player.isSteppingCarefully()) {
            //获取面
            if (context.getFace() == Direction.DOWN) {
                return ActionResultType.PASS;
            }
            BlockState foundResult = blockState.getToolModifiedState(world, blockPos, player, itemStack, ToolType.SHOVEL);
            if (foundResult != null && world.isAirBlock(blockPos.up())) {
                //变草径
                world.playSound(player, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                resultToSet = foundResult;
            } else if (blockState.getBlock() instanceof CampfireBlock && blockState.get(CampfireBlock.LIT)) {
                //灭篝火
                if (!world.isRemote) {
                    world.playEvent(null, Constants.WorldEvents.FIRE_EXTINGUISH_SOUND, blockPos, 0);
                }
                CampfireBlock.extinguish(world, blockPos, blockState);
                resultToSet = blockState.with(CampfireBlock.LIT, false);
            }
        } else {
            if (context.getFace() == Direction.DOWN) {
                return ActionResultType.PASS;
            }
            //变耕地
            if (resultToSet != null && world.isAirBlock(blockPos.up())) {
                world.playSound(player, blockPos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isRemote) {
                    world.setBlockState(blockPos, resultToSet, Constants.BlockFlags.DEFAULT_AND_RERENDER);
                    if (player != null) {
                        itemStack.damageItem(1, player, onBroken -> onBroken.sendBreakAnimation(context.getHand()));
                        return ActionResultType.PASS;
                    }
                }
            }
        }

        if (resultToSet == null) {
            return ActionResultType.PASS;
        }
        if (!world.isRemote) {
            world.setBlockState(blockPos, resultToSet, Constants.BlockFlags.DEFAULT_AND_RERENDER);
            if (player != null) {
                itemStack.damageItem(1, player, onBroken -> onBroken.sendBreakAnimation(context.getHand()));
            }
        }

        return ActionResultType.func_233537_a_(world.isRemote);
    }

    public int getHarvestLevel() {
        return this.harvestLevel;
    }

}

