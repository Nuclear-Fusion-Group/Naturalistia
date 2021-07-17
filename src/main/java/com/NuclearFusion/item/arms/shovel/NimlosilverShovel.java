package com.NuclearFusion.item.arms.shovel;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import com.NuclearFusion.item.arms.NaturalistiaShovel;

//参考了mek的实现
public class NimlosilverShovel extends NaturalistiaShovel {
    public NimlosilverShovel() {
        super(1.5F, -3.0F, new ModItemTier(3, 1000, 10.0F, 1.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 3, ItemRegister.defaultBuilder());
    }

}
