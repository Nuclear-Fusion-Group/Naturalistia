package com.NuclearFusion.item.arms.tool.hoe;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.entity.projectile.BombSeedEntity;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ChlorophyllHoe extends SwordItem {
    public ChlorophyllHoe() {
        super(new ModItemTier(3, 300, 7.5F, 5.5F, 30, ItemRegister.ITEM_INGOT_CHLOROPHYLL.get()), 3, -3.0F, ItemRegister.defaultBuilder());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (!world.isRemote) {
            BombSeedEntity bombSeedEntity = new BombSeedEntity(world, player);
            bombSeedEntity.setWeapon(player.getHeldItem(hand));

            Vector3d look = player.getLookVec();
            bombSeedEntity.shoot(look.x, look.y, look.z, 0.5F, 1);

            world.addEntity(bombSeedEntity);

            player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), 20);
        }
        return super.onItemRightClick(world, player, hand);
    }
}
