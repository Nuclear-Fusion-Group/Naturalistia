package com.NuclearFusion.item;

import com.NuclearFusion.block.BlockRegistry;
import com.NuclearFusion.Naturalistia;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class ItemRegister {
    //��Ʒע��
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Naturalistia.MOD_ID);

    public static final RegistryObject<Item> crucible_made_of_plants = ITEMS.register("crucible_made_of_plants", () -> new BlockItem(BlockRegistry.crucible_made_of_plants.get(), defaultBuilder()));

    public static final RegistryObject<Item> belladonna = ITEMS.register("belladonna", () -> new BlockItem(BlockRegistry.belladonna.get(), defaultBuilder()));
    public static final RegistryObject<Item> chilipepper = ITEMS.register("chilipepper", () -> new BlockItem(BlockRegistry.chilipepper.get(), defaultBuilder()));
    public static final RegistryObject<Item> datura = ITEMS.register("datura", () -> new BlockItem(BlockRegistry.datura.get(), defaultBuilder()));
    public static final RegistryObject<Item> skyfaery = ITEMS.register("skyfaery", () -> new BlockItem(BlockRegistry.skyfaery.get(), defaultBuilder()));

    //��ǩҳ
    public static ItemGroup creativeTab = new ItemGroup(Naturalistia.MOD_ID) {
        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegister.crucible_made_of_plants.get());
        }
    };

    /**
     * �Զ����뵽��ǩҳ
     */
    public static Item.Properties defaultBuilder() {
        return new Item.Properties().group(creativeTab);
    }
}
