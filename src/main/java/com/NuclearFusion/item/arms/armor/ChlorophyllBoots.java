package com.NuclearFusion.item.arms.armor;

import com.NuclearFusion.item.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ChlorophyllBoots extends ArmorItem {
    private static final int MAX_TIME = 1 * 15;
    private int timer = 0;

    public ChlorophyllBoots() {
        super(new Chlorophyll(), EquipmentSlotType.FEET, ItemRegister.defaultBuilder());
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (entityIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityIn;
            if (timer == MAX_TIME) {
                if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ItemRegister.CHLOROPHYLL_HELMET.get()
                        && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemRegister.CHLOROPHYLL_BODY.get()
                        && player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ItemRegister.CHLOROPHYLL_LEGS.get()
                        && player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemRegister.CHLOROPHYLL_BOOTS.get()
                ) {
                    player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 5 * 20, 1));
                    if (worldIn.isDaytime()) {
                        player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 5 * 20, 0));
                    } else if (worldIn.isNightTime()) {
                        player.addPotionEffect(new EffectInstance(Effects.SPEED, 5 * 20, 0));
                    }
                }
                timer = 0;
            }
            timer++;
        }
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}

