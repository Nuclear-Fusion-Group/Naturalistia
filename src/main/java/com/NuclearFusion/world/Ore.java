package com.NuclearFusion.world;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.block.BlockRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Naturalistia.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Ore {
    public static ConfiguredFeature<?, ?> ORE_COPPER_CONFIG;
    public static ConfiguredFeature<?, ?> ORE_NIMLOSILVER_CONFIG;

    /**
     * BASE_STONE_OVERWORLD 主世界
     * BASE_STONE_NETHER 末地
     * NETHERRACK 地狱
     */
    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        ORE_COPPER_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_malachite",
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                                BlockRegistry.BLOCK_ORE_MALACHITE.get().getDefaultState(), 8//最多数量
                        )
                ).range(70).square().count(20)
        );
        ORE_NIMLOSILVER_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_nimlosilver",
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                                BlockRegistry.BLOCK_ORE_NIMLOSILVER.get().getDefaultState(), 9
                        )
                ).range(31).square().count(20)
        );
    }
}
