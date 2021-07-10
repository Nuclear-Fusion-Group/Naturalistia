package com.NuclearFusion.block.crop;

import com.NuclearFusion.block.Crops;
import com.NuclearFusion.item.Items;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;

public class Chilipepper extends CropsBlock {

    public Chilipepper() {
        super(Crops.CropsBuilder());
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return Items.chilipepper.get();
    }
}
