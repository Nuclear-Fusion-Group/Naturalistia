package com.NuclearFusion.block.tileentity;

import com.NuclearFusion.block.Blocks;
import com.NuclearFusion.Naturalistia;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityRegister {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Naturalistia.MOD_ID);

    public static final RegistryObject<TileEntityType<CrucibleMadeOfPlantsTileEntity>> crucible_made_of_plants_tile_entity = TILE_ENTITY.register("crucible_made_of_plants_tile_entity", () -> TileEntityType.Builder.create(CrucibleMadeOfPlantsTileEntity::new, Blocks.crucible_made_of_plants.get()).build(null));
}
