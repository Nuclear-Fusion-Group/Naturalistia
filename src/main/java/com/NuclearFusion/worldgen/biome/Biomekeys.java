package com.NuclearFusion.worldgen.biome;

import com.NuclearFusion.Naturalistia;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

public class Biomekeys {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Naturalistia.MOD_ID);

    public static final RegistryKey<Biome> SECRET_FOREST_LAND = makeKey("secret_forest_land");
    public static final RegistryKey<Biome> YINSHA_DESERT = makeKey("yinsha_desert");

    private static RegistryKey<Biome> makeKey(String name) {
        BIOMES.register(name, () -> new Biome.Builder()
                .precipitation(Biome.RainType.NONE)
                .category(Biome.Category.NONE)
                .depth(0)
                .downfall(0)
                .scale(0)
                .temperature(0)
                .setEffects(new BiomeAmbience.Builder().setFogColor(0).setWaterColor(0).setWaterFogColor(0).withSkyColor(0).build())
                .withGenerationSettings(new BiomeGenerationSettings.Builder().withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS).build())
                .withMobSpawnSettings(new MobSpawnInfo.Builder().build())
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .build());
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Naturalistia.MOD_ID,name.toLowerCase(Locale.ROOT)));
    }
}
