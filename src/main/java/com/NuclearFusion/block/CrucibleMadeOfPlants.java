package com.NuclearFusion.block;

import com.NuclearFusion.api.IHasTileEntity;
import com.NuclearFusion.block.tileentity.CrucibleMadeOfPlantsTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.extensions.IForgeTileEntity;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrucibleMadeOfPlants extends Block implements IHasTileEntity<TileEntity> {
    public CrucibleMadeOfPlants() {
        super(Blocks.defaultBuilder());
    }

    @Override
    public TileEntityType<?> getTileType() {
        return new CrucibleMadeOfPlantsTileEntity().getType();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CrucibleMadeOfPlantsTileEntity();
    }
}
