package com.NuclearFusion.recipe;

import com.NuclearFusion.recipe.crucible.CrucibleRecipe;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;

public class IRecipeManager {

    public static ArrayList<CrucibleRecipe> crucibleRecipes;

    public IRecipeManager(){
        crucibleRecipes.add(new CrucibleRecipe(
                "test_recipe",
                100,
                null,
                new FluidStack(Fluids.WATER, 1000),
                new ItemStack(Items.PUMPKIN, 2),
                new ItemStack(Items.BEETROOT)));
    }
}
