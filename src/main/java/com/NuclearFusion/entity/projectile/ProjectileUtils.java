package com.NuclearFusion.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

/**
 * @author DustW
 */
public class ProjectileUtils {
    public static boolean isShooter(RayTraceResult result, ProjectileEntity projectile) {
        boolean flag1 = result.getType() == RayTraceResult.Type.ENTITY && ((EntityRayTraceResult) result).getEntity() == projectile.getShooter();
        boolean flag2 = result.getType() == RayTraceResult.Type.BLOCK;

        if (flag2) {
            return false;
        }

        return flag1;
    }
}
