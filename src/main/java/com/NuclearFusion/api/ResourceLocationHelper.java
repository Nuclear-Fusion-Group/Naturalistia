package com.NuclearFusion.api;

import com.NuclearFusion.Naturalistia;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper {
    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(Naturalistia.MOD_ID, path);
    }
}
