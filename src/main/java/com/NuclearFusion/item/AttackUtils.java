package com.NuclearFusion.item;

import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author DustW
 */
public class AttackUtils {
    /**
     * 更长的攻击距离
     *
     * @param player 攻击者
     * @param range  攻击的距离
     */
    public static void longerAttack(PlayerEntity player, double range) {
        EntityRayTraceResult rayResult = rayTraceEntities(player, range, player.getPositionVec());
        if (rayResult != null) {
            Entity entity = rayResult.getEntity();
            //调用玩家的攻击函数 这样可以让那些在hitEntity等函数内实现攻击加成的物品实现攻击加成
            player.attackTargetEntityWithCurrentItem(entity);
        }
    }

    @Nullable
    protected static EntityRayTraceResult rayTraceEntities(PlayerEntity player, double expend, Vector3d start) {
        return rayTraceEntities(
                player.world, player,
                start, start.add(player.getLookVec().scale(expend)),
                player.getBoundingBox().grow(player.getLookVec().scale(expend).length() + 1));
    }

    @Nullable
    protected static EntityRayTraceResult rayTraceEntities(World world, Entity entity, Vector3d vector3d1, Vector3d vector3d, AxisAlignedBB axisAlignedBB) {
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

    /**
     * 获取受到生物能力加成的武器的伤害
     *
     * @param itemStack 武器
     * @param entity    玩家
     * @return 伤害的数值
     */
    public static float getAttackDamage(ItemStack itemStack, @Nullable LivingEntity entity) {
        double attackDamage = 0;
        Multimap<Attribute, AttributeModifier> multimap = itemStack.getAttributeModifiers(EquipmentSlotType.MAINHAND);

        if (multimap.containsKey(Attributes.ATTACK_DAMAGE)) {
            Collection<AttributeModifier> damageMap = multimap.get(Attributes.ATTACK_DAMAGE);
            attackDamage = ((AttributeModifier) damageMap.toArray()[0]).getAmount();
        }

        if (entity != null) {
            ModifiableAttributeInstance attribute = entity.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attribute != null) {
                attackDamage += attribute.getValue();
            }
        }

        return (float) attackDamage;
    }
}
