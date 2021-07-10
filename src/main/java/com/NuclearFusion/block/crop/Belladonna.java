package com.NuclearFusion.block.crop;

import com.NuclearFusion.block.Crops;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;

import javax.annotation.Nonnull;

public class Belladonna extends CropsBlock {
    public Belladonna() {
        super(Crops.CropsBuilder());
    }

    @Nonnull
    @Override
    protected IItemProvider getSeedsItem() {
        return ItemRegister.belladonna.get();
    }
}
