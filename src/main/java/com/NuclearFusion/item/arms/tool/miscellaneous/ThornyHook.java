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
        super(ItemRegister.defaultBuilder().maxStackSize(1).maxDamage(1951));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            if (handIn == Hand.OFF_HAND) {
                ItemStack itemStack = playerIn.getHeldItem(handIn);
                itemStack.damageItem(1, playerIn, (player) -> player.sendBreakAnimation(handIn));

                HookClawEntity hookClawEntity = new HookClawEntity(worldIn, playerIn);
                Vector3d look = playerIn.getLookVec();

                hookClawEntity.shoot(look.x, look.y, look.z, 1.0F, 0);
                //添加实体
                worldIn.addEntity(hookClawEntity);
                //冷却
                playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), 20);
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
