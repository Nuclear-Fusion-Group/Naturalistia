package com.NuclearFusion.item.arms.tool.miscellaneous;

import com.NuclearFusion.entity.projectile.HookClawEntity;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ThornyHook extends Item {
    public ThornyHook() {
        super(ItemRegister.defaultBuilder());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            if (handIn == Hand.OFF_HAND) {
                HookClawEntity hookClawEntity = new HookClawEntity(worldIn, playerIn);
                Vector3d look = playerIn.getLookVec();

                //TODO 调整速度
                hookClawEntity.shoot(look.x, look.y, look.z, 10.0F, 0);

                worldIn.addEntity(hookClawEntity);

                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 20);
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
