package com.NuclearFusion.entity;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.entity.projectile.BombSeedEntity;
import com.NuclearFusion.entity.projectile.HookClawEntity;
import com.NuclearFusion.entity.projectile.NimlosilverCrossbowArrowEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author DustW
 */
public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Naturalistia.MOD_ID);

    public static final RegistryObject<EntityType<NimlosilverCrossbowArrowEntity>> NIMLOSILVER_CROSSBOW_ARROW =
            ENTITIES.register("nimlosilver_crossbow_arrow_entity",
                    () -> EntityType.Builder.<NimlosilverCrossbowArrowEntity>create(
                                    NimlosilverCrossbowArrowEntity::new,
                                    EntityClassification.MISC)
                            .size(0.5F, 0.5F)
                            .trackingRange(4)
                            .updateInterval(20).build("nimlosilver_crossbow_arrow_entity"));

    public static final RegistryObject<EntityType<BombSeedEntity>> BOMB_SEED_ENTITY =
            ENTITIES.register("bomb_seed_entity",
                    () -> EntityType.Builder.<BombSeedEntity>create(
                                    BombSeedEntity::new,
                                    EntityClassification.MISC)
                            .size(0.5F, 0.5F)
                            .trackingRange(4)
                            .updateInterval(20).build("bomb_seed_entity"));

    public static final RegistryObject<EntityType<HookClawEntity>> THORNY_HOOK =
            ENTITIES.register("thorny_hook",
                    () -> EntityType.Builder.<HookClawEntity>create(
                                    HookClawEntity::new,
                                    EntityClassification.MISC)
                            .size(0.5F, 0.5F)
                            .trackingRange(4)
                            .updateInterval(20).build("thorny_hook")

            );
}
