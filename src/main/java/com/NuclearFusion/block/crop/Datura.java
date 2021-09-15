package com.NuclearFusion.block.crop;

import com.NuclearFusion.block.Crops;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

public class Datura extends Shrub {
    public Datura() {
        super(Crops.CropsBuilder());
    }

    @Override
    public ItemStack getItem() {
        return ItemRegister.ITEM_DATURA.get().getDefaultInstance();
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ItemRegister.ITEM_DATURA_SEED.get();
    }
}
