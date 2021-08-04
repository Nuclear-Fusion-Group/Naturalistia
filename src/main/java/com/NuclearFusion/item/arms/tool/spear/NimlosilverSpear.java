package com.NuclearFusion.item.arms.tool.spear;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.api.NimlosilverBuff;
import com.NuclearFusion.item.AttackUtils;
import com.NuclearFusion.item.ItemRegister;
import com.NuclearFusion.item.interfaces.ILeftClick;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class NimlosilverSpear extends NimlosilverBuff implements ILeftClick {
    public NimlosilverSpear() {
        super(new ModItemTier(3, 250, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 3, -2.8F, ItemRegister.defaultBuilder());
    }

    @Override
    public void onLeftClick(ItemStack stack, PlayerEntity player) {
        //TODO 1.5倍攻击距离
    }
}
