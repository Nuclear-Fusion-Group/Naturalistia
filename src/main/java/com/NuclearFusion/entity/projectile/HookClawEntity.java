package com.NuclearFusion.entity.projectile;

import com.NuclearFusion.entity.EntityRegister;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class HookClawEntity extends ProjectileItemEntity implements IEntityAdditionalSpawnData {
    private Entity shooter = null;
    private int shooterID = 0;

    public HookClawEntity(World worldIn, double x, double y, double z) {
        super(EntityRegister.HOOK_CLAW_ENTITY.get(), x, y, z, worldIn);
    }

    public HookClawEntity(EntityType<HookClawEntity> hookClawEntityEntityType, World world) {
        super(hookClawEntityEntityType, world);
    }

    public HookClawEntity(World worldIn, LivingEntity shooter) {
        super(EntityRegister.HOOK_CLAW_ENTITY.get(), shooter, worldIn);
        this.shooter = shooter;
        this.shooterID = shooter.getEntityId();
    }

    @Override
    public void tick() {
        if (this.shooter == null || shooterID == 0) {
            this.remove();
        }
        super.tick();
    }

    //禁止重力
    @Override
    protected float getGravityVelocity() {
        return 0F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (world.isRemote) {
            return;
        }
        if (shooterID != 0) {
            if (result == null) {
                return;
            }
            if (result instanceof EntityRayTraceResult){
                Vector3d moveVec = result.getHitVec().subtractReverse(shooter.getPositionVec().scale(0.4D));
                ((EntityRayTraceResult) result).getEntity().addVelocity(moveVec.getX(), Math.min(moveVec.getY(), 2), moveVec.getZ());
            }
            if (result instanceof BlockRayTraceResult) {
                //有bug
                Vector3d moveVec = result.getHitVec().subtractReverse(shooter.getPositionVec().scale(0.4D));
                shooter.addVelocity(moveVec.getX(), Math.min(moveVec.getY(), 2), moveVec.getZ());
//            getShooter().moveRelative(5, getShooter().getPositionVec().subtractReverse(result.getHitVec()));
            } else {
                this.remove();
            }
        }
        this.remove();
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return true;
    }

    @Override
    public boolean isInRangeToRender3d(double x, double y, double z) {
        return true;
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegister.THORNY_HOOK.get();
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(this.getDefaultItem());
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        buffer.writeInt(this.shooter != null ? this.shooter.getEntityId() : 0);
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        this.shooterID = additionalData.readInt();
        this.shooter = this.world.getEntityByID(this.shooterID);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void remove() {
        super.remove();
        shooterID = 0;
    }
}
