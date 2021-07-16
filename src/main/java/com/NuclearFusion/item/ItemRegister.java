package com.NuclearFusion.item;

import com.NuclearFusion.block.BlockRegistry;
import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.item.arms.NimlosilverShovel;
import com.NuclearFusion.item.arms.NimlosilverSword;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Naturalistia.MOD_ID);

    public static final RegistryObject<Item> ITEM_BOTANIC_CRUCIBLE = ITEMS.register("botanic_crucible", () -> new BlockItem(BlockRegistry.BLOCK_BOTANIC_CRUCIBLE.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_BOTANIC_ALTAR = ITEMS.register("botanic_altar", () -> new BlockItem(BlockRegistry.BLOCK_BOTANIC_ALTAR.get(), defaultBuilder()));

    public static final RegistryObject<Item> ITEM_BELLADONNA = ITEMS.register("belladonna", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ITEM_CHILIPEPPER = ITEMS.register("chilipepper", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ITEM_DATURA = ITEMS.register("datura", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ITEM_SKYFAERY = ITEMS.register("skyfaery", () -> new Item(defaultBuilder()));

    public static final RegistryObject<Item> ITEM_BELLADONNA_SEED = ITEMS.register("belladonna_seed", () -> new BlockItem(BlockRegistry.BLOCK_BELLADONNA.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_CHILIPEPPER_SEED = ITEMS.register("chilipepper_seed", () -> new BlockItem(BlockRegistry.BLOCK_CHILIPEPPER.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_DATURA_SEED = ITEMS.register("datura_seed", () -> new BlockItem(BlockRegistry.BLOCK_DATURA.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_SKYFAERY_SEED = ITEMS.register("skyfaery_seed", () -> new BlockItem(BlockRegistry.BLOCK_SKYFAERY.get(), defaultBuilder()));

    public static final RegistryObject<Item> ITEM_ORE_MALACHITE = ITEMS.register("ore_malachite", () -> new BlockItem(BlockRegistry.BLOCK_ORE_MALACHITE.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_ORE_NIMLOSILVER = ITEMS.register("ore_nimlosilver", () -> new BlockItem(BlockRegistry.BLOCK_ORE_NIMLOSILVER.get(), defaultBuilder()));

    public static final RegistryObject<Item> ITEM_ORE_COPPER_BLOCK = ITEMS.register("copper_block", () -> new BlockItem(BlockRegistry.BLOCK_ORE_COPPER_BLOCK.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_ORE_NIMLOSILVER_BLOCK = ITEMS.register("nimlosilver_block", () -> new BlockItem(BlockRegistry.BLOCK_ORE_NIMLOSILVER_BLOCK.get(), defaultBuilder()));

    public static final RegistryObject<Item> ITEM_INGOT_MALACHITE = ITEMS.register("copper", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ITEM_INGOT_NIMLOSILVER = ITEMS.register("nimlosilver", () -> new Item(defaultBuilder()));

    public static final RegistryObject<Item> ITEM_ARMS_SWORD_NIMLOSILVER = ITEMS.register("nimlosilver_sword", NimlosilverSword::new);
    public static final RegistryObject<Item> ITEM_ARMS_SHOVEL_NIMLOSILVER = ITEMS.register("nimlosilver_shovel", NimlosilverShovel::new);

    public static ItemGroup creativeTab = new ItemGroup(Naturalistia.MOD_ID) {
        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegister.ITEM_BOTANIC_ALTAR.get());
        }
    };

    public static Item.Properties defaultBuilder() {
        return new Item.Properties().group(creativeTab);
    }
}
