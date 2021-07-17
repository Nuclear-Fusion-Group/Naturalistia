package com.NuclearFusion.item.arms.crossbow;

import com.NuclearFusion.entity.projectile.NimlosilverCrossbowArrowEntity;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author DustW
 */
public class NimlosilverCrossbow extends CrossbowItem {
    public NimlosilverCrossbow() {
        super(ItemRegister.defaultBuilder().maxDamage(326));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (isCharged(itemstack)) {
            if (!worldIn.isRemote) {
                NimlosilverCrossbowArrowEntity arrow = createArrow(worldIn, playerIn);
                setPiercingLevel(arrow, itemstack);

                if (playerIn.isCreative()) {
                    arrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                }

                Vector3f vector3f = getVector(playerIn);
                arrow.shoot(vector3f.getX(), vector3f.getY(), vector3f.getZ(), 3.15F, 1);
                worldIn.addEntity(arrow);

                itemstack.damageItem(1, playerIn, (playerEntity) -> {
                    playerEntity.sendBreakAnimation(handIn);
                });
            }
            setCharged(itemstack, false);
            return ActionResult.resultConsume(itemstack);
        } else {
            return super.onItemRightClick(worldIn, playerIn, handIn);
        }
    }

    public static Vector3f getVector(PlayerEntity playerIn) {
        Vector3d upVector = playerIn.getUpVector(1.0F);
        Vector3d look = playerIn.getLook(1.0F);
        Vector3f vector3f = new Vector3f(look);
        vector3f.transform(new Vector3f(upVector).rotationDegrees(0));
        return vector3f;
    }

    public static void setPiercingLevel(AbstractArrowEntity arrow, ItemStack itemStack) {
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.PIERCING, itemStack);
        if (i > 0) {
            arrow.setPierceLevel((byte)i);
        }
    }

    public static NimlosilverCrossbowArrowEntity createArrow(World worldIn, PlayerEntity playerIn) {
        NimlosilverCrossbowArrowEntity arrow = new NimlosilverCrossbowArrowEntity(worldIn, playerIn);
        arrow.setIsCritical(true);
        arrow.setHitSound(SoundEvents.ITEM_CROSSBOW_HIT);
        arrow.setShotFromCrossbow(true);
        return arrow;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        int i = this.getUseDuration(stack) - timeLeft;
        float f = getCharge(i, stack);
        ItemStack ammo = entityLiving.findAmmo(new ItemStack(this));
        if (f >= 1.0F && !isCharged(stack) && !ammo.isEmpty()) {
            if (entityLiving instanceof ServerPlayerEntity && !((ServerPlayerEntity) entityLiving).isCreative()) {
                ammo.shrink(1);
            }
            setCharged(stack, true);
            SoundCategory soundcategory = entityLiving instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
            worldIn.playSound(null, entityLiving.getPosX(), entityLiving.getPosY(), entityLiving.getPosZ(), SoundEvents.ITEM_CROSSBOW_LOADING_END, soundcategory, 1.0F, 1.0F / (random.nextFloat() * 0.5F + 1.0F) + 0.2F);
        }
    }

    private static float getCharge(int useTime, ItemStack stack) {
        float f = (float)useTime / (float)getChargeTime(stack);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return getChargeTime(stack) + 3;
    }

    /**
     * 改这里就能改蓄力时间
     */
    public static int getChargeTime(ItemStack stack) {
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
        return i == 0 ? 12 : 12 - 3 * i;
    }

    @Override
    public Predicate<ItemStack> getAmmoPredicate() {
        return getInventoryAmmoPredicate();
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return (itemStack) -> itemStack.getItem() == Items.ARROW;
    }

    //武器描述第NNNN次重写o(╥﹏╥)o

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
