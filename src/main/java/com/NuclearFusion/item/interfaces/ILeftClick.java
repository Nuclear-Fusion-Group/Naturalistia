package com.NuclearFusion.item.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

/**
 * @author DustW
 */
public interface ILeftClick {
    void onLeftClick(ItemStack stack, PlayerEntity player);
}
