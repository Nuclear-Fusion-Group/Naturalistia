package com.NuclearFusion.datagen.recipes;

import com.NuclearFusion.item.ItemRegister;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.function.Consumer;

public class ItemRecipe extends ForgeRecipeProvider {
    public ItemRecipe(DataGenerator generatorIn) {
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
//
//    private void buildShapedRecipe(Item item, String line1, String line2, String line3, Consumer<IFinishedRecipe> consumer, Map<Character, Item> key) {
//        ShapedRecipeBuilder builder1 = ShapedRecipeBuilder.shapedRecipe(item)
//                .patternLine(line1)
//                .patternLine(line2)
//                .patternLine(line3);
//        for (Character ch : key.keySet()) {
//            builder1 = builder1.key(ch, key.get(ch));
//        }
//        builder1.build(consumer);
//    }
}
