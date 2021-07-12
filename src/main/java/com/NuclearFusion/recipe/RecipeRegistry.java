package com.NuclearFusion.recipe;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.recipe.crucible.CrucibleRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Naturalistia.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeRegistry {

    public static final IRecipeSerializer<?> CRUCIBLE_RECIPE_SERIALIZER = new CrucibleRecipe.Serializer();

    @SubscribeEvent
    public static void onRecipeRegister(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        event.getRegistry().register(CRUCIBLE_RECIPE_SERIALIZER.setRegistryName(new ResourceLocation(Naturalistia.MOD_ID, "crucible_recipe")));
    }

    @SubscribeEvent
    public static void registerRegistry(RegistryEvent.NewRegistry event){

    }
}
