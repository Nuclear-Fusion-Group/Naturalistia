package com.NuclearFusion.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author DustW
 */
public class AttackUtils {
    public static void longerAttack(PlayerEntity player, double range) {
        Optional<EntityRayTraceResult> a = Optional.ofNullable(rayTraceEntities(player, range, player.getPositionVec()));

        a.ifPresent((rayTraceResult) -> {
            rayTraceResult.getEntity().attackEntityFrom(DamageSource.ANVIL, 12);
        });
    }

    @Nullable
    protected static EntityRayTraceResult rayTraceEntities(PlayerEntity player, double expend, Vector3d start) {
        return rayTraceEntities(
                player.world, player,
                start, start.add(player.getLookVec().scale(expend)),
                player.getBoundingBox().grow(player.getLookVec().scale(expend).length() + 1));
    }

    @Nullable
    public static EntityRayTraceResult rayTraceEntities(World world, Entity entity, Vector3d vector3d1, Vector3d vector3d, AxisAlignedBB axisAlignedBB) {
        double d0 = Integer.MAX_VALUE;
        Entity entity2 = null;

        List<Entity> list = world.getEntitiesWithinAABB(Entity.class, axisAlignedBB, (entity1) -> entity1 != entity);

        for (Entity entity1 : list) {
            AxisAlignedBB axisalignedbb = entity1.getBoundingBox().offset(0, -entity1.getHeight(), 0);
            Optional<Vector3d> optional = axisalignedbb.rayTrace(vector3d1, vector3d);
            if (optional.isPresent()) {
                double d1 = vector3d1.squareDistanceTo(optional.get());
                if (d1 < d0) {
                    entity2 = entity1;
                    d0 = d1;
                }
            }
        }

        return entity2 == null ? null : new EntityRayTraceResult(entity2);
    }
}
