package com.NuclearFusion.Effect;

import com.NuclearFusion.Naturalistia;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;

public class EffectRegister {
    public static final DeferredRegister<Effect> Effect = DeferredRegister.create(ForgeRegistries.POTIONS, Naturalistia.MOD_ID);

    public static final RegistryObject<Effect> EFFECT_VERTIGO = Effect.register("effect_vertigo", () -> new OBJvertigo()
            .addAttributesModifier(Attributes.ATTACK_DAMAGE, "22653B89-116E-49DC-9B6B-9971489B5BE5", 0, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributesModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", 0, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributesModifier(Attributes.ATTACK_SPEED, "55FCED67-E92A-486E-9800-B47F202C4386",0,AttributeModifier.Operation.MULTIPLY_TOTAL));

}
