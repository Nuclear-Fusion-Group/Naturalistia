package com.NuclearFusion.event;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.block.BlockRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,modid = Naturalistia.MOD_ID,value = Dist.CLIENT)
public class RenderTypeRegistry {
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            RenderTypeLookup.setRenderLayer(BlockRegistry.BLOCK_BELLADONNA.get(), RenderType.getCutout());
        });
        event.enqueueWork(()->{
            RenderTypeLookup.setRenderLayer(BlockRegistry.BLOCK_CHILIPEPPER.get(), RenderType.getCutout());
        });
        event.enqueueWork(()->{
            RenderTypeLookup.setRenderLayer(BlockRegistry.BLOCK_DATURA.get(), RenderType.getCutout());
        });
        event.enqueueWork(()->{
            RenderTypeLookup.setRenderLayer(BlockRegistry.BLOCK_SKYFAERY.get(), RenderType.getCutout());
        });
    }
}
