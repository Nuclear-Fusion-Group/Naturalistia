package com.NuclearFusion.effect;

import com.NuclearFusion.Naturalistia;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectRegister {
    public static final DeferredRegister<Effect> EFFECT = DeferredRegister.create(ForgeRegistries.POTIONS, Naturalistia.MOD_ID);

    public static final RegistryObject<Effect> EFFECT_VERTIGO = EFFECT.register("effect_vertigo", () -> new OBJvertigo()
            .addAttributesModifier(Attributes.ATTACK_DAMAGE, "5a64a413-cdc5-d80b-e4b8-8f09ad67c62c", 0, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributesModifier(Attributes.MOVEMENT_SPEED, "5739c3c3-f93d-02d5-53f8-37a74d61e7dd", 0, AttributeModifier.Operation.MULTIPLY_TOTAL)
            .addAttributesModifier(Attributes.ATTACK_SPEED, "162f964b-2c59-2843-b6ee-69f060dcc6e7", 0, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<Effect> EFFECT_ARMOR_CORROSION = EFFECT.register("effect_armor_corrosion", () -> new ArmorCorrosion()
            .addAttributesModifier(Attributes.ARMOR,"23549cd1-5b32-a4c5-95d7-ab451c4665aa",-10.0D,AttributeModifier.Operation.ADDITION));

}
