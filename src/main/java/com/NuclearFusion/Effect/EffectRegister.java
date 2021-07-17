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
            .addAttributesModifier(Attributes.ATTACK_DAMAGE,  "5a64a413-cdc5-d80b-e4b8-8f09ad67c62c", 0, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributesModifier(Attributes.MOVEMENT_SPEED, "5739c3c3-f93d-02d5-53f8-37a74d61e7dd", 0, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributesModifier(Attributes.ATTACK_SPEED,   "162f964b-2c59-2843-b6ee-69f060dcc6e7",0,AttributeModifier.Operation.MULTIPLY_TOTAL));

}
