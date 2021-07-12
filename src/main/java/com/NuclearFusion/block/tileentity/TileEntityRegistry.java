package com.NuclearFusion.block.tileentity;

import com.NuclearFusion.block.BlockRegistry;
import com.NuclearFusion.Naturalistia;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityRegistry {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_REGISTRY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Naturalistia.MOD_ID);

    public static final RegistryObject<TileEntityType<TileEntityBotanicCrucible>> REGISTRY_OBJECT_TILE_ENTITY_BOTANIC_CRUCIBLE = TILE_ENTITY_REGISTRY.register("botanic_crucible", () -> TileEntityType.Builder.create(TileEntityBotanicCrucible::new, BlockRegistry.BLOCK_BOTANIC_CRUCIBLE.get()).build(null));
}
