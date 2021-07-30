package com.NuclearFusion.item.arms.tool.hoe;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.item.SwordItem;

public class ChlorophyllHoe extends SwordItem {
    public ChlorophyllHoe() {
        super(new ModItemTier(3, 300, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_CHLOROPHYLL.get()), 3, -3.0F, ItemRegister.defaultBuilder());
    }

    /*
      TODO 右键发射受重力影响的爆炸种子，爆炸种子会造成武器伤害100%的小范围伤害
      提示语句：投出爆炸种子
     */
}
