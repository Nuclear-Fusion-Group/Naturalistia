package com.NuclearFusion.event;

import com.NuclearFusion.datagen.LootTable.LootProvider;
import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.datagen.Recipes.BotanicCrucible;
import com.NuclearFusion.datagen.model.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Naturalistia.MOD_ID)
public class DataGenEvent {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        ExistingFileHelper helper = event.getExistingFileHelper();
        if (event.includeClient()) {

        }
        if (event.includeServer()) {
            event.getGenerator().addProvider(new BotanicCrucible(event.getGenerator()));
            event.getGenerator().addProvider(new LootProvider(event.getGenerator()));
            event.getGenerator().addProvider(new ItemModelProvider(event.getGenerator(), helper));
        }
    }
}
