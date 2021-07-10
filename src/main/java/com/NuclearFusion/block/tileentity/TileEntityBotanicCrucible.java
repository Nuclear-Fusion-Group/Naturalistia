package com.NuclearFusion.block.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBotanicCrucible extends TileEntity implements ITickableTileEntity {

    public TileEntityBotanicCrucible() {
        super(TileEntityRegistry.TILE_ENTITY_BOTANIC_CRUCIBLE.get());
    }

    @Override
    public void tick() {

    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {


        return super.write(compound);
    }
}
