package com.NuclearFusion.item.arms;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.item.ShovelItem;

public class NimlosilverShovel extends ShovelItem {
    public NimlosilverShovel() {
        super(new ModItemTier(3, 1000, 10.0F, 1.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 1.5F, -3.0F, ItemRegister.defaultBuilder());
    }
}
