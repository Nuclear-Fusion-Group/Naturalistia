package com.NuclearFusion.block;

import com.NuclearFusion.block.crop.Belladonna;
import com.NuclearFusion.block.crop.Chilipepper;
import com.NuclearFusion.block.crop.Datura;
import com.NuclearFusion.block.crop.Skyfaery;
import com.NuclearFusion.Naturalistia;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Naturalistia.MOD_ID);

    public static final RegistryObject<Block> BLOCK_BOTANIC_CRUCIBLE = BLOCKS.register("botanic_crucible", BlockBotanicCrucible::new);
    public static final RegistryObject<Block> BLOCK_BOTANIC_ALTAR = BLOCKS.register("botanic_altar", BlockBotanicAltar::new);
    public static final RegistryObject<Block> BLOCK_BELLADONNA = BLOCKS.register("belladonna", Belladonna::new);
    public static final RegistryObject<Block> BLOCK_CHILIPEPPER = BLOCKS.register("chilipepper", Chilipepper::new);
    public static final RegistryObject<Block> BLOCK_DATURA = BLOCKS.register("datura", Datura::new);
    public static final RegistryObject<Block> BLOCK_SKYFAERY = BLOCKS.register("skyfaery", Skyfaery::new);

    public static final RegistryObject<Block> BLOCK_ORE_MALACHITE = BLOCKS.register("ore_malachite", () -> new Block(defaultBuilder()));
    public static final RegistryObject<Block> BLOCK_ORE_NIMLOSILVER = BLOCKS.register("ore_nimlosilver", () -> new Block(defaultBuilder()));
    public static final RegistryObject<Block> BLOCK_ORE_CHLOROPLAST = BLOCKS.register("ore_chloroplast", () -> new Block(defaultBuilder()));

    public static final RegistryObject<Block> BLOCK_ORE_COPPER_BLOCK = BLOCKS.register("copper_block", () -> new Block(defaultBuilder()));
    public static final RegistryObject<Block> BLOCK_ORE_NIMLOSILVER_BLOCK = BLOCKS.register("nimlosilver_block", () -> new Block(defaultBuilder()));
    public static final RegistryObject<Block> BLOCK_ORE_CHLOROPHYLL_BLOCK = BLOCKS.register("chlorophyll_block", () -> new Block(defaultBuilder()));

    public static ArrayList<RegistryObject<Block>> getBlocks() {
        return new ArrayList<>(BLOCKS.getEntries());
    }

    public static AbstractBlock.Properties defaultBuilder(Material material) {
        return AbstractBlock.Properties.create(material);
    }

    public static AbstractBlock.Properties defaultBuilder() {
        return AbstractBlock.Properties.create(Material.ROCK)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(3.0F);
    }
}
