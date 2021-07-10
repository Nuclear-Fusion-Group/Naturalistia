package com.NuclearFusion.datagen;

import com.NuclearFusion.datagen.LootTable.LootProvider;
import com.NuclearFusion.datagen.Recipes.CrucibleMadeOfPlants;
import com.NuclearFusion.naturalistia;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = naturalistia.MOD_ID)
public class DataGenEvent {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        if (event.includeClient()) {

        }
        if (event.includeServer()) {
            event.getGenerator().addProvider(new CrucibleMadeOfPlants(event.getGenerator()));
            event.getGenerator().addProvider(new LootProvider(event.getGenerator()));
        }
    }
}
