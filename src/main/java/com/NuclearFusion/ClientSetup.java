package com.NuclearFusion;

import com.NuclearFusion.entity.EntityRegister;
import com.NuclearFusion.entity.projectile.renderer.BombSeedEntityRenderer;
import com.NuclearFusion.entity.projectile.renderer.HookClawEntityRenderer;
import com.NuclearFusion.entity.projectile.renderer.NimlosilverCrossbowArrowRenderer;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            registerProperties();
            registerEntityRenderer();
        });
    }

    static void registerEntityRenderer() {
        EntityRendererManager manager = Minecraft.getInstance().getRenderManager();

        manager.register(EntityRegister.NIMLOSILVER_CROSSBOW_ARROW.get(), new NimlosilverCrossbowArrowRenderer(manager));
        manager.register(EntityRegister.BOMB_SEED_ENTITY.get(), new BombSeedEntityRenderer(manager));
        manager.register(EntityRegister.HOOK_CLAW_ENTITY.get(), new HookClawEntityRenderer(manager));
    }

    static void registerProperties() {
        ItemModelsProperties.registerProperty(ItemRegister.ITEM_ARMS_CROSSBOW_NIMLOSILVER.get(), new ResourceLocation("pull"), (itemStack, world, entity) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return CrossbowItem.isCharged(itemStack) ? 0.0F : (float)(itemStack.getUseDuration() - entity.getItemInUseCount()) / (float)CrossbowItem.getChargeTime(itemStack);
            }
        });

        ItemModelsProperties.registerProperty(ItemRegister.ITEM_ARMS_CROSSBOW_NIMLOSILVER.get(), new ResourceLocation("pulling"),
                (itemStack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == itemStack && !CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);

        ItemModelsProperties.registerProperty(ItemRegister.ITEM_ARMS_CROSSBOW_NIMLOSILVER.get(), new ResourceLocation("charged"),
                (itemStack, world, entity) -> entity != null && CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);
    }
}
