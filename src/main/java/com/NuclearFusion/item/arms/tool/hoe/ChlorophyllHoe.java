package com.NuclearFusion.item.arms.tool.hoe;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import com.NuclearFusion.item.arms.tool.NaturalistiaShovel;
import net.minecraft.item.SwordItem;

public class ChlorophyllHoe extends SwordItem {
    public ChlorophyllHoe() {

        super(new ModItemTier(3, 300, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_CHLOROPHYLL.get()), 3, -3.0F, ItemRegister.defaultBuilder());
    }
}
