package com.NuclearFusion.item.arms.armor;

import com.NuclearFusion.api.ModArmor;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;

public class Chlorophyll extends ModArmor {
    public Chlorophyll() {
        super("chlorophyll", 40, new int[]{3, 8, 6, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0f, 0f, () -> {
        return Ingredient.fromItems(ItemRegister.ITEM_INGOT_CHLOROPHYLL.get());
        });
    }
}
