package com.NuclearFusion;

import com.NuclearFusion.entity.EntityRegister;
import com.NuclearFusion.entity.projectile.NimlosilverCrossbowArrowEntity;
import com.NuclearFusion.entity.projectile.renderer.NimlosilverCrossbowArrowRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
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
        EntityRendererManager manager = Minecraft.getInstance().getRenderManager();

        manager.register(EntityRegister.NIMLOSILVER_CROSSBOW_ARROW.get(), new NimlosilverCrossbowArrowRenderer(manager));
    }
}
