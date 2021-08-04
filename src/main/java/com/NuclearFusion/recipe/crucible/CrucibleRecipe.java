package com.NuclearFusion.recipe.crucible;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.recipe.IRecipeManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.mantle.util.ItemStackList;

import java.util.List;

public class CrucibleRecipe {

    public ResourceLocation recipeId;

    public ItemStackList itemInputs = ItemStackList.withSize(6);

    public FluidStack inputFluid;

    public FluidStack outputFluid;

    public int timeTaken;

    public CrucibleRecipe(String recipeId, int time, FluidStack inputFluid, FluidStack outputFluid, ItemStack... inputs) {
        int i = 0;
        for (ItemStack itemStack:inputs){
            itemInputs.set(i++, itemStack);
        }
        this.inputFluid = inputFluid;
        this.outputFluid = outputFluid;
        this.recipeId = new ResourceLocation(Naturalistia.MOD_ID, recipeId);
        this.timeTaken = time;
    }

    /**
     * @param inputFluid The input fluids
     * @param inputs The input items in crucible
     * @return The output fluid that the whole bunch of stuffs in the crucible tends to; returns null if no corresponding recipe is found.
     * todo: 配方能够在有整数倍的原料的情况下大批量制作液体
     */
    public static CrucibleRecipe getRecipe(FluidStack inputFluid, List<ItemStack> inputs){

        for(CrucibleRecipe recipe : IRecipeManager.crucibleRecipes){

            if(recipe.inputFluid.isFluidStackIdentical(inputFluid)){

                Naturalistia.LOGGER.info(inputs);
                Naturalistia.LOGGER.info(recipe.itemInputs);

                boolean match = true;
                for (int i = 0; i < 6; i++) {

                    Naturalistia.LOGGER.info(inputs.get(i));
                    Naturalistia.LOGGER.info(recipe.itemInputs.get(i));
                    Naturalistia.LOGGER.info(!inputs.get(i).isItemEqual(recipe.itemInputs.get(i)) & !((inputs.get(i).isEmpty()) & recipe.itemInputs.get(i).isEmpty()));

                    if(!inputs.get(i).isItemEqual(recipe.itemInputs.get(i)) & !((inputs.get(i).isEmpty()) & recipe.itemInputs.get(i).isEmpty())){
                        match = false;
                    }
                }
                if(match){
                    return recipe;
                }
            }
        }

        return null;
    }
}
