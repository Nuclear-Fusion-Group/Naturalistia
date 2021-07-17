package com.NuclearFusion.event;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class TickPlay {
    private static final int MAX_TIME = 1 * 15;
    private int timer = 0;

    @SubscribeEvent
    void Tick(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;
        if (event.side == LogicalSide.SERVER) {
            if (timer == MAX_TIME) {
                if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == com.NuclearFusion.item.ItemRegister.CHLOROPHYLL_HELMET.get()
                        && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == com.NuclearFusion.item.ItemRegister.CHLOROPHYLL_BODY.get()
                        && player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == com.NuclearFusion.item.ItemRegister.CHLOROPHYLL_BOOTS.get()
                        && player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == com.NuclearFusion.item.ItemRegister.CHLOROPHYLL_CHAIN.get()
                ) {
                    player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 5 * 20, 1));
                    if (event.player.getEntityWorld().isDaytime()) {
                        player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 5 * 20, 0));
                    } else if (event.player.getEntityWorld().isNightTime()) {
                        player.addPotionEffect(new EffectInstance(Effects.SPEED, 5 * 20, 0));
                    } else if (!event.player.getEntityWorld().isDaytime()) {
                        player.removePotionEffect(Effects.REGENERATION);
                    } else if (!event.player.getEntityWorld().isNightTime()) {
                        player.removePotionEffect(Effects.SPEED);
                    }

                } else {
                    player.removePotionEffect(Effects.JUMP_BOOST);
                    player.removePotionEffect(Effects.REGENERATION);
                    player.removePotionEffect(Effects.SPEED);
                }
                timer = 0;
            }
            timer++;
        }
    }
}
