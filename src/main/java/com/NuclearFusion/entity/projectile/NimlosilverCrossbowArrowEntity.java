package com.NuclearFusion.entity.projectile;

import com.NuclearFusion.entity.EntityRegister;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * @author DustW
 */
public class NimlosilverCrossbowArrowEntity extends AbstractArrowEntity {
    public NimlosilverCrossbowArrowEntity(EntityType<NimlosilverCrossbowArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public NimlosilverCrossbowArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityRegister.NIMLOSILVER_CROSSBOW_ARROW.get(), x, y, z, worldIn);
    }

    public NimlosilverCrossbowArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntityRegister.NIMLOSILVER_CROSSBOW_ARROW.get(), shooter, worldIn);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        if (result.getEntity() instanceof LivingEntity) {
            result.getEntity().attackEntityFrom(DamageSource.causeArrowDamage(this, getShooter() == null ? this : getShooter()), 5);

            this.remove();
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
