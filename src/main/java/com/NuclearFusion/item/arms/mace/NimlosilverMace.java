package com.NuclearFusion.item.arms.mace;


import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.item.SwordItem;


import java.util.function.Supplier;

public class NimlosilverMace extends SwordItem {

    public NimlosilverMace() {
        super(new ModItemTier(3, 300, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 1, -3.4F, ItemRegister.defaultBuilder());

    }
}
