package com.NuclearFusion.item.arms;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;

public class NimlosilverSword extends SwordItem {

    public NimlosilverSword() {
        super(new ModItemTier(3, 1000, 10.0F, 3.0F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 1, -2.4F, ItemRegister.defaultBuilder());
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.attackEntityFrom(DamageSource.DRAGON_BREATH, EnchantmentHelper.getSweepingDamageRatio(attacker) * 5);
        return super.hitEntity(stack, target, attacker);
    }
}
