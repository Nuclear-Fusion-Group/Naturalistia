package com.NuclearFusion.entity.projectile;

import com.NuclearFusion.entity.EntityRegister;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Objects;

public class HookClawEntity extends AbstractArrowEntity {
    private boolean isOne = true;
    private final Vector3d LOOK = Objects.requireNonNull(getShooter()).getLookVec();

    public HookClawEntity(World worldIn, double x, double y, double z) {
        super(EntityRegister.HOOK_CLAW_ENTITY.get(), x, y, z, worldIn);
    }

    public HookClawEntity(EntityType<HookClawEntity> hookClawEntityEntityType, World world) {
        super(hookClawEntityEntityType, world);
    }

    public HookClawEntity(World worldIn, LivingEntity shooter, boolean isOne) {
        super(EntityRegister.HOOK_CLAW_ENTITY.get(), shooter, worldIn);
        this.isOne = isOne;
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (!isOne) {
            if (getShooter() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) getShooter();
                player.playSound(SoundEvents.ENTITY_ENDERMAN_AMBIENT,1.0F,1.0F);
                player.startRiding(this);
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (world.isRemote && ProjectileUtils.isShooter(result, this)) {
            return;
        }
        if (getShooter() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) getShooter();
            if (isOne) {
                HookClawEntity hookClawEntity = new HookClawEntity(player.world, player, false);
                hookClawEntity.shoot(LOOK.x, LOOK.y, LOOK.z, 2.0F, 0);
                player.world.addEntity(hookClawEntity);
            }
        }
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
