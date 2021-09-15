package com.NuclearFusion.entity.projectile;

import com.NuclearFusion.entity.EntityRegister;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class HookClawEntity extends AbstractArrowEntity {

    public HookClawEntity(World worldIn, double x, double y, double z) {
        super(EntityRegister.HOOK_CLAW_ENTITY.get(), x, y, z, worldIn);
    }

    public HookClawEntity(EntityType<HookClawEntity> hookClawEntityEntityType, World world) {
        super(hookClawEntityEntityType, world);
    }

    public HookClawEntity(World worldIn, LivingEntity shooter) {
        super(EntityRegister.HOOK_CLAW_ENTITY.get(), shooter, worldIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (world.isRemote && ProjectileUtils.isShooter(result, this)) {
            return;
        }
        if (getShooter() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) getShooter();
            double x = player.getPosX();
            double y = player.getPosY();
            double z = player.getPosZ();
            //TODO 进行相对位置判断并发出声音
            player.playSound(SoundEvents.ENTITY_ENDERMAN_AMBIENT,10.0F,10.0F);
            player.moveForced(result.getHitVec());
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
