package com.NuclearFusion.plugin.jei;

import com.NuclearFusion.Naturalistia;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JEICompact implements IModPlugin {
    public void register(){

    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Naturalistia.MOD_ID, Naturalistia.MOD_ID);
    }
}
