package com.NuclearFusion.block.crop;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public abstract class Shrub extends CropsBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_5;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.67D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.34D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.01D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.68D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 13.35D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    public Shrub(AbstractBlock.Properties builder) {
        super(builder);
    }

    /**
     * 用于实现右键收获
     *
     * @param state
     * @param worldIn
     * @param pos
     * @param player
     * @param handIn
     * @param hit
     * @return
     */
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (this.getAge(state) == getMaxAge()) {
            //重置作物状态
            worldIn.setBlockState(pos, this.stateContainer.getBaseState().with(this.getAgeProperty(), Integer.valueOf(0)));

            //获取一个0~3的随机数
            int random = (int) (Math.random() * (3 + 1));
            for (int i = 0; i < random; i++) {
                worldIn.addEntity(new ItemEntity(worldIn, player.getPosX(), player.getPosY(), player.getPosZ(), this.getSeedsItem().asItem().getDefaultInstance()));
            }

            random = (int) (Math.random() * (3 + 1));
            for (int i = 0; i < random; i++) {
                worldIn.addEntity(new ItemEntity(worldIn, player.getPosX(), player.getPosY(), player.getPosZ(), getItem()));
            }

            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    /**
     * 获取果实
     *
     * @return 作物的果实
     */
    //TODO 以后可能将这个拆出去做成接口
    public abstract ItemStack getItem();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    public int getMaxAge() {
        return 5;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }
}
