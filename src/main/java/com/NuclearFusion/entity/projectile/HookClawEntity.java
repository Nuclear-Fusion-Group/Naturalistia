package com.NuclearFusion.entity.projectile;

import com.NuclearFusion.entity.EntityRegister;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class HookClawEntity extends ProjectileItemEntity implements IEntityAdditionalSpawnData {
    private Entity shooter = null;
    private int shooterID = 0;
    private long time = -1;
    private Entity caughtEntity;

    public HookClawEntity(World worldIn, double x, double y, double z) {
        super(EntityRegister.THORNY_HOOK.get(), x, y, z, worldIn);
    }

    public HookClawEntity(EntityType<HookClawEntity> hookClawEntityEntityType, World world) {
        super(hookClawEntityEntityType, world);
    }

    public HookClawEntity(World worldIn, LivingEntity shooter) {
        super(EntityRegister.THORNY_HOOK.get(), shooter, worldIn);
        this.shooter = shooter;
        this.shooterID = shooter.getEntityId();
    }

    @Override
    public void tick() {
        if (this.shooter == null || shooterID == 0) {
            this.remove();
        }
        //碰撞后定时移除
        if (this.getMotion().equals(new Vector3d(0, 0, 0))) {
            if (time == -1) {
                time = System.currentTimeMillis();
                super.tick();
                return;
            }
            if (System.currentTimeMillis() - time > 1500) {
                this.remove();
            }
        }
        //超出范围则移除实体
        if (world.getEntitiesInAABBexcluding(this, new AxisAlignedBB(
                        this.getPosition()
                                .offset(Direction.EAST, 14)
                                .offset(Direction.NORTH, 14)
                                .offset(Direction.DOWN, 14),
                        this.getPosition()
                                .offset(Direction.SOUTH, 14)
                                .offset(Direction.WEST, 14)
                                .offset(Direction.UP, 14)), null)
                .stream().noneMatch(entity -> entity == shooter)) {
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
            this.setMotion(0, 0, 0);
            return;
        }
        if (shooterID != 0) {
//            if (result instanceof EntityRayTraceResult) {
//                Vector3d moveVec = shooter.getPositionVec().subtract(result.getHitVec()).scale(0.4D);
//                caughtEntity = ((EntityRayTraceResult) result).getEntity();
//                caughtEntity.setMotion(moveVec.getX(), Math.min(moveVec.getY(), 2), moveVec.getZ());
//            }
            if (result instanceof BlockRayTraceResult) {
                if (shooter instanceof PlayerEntity) {
                    Vector3d moveVec = result.getHitVec().subtract(shooter.getPositionVec()).scale(0.4D);
                    shooter.setMotion(moveVec.getX(), Math.min(moveVec.getY(), 2), moveVec.getZ());
                    shooter.velocityChanged = true;
                }
            }
            this.setMotion(0, 0, 0);
        }
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
