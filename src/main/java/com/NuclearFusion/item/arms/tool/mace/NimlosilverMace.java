package com.NuclearFusion.item.arms.tool.mace;


import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.api.NimlosilverBuff;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.registry.Registry;

public class NimlosilverMace extends NimlosilverBuff {
    public NimlosilverMace() {
        super(new ModItemTier(3, 300, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 3, -3.0F, new Item.Properties());
    }

    //TODO ���������Ҫ�Դ���ħ����Ʒ�ܶ�Ļ� ���Կ��ǽ����ܶ�����ȥ
    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemRegister.creativeTab) {
            ItemStack itemstack = this.getItem().getDefaultInstance();

            CompoundNBT nbt = new CompoundNBT();
            ListNBT listNBT = new ListNBT();
            EnchantmentData stackIn = new EnchantmentData(Enchantments.KNOCKBACK, 1);

            //��Ӹ�ħ��ȼ�
            nbt.putString("id", String.valueOf(Registry.ENCHANTMENT.getKey(stackIn.enchantment)));
            nbt.putShort("lvl", (short) stackIn.enchantmentLevel);

            listNBT.add(nbt);
            itemstack.getOrCreateTag().put("Enchantments", listNBT);

            items.add(itemstack);
        }
        super.fillItemGroup(group, items);
    }

}
