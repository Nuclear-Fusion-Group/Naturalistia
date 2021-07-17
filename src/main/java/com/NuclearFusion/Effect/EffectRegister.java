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
            .addAttributesModifier(Attributes.ATTACK_DAMAGE, UUID.randomUUID().toString(), 0.0D, AttributeModifier.Operation.ADDITION)
            .addAttributesModifier(Attributes.MOVEMENT_SPEED, UUID.randomUUID().toString(), -1.0D, AttributeModifier.Operation.ADDITION)
            .addAttributesModifier(Attributes.ATTACK_SPEED, UUID.randomUUID().toString(),-1.0D,AttributeModifier.Operation.ADDITION));

}
