package com.NuclearFusion.plugin.jei;

import com.NuclearFusion.naturalistia;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class jei implements IModPlugin {
    public void register(){

    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(naturalistia.MOD_ID, naturalistia.MOD_ID);
    }
}
