package com.NuclearFusion.block.crop;

import com.NuclearFusion.block.Crops;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

import javax.annotation.Nonnull;

public class Belladonna extends Shrub {
    public Belladonna() {
        super(Crops.CropsBuilder());
    }

    @Override
    public ItemStack getItem() {
        return ItemRegister.ITEM_BELLADONNA.get().getDefaultInstance();
    }

    @Nonnull
    @Override
    protected IItemProvider getSeedsItem() {
        return ItemRegister.ITEM_BELLADONNA_SEED.get();
    }
}
