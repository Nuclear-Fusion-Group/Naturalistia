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

    public static final RegistryObject<Block> crucible_made_of_plants = BLOCKS.register("crucible_made_of_plants", BlockCrucibleMadeOfPlants::new);
    //����
    public static final RegistryObject<Block> belladonna = BLOCKS.register("belladonna", Belladonna::new);
    public static final RegistryObject<Block> chilipepper = BLOCKS.register("chilipepper", Chilipepper::new);
    public static final RegistryObject<Block> datura = BLOCKS.register("datura", Datura::new);
    public static final RegistryObject<Block> skyfaery = BLOCKS.register("skyfaery", Skyfaery::new);

    /**
     * ���ڻ�ȡmod��ע������з���
     *
     * @return ��ע�᷽�������
     */
    public static ArrayList<RegistryObject<Block>> getBlocks() {
        return new ArrayList<>(BLOCKS.getEntries());
    }

    /**
     * ���鹹�췽��
     */
    public static AbstractBlock.Properties defaultBuilder(Material material) {
        return AbstractBlock.Properties.create(material);
    }

    /**
     * ���ﹹ�췽��
     */
    public static AbstractBlock.Properties defaultBuilder() {
        return AbstractBlock.Properties.create(Material.STRUCTURE_VOID)
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(3F);
    }
}
