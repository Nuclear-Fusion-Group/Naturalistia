package com.NuclearFusion.item.arms;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
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
public class NimlosilverShovel extends ToolItem {

    private static Item.Properties addHarvestLevel(Item.Properties properties, int harvestLevel) {
        return properties.addToolType(ToolType.HOE, harvestLevel).addToolType(ToolType.PICKAXE, harvestLevel)
                .addToolType(ToolType.SHOVEL, harvestLevel);
    }

    private static int harvestLevel;

    public NimlosilverShovel() {
        super(1.5F, -3.0F, new ModItemTier(3, 1000, 10.0F, 1.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), Collections.emptySet(), addHarvestLevel(ItemRegister.defaultBuilder(), 3));
        harvestLevel = 3;
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
            if (context.getFace() == Direction.DOWN){
                return ActionResultType.PASS;
            }
            //变耕地
            if (resultToSet!=null && world.isAirBlock(blockPos.up())){
                world.playSound(player,blockPos,SoundEvents.ITEM_HOE_TILL,SoundCategory.BLOCKS,1.0F,1.0F);
                if (!world.isRemote){
                    world.setBlockState(blockPos,resultToSet, Constants.BlockFlags.DEFAULT_AND_RERENDER);
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

    public static int getHarvestLevel() {
        return harvestLevel;
    }
}
