package com.NuclearFusion.item.arms.tool.mace;


import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import com.NuclearFusion.item.arms.tool.NaturalistiaShovel;

public class NimlosilverMace extends NaturalistiaShovel {

    public NimlosilverMace() {
        super(1.5F, -3.0F, new ModItemTier(3, 300, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 3, ItemRegister.defaultBuilder());

    }
}
