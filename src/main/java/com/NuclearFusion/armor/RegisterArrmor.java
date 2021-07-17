package com.NuclearFusion.armor;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.item.ItemRegister;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterArrmor {

            public static final DeferredRegister<Item> ARMORS = DeferredRegister.create(ForgeRegistries.ITEMS, Naturalistia.MOD_ID);

            public static final RegistryObject<Item> CHLOROPHYLL_HELMET= ARMORS.register("chlorophyll_helmet",
            ()-> new ArmorItem(new ObjChlorophyll(), EquipmentSlotType.HEAD,(new Item.Properties()).group(ItemRegister.creativeTab)));

            public static final RegistryObject<Item> CHLOROPHYLL_BODY=ARMORS.register("chlorophyll_body",
            ()-> new ArmorItem(new ObjChlorophyll(), EquipmentSlotType.CHEST,(new Item.Properties()).group(ItemRegister.creativeTab)));

            public static final RegistryObject<Item> CHLOROPHYLL_BOOTS=ARMORS.register("chlorophyll_chain",
            ()-> new ArmorItem(new ObjChlorophyll(),EquipmentSlotType.LEGS,(new Item.Properties()).group(ItemRegister.creativeTab)));

            public static final RegistryObject<Item> CHLOROPHYLL_CHAIN=ARMORS.register("chlorophyll_chain",
            ()-> new ArmorItem(new ObjChlorophyll(), EquipmentSlotType.FEET,(new Item.Properties()).group(ItemRegister.creativeTab)));

}
