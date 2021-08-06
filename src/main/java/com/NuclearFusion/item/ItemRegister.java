package com.NuclearFusion.item;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.block.BlockRegistry;
import com.NuclearFusion.item.arms.armor.Chlorophyll;
import com.NuclearFusion.item.arms.armor.ChlorophyllBoots;
import com.NuclearFusion.item.arms.crossbow.NimlosilverCrossbow;
import com.NuclearFusion.item.arms.tool.hoe.ChlorophyllHoe;
import com.NuclearFusion.item.arms.tool.mace.NimlosilverMace;
import com.NuclearFusion.item.arms.tool.pickaxe.ChlorophyllPickaxe;
import com.NuclearFusion.item.arms.tool.shovel.NimlosilverShovel;
import com.NuclearFusion.item.arms.tool.spear.NimlosilverSpear;
import com.NuclearFusion.item.arms.tool.sword.NimlosilverSword;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Naturalistia.MOD_ID);

    public static final RegistryObject<Item> ITEM_BOTANIC_CRUCIBLE = ITEMS.register("botanic_crucible", () -> new BlockItem(BlockRegistry.BLOCK_BOTANIC_CRUCIBLE.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_BOTANIC_ALTAR = ITEMS.register("botanic_altar", () -> new BlockItem(BlockRegistry.BLOCK_BOTANIC_ALTAR.get(), defaultBuilder()));

    public static final RegistryObject<Item> ITEM_EBONY_STICK = ITEMS.register("ebony_stick", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ITEM_MANA_DIAMOND = ITEMS.register("mana_diamond", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ITEM_RIB = ITEMS.register("rib", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ITEM_SILVER_KEY = ITEMS.register("silver_key", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ITEM_SPIRIT_BALL = ITEMS.register("spirit_ball", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ITEM_ZOMBIE_HEART = ITEMS.register("zombie_heart", () -> new Item(defaultBuilder()));

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
    public static final RegistryObject<Item> ITEM_ORE_CHLOROPLAST = ITEMS.register("ore_chloroplast", () -> new BlockItem(BlockRegistry.BLOCK_ORE_CHLOROPLAST.get(), defaultBuilder()));

    public static final RegistryObject<Item> ITEM_ORE_COPPER_BLOCK = ITEMS.register("copper_block", () -> new BlockItem(BlockRegistry.BLOCK_ORE_COPPER_BLOCK.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_ORE_NIMLOSILVER_BLOCK = ITEMS.register("nimlosilver_block", () -> new BlockItem(BlockRegistry.BLOCK_ORE_NIMLOSILVER_BLOCK.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_ORE_CHLOROPHYLL_BLOCK = ITEMS.register("chlorophyll_block", () -> new BlockItem(BlockRegistry.BLOCK_ORE_CHLOROPHYLL_BLOCK.get(), defaultBuilder()));

    public static final RegistryObject<Item> ITEM_INGOT_MALACHITE = ITEMS.register("copper", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ITEM_INGOT_NIMLOSILVER = ITEMS.register("nimlosilver", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ITEM_INGOT_CHLOROPHYLL = ITEMS.register("chlorophyll", () -> new Item(defaultBuilder()));
    //叶绿锁链
    public static final RegistryObject<Item> ITEM_CHLOROPHYLL_CHAIN = ITEMS.register("chlorophyll_chain", () -> new Item(defaultBuilder()));

    public static final RegistryObject<Item> ITEM_ARMS_SWORD_NIMLOSILVER = ITEMS.register("nimlosilver_sword", NimlosilverSword::new);
    public static final RegistryObject<Item> ITEM_ARMS_SHOVEL_NIMLOSILVER = ITEMS.register("nimlosilver_shovel", NimlosilverShovel::new);
    /** 狼牙棒 */
    public static final RegistryObject<Item> ITEM_ARMS_SHOVEL_MACE = ITEMS.register("nimlosilver_mace", NimlosilverMace::new);
    //短矛
    public static final RegistryObject<Item> ITEM_ARMS_SHOVEL_SPEAR = ITEMS.register("nimlosilver_spear", NimlosilverSpear::new);
    //种子弯刀
    public static final RegistryObject<Item> ITEM_CHLOROPHYLL_HOE = ITEMS.register("chlorophyll_hoe", ChlorophyllHoe::new);
    public static final RegistryObject<Item> ITEM_ARMS_CHLOROPHYLL_PICKAXE = ITEMS.register("chlorophyll_pickaxe", ChlorophyllPickaxe::new);

    public static final RegistryObject<Item> ITEM_ARMS_CROSSBOW_NIMLOSILVER = ITEMS.register("nimlosilver_crossbow", NimlosilverCrossbow::new);

    public static final RegistryObject<Item> CHLOROPHYLL_HELMET = ITEMS.register("chlorophyll_helmet",
            () -> new ArmorItem(new Chlorophyll(), EquipmentSlotType.HEAD, defaultBuilder()));

    public static final RegistryObject<Item> CHLOROPHYLL_BODY = ITEMS.register("chlorophyll_body",
            () -> new ArmorItem(new Chlorophyll(), EquipmentSlotType.CHEST, defaultBuilder()));

    public static final RegistryObject<Item> CHLOROPHYLL_LEGS = ITEMS.register("chlorophyll_legs",
            () -> new ArmorItem(new Chlorophyll(), EquipmentSlotType.LEGS, defaultBuilder()));

    public static final RegistryObject<Item> CHLOROPHYLL_BOOTS = ITEMS.register("chlorophyll_boots", ChlorophyllBoots::new);

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
