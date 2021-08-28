package com.NuclearFusion.item.arms.tool.spear;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.api.NimlosilverBuff;
import com.NuclearFusion.item.AttackUtils;
import com.NuclearFusion.item.ItemRegister;
import com.NuclearFusion.item.interfaces.ILeftClick;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.common.ForgeMod;

public class NimlosilverSpear extends NimlosilverBuff implements ILeftClick {
    public NimlosilverSpear() {
        super(new ModItemTier(3, 250, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 3, -2.8F, ItemRegister.defaultBuilder());
    }

    @Override
    public void onLeftClick(ItemStack stack, PlayerEntity player) {
        double attackRange = player.getAttribute(ForgeMod.REACH_DISTANCE.get()).getBaseValue();
        AttackUtils.longerAttack(player, attackRange * 1.5, DamageSource.causePlayerDamage(player), AttackUtils.getAttackDamage(stack, player));
    }
}
