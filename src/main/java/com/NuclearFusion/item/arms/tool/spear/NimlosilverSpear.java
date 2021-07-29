package com.NuclearFusion.item.arms.tool.spear;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import com.NuclearFusion.item.arms.tool.NaturalistiaShovel;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;

public class NimlosilverSpear extends SwordItem {
    public NimlosilverSpear() {
        super(new ModItemTier(3, 250, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 3, -2.8F, ItemRegister.defaultBuilder());
    }

}