package com.NuclearFusion.entity.projectile;

import com.NuclearFusion.entity.EntityRegister;
import com.NuclearFusion.item.AttackUtils;
import com.NuclearFusion.item.ExplosionUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

/**
 * @author DustW
 */
public class BombSeedEntity extends AbstractArrowEntity {
    public ItemStack weapon = ItemStack.EMPTY;

    public BombSeedEntity(EntityType<BombSeedEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public BombSeedEntity(World worldIn, double x, double y, double z) {
        super(EntityRegister.BOMB_SEED_ENTITY.get(), x, y, z, worldIn);
    }

    public BombSeedEntity(World worldIn, LivingEntity shooter) {
        super(EntityRegister.BOMB_SEED_ENTITY.get(), shooter, worldIn);
    }

    public void setWeapon(ItemStack weapon) {
        this.weapon = weapon;
    }

    @Override
    protected void onImpact(RayTraceResult p_70227_1_) {
        if (world.isRemote && ProjectileUtils.isShooter(p_70227_1_, this)) {
            return;
        }

        LivingEntity attacker = getShooter() instanceof LivingEntity ? (LivingEntity) getShooter() : null;
        PlayerEntity player = attacker instanceof PlayerEntity ? (PlayerEntity) attacker : null;

        ExplosionUtils.createExplosionWithDamage(world, getPositionVec(), 4,
                (float) AttackUtils.getAttackDamage(weapon, attacker), player);

        if (!world.isRemote) {
            remove();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(Items.ARROW);
    }
}
