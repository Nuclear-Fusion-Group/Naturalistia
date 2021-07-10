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

    public static final RegistryObject<Item> ITEM_BOTANIC_CRUCIBLE = ITEMS.register("botanic_crucible", () -> new BlockItem(BlockRegistry.BLOCK_BOTANIC_CRUCIBLE.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_BOTANIC_ALTAR = ITEMS.register("botanic_altar", () -> new BlockItem(BlockRegistry.BLOCK_BOTANIC_ALTAR.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_BELLADONNA = ITEMS.register("belladonna", () -> new BlockItem(BlockRegistry.BLOCK_BELLADONNA.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_CHILIPEPPER = ITEMS.register("chilipepper", () -> new BlockItem(BlockRegistry.BLOCK_CHILIPEPPER.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_DATURA = ITEMS.register("datura", () -> new BlockItem(BlockRegistry.BLOCK_DATURA.get(), defaultBuilder()));
    public static final RegistryObject<Item> ITEM_SKYFAERY = ITEMS.register("skyfaery", () -> new BlockItem(BlockRegistry.BLOCK_SKYFAERY.get(), defaultBuilder()));

    //��ǩҳ
    public static ItemGroup creativeTab = new ItemGroup(Naturalistia.MOD_ID) {
        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegister.ITEM_BOTANIC_CRUCIBLE.get());
        }
    };

    /**
     * �Զ����뵽��ǩҳ
     */
    public static Item.Properties defaultBuilder() {
        return new Item.Properties().group(creativeTab);
    }
}
