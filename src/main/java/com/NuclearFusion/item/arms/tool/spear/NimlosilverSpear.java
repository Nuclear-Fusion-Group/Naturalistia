package com.NuclearFusion.item.arms.tool.spear;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.api.NimlosilverBuff;
import com.NuclearFusion.item.ItemRegister;

public class NimlosilverSpear extends NimlosilverBuff {
    public NimlosilverSpear() {
        super(new ModItemTier(3, 250, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 3, -2.8F, ItemRegister.defaultBuilder());
    }
    //TODO 1.5倍攻击距离
}
