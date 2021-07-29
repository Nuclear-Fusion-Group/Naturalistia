package com.NuclearFusion.api;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;

public class NimlosilverBuff extends SwordItem {
    public NimlosilverBuff(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.attackEntityFrom(DamageSource.DRAGON_BREATH, EnchantmentHelper.getSweepingDamageRatio(attacker) * 5);
        return super.hitEntity(stack, target, attacker);
    }
}
