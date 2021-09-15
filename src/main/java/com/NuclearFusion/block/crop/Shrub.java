package com.NuclearFusion.block.crop;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public abstract class Shrub extends BushBlock implements IGrowable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_5;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.67D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.34D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.01D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.68D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 13.35D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    public Shrub(AbstractBlock.Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), Integer.valueOf(0)));
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

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    public boolean isMaxAge(BlockState state) {
        return state.get(this.getAgeProperty()) >= this.getMaxAge();
    }

    protected int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.nextInt(worldIn.rand, 2, 5);
    }

    public BlockState withAge(int age) {
        return this.getDefaultState().with(this.getAgeProperty(), Integer.valueOf(age));
    }

    public boolean ticksRandomly(BlockState state) {
        return !this.isMaxAge(state);
    }

    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!worldIn.isAreaLoaded(pos, 1)) return;
        if (worldIn.getLightSubtracted(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = getGrowthChance(this, worldIn, pos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0)) {
                    worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }

    }

    protected static float getGrowthChance(Block blockIn, IBlockReader worldIn, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockpos = pos.down();

        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                BlockState blockstate = worldIn.getBlockState(blockpos.add(i, 0, j));
                if (blockstate.canSustainPlant(worldIn, blockpos.add(i, 0, j), net.minecraft.util.Direction.UP, (net.minecraftforge.common.IPlantable) blockIn)) {
                    f1 = 1.0F;
                    if (blockstate.isFertile(worldIn, pos.add(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof RavagerEntity && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldIn, entityIn)) {
            worldIn.destroyBlock(pos, true, entityIn);
        }

        super.onEntityCollision(state, worldIn, pos, entityIn);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return (worldIn.getLightSubtracted(pos, 0) >= 8 || worldIn.canSeeSky(pos)) && super.isValidPosition(state, worldIn, pos);
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.matchesBlock(Blocks.FARMLAND);
    }

    protected IItemProvider getSeedsItem() {
        return Items.WHEAT_SEEDS;
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(this.getSeedsItem());
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return !this.isMaxAge(state);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        this.grow(worldIn, pos, state);
    }

    public void grow(World worldIn, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        worldIn.setBlockState(pos, this.withAge(i), 2);
    }
}
