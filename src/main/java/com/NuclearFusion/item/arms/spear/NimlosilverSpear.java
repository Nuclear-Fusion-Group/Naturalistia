package com.NuclearFusion.item.arms.spear;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import com.NuclearFusion.item.arms.NaturalistiaShovel;

public class NimlosilverSpear extends NaturalistiaShovel {
    public NimlosilverSpear() {
        super(1.5F, -2.8F, new ModItemTier(3, 250, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 3, ItemRegister.defaultBuilder());

    }
}
