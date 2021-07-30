package com.NuclearFusion.datagen.Recipes;

import com.NuclearFusion.item.ItemRegister;
import com.NuclearFusion.item.arms.tool.mace.NimlosilverMace;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.function.Consumer;

public class BotanicCrucible extends ForgeRecipeProvider {
    public BotanicCrucible(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ItemRegister.ITEM_BOTANIC_CRUCIBLE.get())
                .patternLine(" I ")
                .patternLine("SGS")
                .patternLine("ISI")
                .key('I', Items.IRON_INGOT)
                .key('G', Items.BONE_MEAL)
                .key('S', Items.STONE)
                .addCriterion("botanic_crucible", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT, Items.BONE_MEAL, Items.STONE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ItemRegister.ITEM_ARMS_SHOVEL_MACE.get())
                .patternLine(" S ")
                .patternLine("SSS")
                .patternLine(" S ")
                .key('S', Items.STONE)
                .addCriterion("nimlocsilver_mace", InventoryChangeTrigger.Instance.forItems(ItemRegister.ITEM_INGOT_NIMLOSILVER.get()))
                .build(consumer);
    }
}
