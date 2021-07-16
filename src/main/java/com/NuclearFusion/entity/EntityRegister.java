package com.NuclearFusion.entity;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.entity.projectile.NimlosilverCrossbowArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author DustW
 */
public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Naturalistia.MOD_ID);

    public static final RegistryObject<EntityType<NimlosilverCrossbowArrowEntity>> NIMLOSILVER_CROSSBOW_ARROW = ENTITIES.register("nimlosilver_crossbow_arrow_entity", () -> EntityType.Builder.<NimlosilverCrossbowArrowEntity>create(NimlosilverCrossbowArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).trackingRange(4).updateInterval(20).build("nimlosilver_crossbow_arrow_entity"));
}
