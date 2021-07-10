package com.NuclearFusion.block;

import com.NuclearFusion.api.TileEntityProvider;
import com.NuclearFusion.block.tileentity.TileEntityBotanicAltar;
import com.NuclearFusion.block.tileentity.TileEntityBotanicCrucible;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockBotanicAltar extends Block implements TileEntityProvider<TileEntity> {

    protected static final VoxelShape SHAPE =
            VoxelShapes.or(
                    makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
                    makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 9.0D, 15.0D),
                    makeCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 9.0D, 16.0D));


    public BlockBotanicAltar() {
        super(BlockRegistry.defaultBuilder());
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public TileEntityType<?> getTileType() {
        return new TileEntityBotanicAltar().getType();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityBotanicAltar();
    }
}
