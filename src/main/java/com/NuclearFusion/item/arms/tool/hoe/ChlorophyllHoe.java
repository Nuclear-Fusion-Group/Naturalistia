package com.NuclearFusion.item.arms.tool.hoe;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import com.NuclearFusion.item.arms.tool.NaturalistiaShovel;

public class ChlorophyllHoe extends NaturalistiaShovel {
    public ChlorophyllHoe() {

        super(1.5F, -3.0F, new ModItemTier(3, 300, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_CHLOROPHYLL.get()), 3, ItemRegister.defaultBuilder());
    }
}
