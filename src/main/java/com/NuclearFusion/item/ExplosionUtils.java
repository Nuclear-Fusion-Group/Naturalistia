package com.NuclearFusion.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.ExplosionContext;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author DustW
 */
public class ExplosionUtils {
    /**
     * 生成一个不会破坏方块且只对living实体生效的爆炸
     *
     * @param world  世界
     * @param pos    坐标
     * @param range  范围
     * @param damage 伤害
     * @param player 玩家
     */
    public static void createExplosionWithDamage(World world, Vector3d pos, double range, float damage, @Nullable PlayerEntity player) {
        if (world.isRemote) {
            world.playSound(pos.x, pos.y, pos.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS,
                    4.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F, false);
            world.addParticle(ParticleTypes.EXPLOSION, pos.x, pos.y, pos.z, 1.0D, 0.0D, 0.0D);
        } else {
            List<LivingEntity> list = world.getEntitiesWithinAABB(LivingEntity.class,
                    new AxisAlignedBB(pos.add(range, range, range), pos.subtract(range, range, range)));
            list.forEach(entity -> entity.attackEntityFrom(DamageSource.causeExplosionDamage(player), damage));
        }
    }
}
