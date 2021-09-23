package com.NuclearFusion.block.crop;

import com.NuclearFusion.block.Crops;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

import javax.annotation.Nonnull;

public class Skyfaery extends Shrub {

    public Skyfaery() {
        super(Crops.CropsBuilder());
    }

    @Override
    public ItemStack getItem() {
        return ItemRegister.ITEM_SKYFAERY.get().getDefaultInstance();
    }

    @Nonnull
    @Override
    protected IItemProvider getSeedsItem() {
        return ItemRegister.ITEM_SKYFAERY_SEED.get();
    }
}
