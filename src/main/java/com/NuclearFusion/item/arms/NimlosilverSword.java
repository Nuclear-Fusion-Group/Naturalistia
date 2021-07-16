package com.NuclearFusion.item.arms;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.SoundEvents;

public class NimlosilverSword extends SwordItem {

    public NimlosilverSword() {
        super(new ModItemTier(3, 1000, 10.0F, 3.0F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 1, -2.4F, ItemRegister.defaultBuilder());
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (entity instanceof MonsterEntity) {
            if (((MonsterEntity) entity).getCreatureAttribute().equals(CreatureAttribute.UNDEAD) || ((MonsterEntity) entity).getCreatureAttribute().equals(CreatureAttribute.ARTHROPOD)) {
                ((MonsterEntity) entity).setHealth(((MonsterEntity) entity).getHealth()-this.getAttackDamage());
                if (((MonsterEntity) entity).getShouldBeDead()){
                    entity.playSound(SoundEvents.ENTITY_ZOMBIE_DEATH,200,200);
                }
                return false;
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }
}
