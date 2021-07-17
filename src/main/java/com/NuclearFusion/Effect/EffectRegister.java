package com.NuclearFusion.Effect;

import com.NuclearFusion.Naturalistia;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectRegister {
    public static final DeferredRegister<Effect> Effect = DeferredRegister.create(ForgeRegistries.POTIONS, Naturalistia.MOD_ID);

    public static final RegistryObject<Effect> EFFECT_VERTIGO = Effect.register("effect_vertigo", OBJvertigo::new);


}
