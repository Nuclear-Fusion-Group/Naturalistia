package com.NuclearFusion.block;

import com.NuclearFusion.api.TileEntityProvider;
import com.NuclearFusion.block.tileentity.TileEntityBotanicCrucible;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockBotanicCrucible extends Block implements TileEntityProvider<TileEntity> {
    public BlockBotanicCrucible() {
        super(BlockRegistry.defaultBuilder());
    }

    @Override
    public TileEntityType<?> getTileType() {
        return new TileEntityBotanicCrucible().getType();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityBotanicCrucible();
    }
}
