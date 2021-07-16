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

    @Override
    public int getMaxAge() {
        return 5;
    }

    @Nonnull
    @Override
    protected IItemProvider getSeedsItem() {
        return ItemRegister.ITEM_BELLADONNA_SEED.get();
    }
}
