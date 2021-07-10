package com.NuclearFusion.block.crop;

import com.NuclearFusion.block.Crops;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;

public class Skyfaery extends CropsBlock {

    public Skyfaery() {
        super(Crops.CropsBuilder());
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ItemRegister.ITEM_SKYFAERY.get();
    }
}
