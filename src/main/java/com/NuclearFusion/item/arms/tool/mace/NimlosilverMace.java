package com.NuclearFusion.item.arms.tool.mace;


import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.api.NimlosilverBuff;
import com.NuclearFusion.item.ItemRegister;
import com.NuclearFusion.item.arms.tool.NaturalistiaShovel;
import net.minecraft.item.SwordItem;

public class NimlosilverMace extends NimlosilverBuff {
    public NimlosilverMace() {
        super(new ModItemTier(3, 300, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 3, -3.0F, ItemRegister.defaultBuilder());
    }
    //TODO ×Ô´ø»÷ÍË1
}
