package com.NuclearFusion.event.listener;

import com.NuclearFusion.Effect.EffectRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber
public class ArrowJoinWorldEvent {
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof AbstractArrowEntity) {
            AbstractArrowEntity arrow = (AbstractArrowEntity) entity;

            if (arrow.getShooter() != null) {
                Entity shooter = arrow.getShooter();

                if (shooter instanceof LivingEntity && ((LivingEntity) shooter).isPotionActive(EffectRegister.EFFECT_VERTIGO.get())) {
                    event.setCanceled(true);
                }
            }
        }
    }
}
