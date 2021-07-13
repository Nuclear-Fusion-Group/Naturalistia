package com.NuclearFusion.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class Crops {
    public static AbstractBlock.Properties CropsBuilder(){
        return AbstractBlock.Properties.create(Material.PLANTS).sound(SoundType.CROP).tickRandomly().doesNotBlockMovement().zeroHardnessAndResistance();
    }
}
