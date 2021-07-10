package com.NuclearFusion.item;

import com.NuclearFusion.block.Blocks;
import com.NuclearFusion.Naturalistia;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class Items {
    //物品注册
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Naturalistia.MOD_ID);

    public static final RegistryObject<Item> crucible_made_of_plants = ITEMS.register("crucible_made_of_plants", () -> new BlockItem(Blocks.crucible_made_of_plants.get(), defaultBuilder()));

    public static final RegistryObject<Item> belladonna = ITEMS.register("belladonna", () -> new BlockItem(Blocks.belladonna.get(), defaultBuilder()));
    public static final RegistryObject<Item> chilipepper = ITEMS.register("chilipepper", () -> new BlockItem(Blocks.chilipepper.get(), defaultBuilder()));
    public static final RegistryObject<Item> datura = ITEMS.register("datura", () -> new BlockItem(Blocks.datura.get(), defaultBuilder()));
    public static final RegistryObject<Item> skyfaery = ITEMS.register("skyfaery", () -> new BlockItem(Blocks.skyfaery.get(), defaultBuilder()));

    //标签页
    public static ItemGroup creativeTab = new ItemGroup(Naturalistia.MOD_ID) {
        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.crucible_made_of_plants.get());
        }
    };

    /**
     * 自动导入到标签页
     */
    public static Item.Properties defaultBuilder() {
        return new Item.Properties().group(creativeTab);
    }
}
