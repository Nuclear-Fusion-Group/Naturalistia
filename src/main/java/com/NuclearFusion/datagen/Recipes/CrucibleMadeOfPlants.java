package com.NuclearFusion.datagen.Recipes;

import com.NuclearFusion.item.ItemRegister;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.function.Consumer;

public class CrucibleMadeOfPlants extends ForgeRecipeProvider {
    public CrucibleMadeOfPlants(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ItemRegister.ITEM_BOTANIC_CRUCIBLE.get())
                .patternLine(" I ")
                .patternLine("SGS")
                .patternLine("ISI")
                .key('I', net.minecraft.item.Items.IRON_INGOT)
                .key('G', net.minecraft.item.Items.BONE_MEAL)
                .key('S', net.minecraft.item.Items.STONE)
                .addCriterion("crucible_made_of_plants", InventoryChangeTrigger.Instance.forItems(net.minecraft.item.Items.IRON_INGOT, net.minecraft.item.Items.BONE_MEAL, net.minecraft.item.Items.STONE))
                .build(consumer);
    }
}
