package com.NuclearFusion.item.arms.tool.mace;


import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.api.NimlosilverBuff;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class NimlosilverMace extends NimlosilverBuff {
    public NimlosilverMace() {
        super(new ModItemTier(3, 300, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 3, -3.0F, ItemRegister.defaultBuilder());
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemRegister.creativeTab) {
            ItemStack itemstack = this.getItem().getDefaultInstance();
            itemstack.addEnchantment(Enchantments.KNOCKBACK, 1);
            items.add(itemstack);
        }
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        stack.addEnchantment(Enchantments.KNOCKBACK, 1);
        super.onCreated(stack, worldIn, playerIn);
    }
}
