package com.NuclearFusion.datagen.model;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.block.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.item.BlockItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Set;
import java.util.stream.Collectors;

import static com.NuclearFusion.api.ResourceLocationHelper.prefix;

//此处借鉴了植物魔法
public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {

    public ItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Naturalistia.MOD_ID, existingFileHelper);
    }

    @Override
    protected void generateAll(DirectoryCache cache) {
        super.generateAll(cache);
    }

    @Override
    protected void registerModels() {
        Set<Item> items = Registry.ITEM.stream()
                .filter(i -> Naturalistia.MOD_ID.equals(Registry.ITEM.getKey(i).getNamespace()))//筛选出我们mod的物品注册
                .collect(Collectors.toSet());
        registerItemBlock(items.stream().filter(i -> i instanceof BlockItem).map(i -> (BlockItem) i).collect(Collectors.toSet()));
        registerItem(items);
    }

    private static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    private static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");

    private ItemModelBuilder handheldItem(String name) {
        return withExistingParent(name, HANDHELD)
                .texture("layer0", prefix("items/" + name));
    }

    private ItemModelBuilder handheldItem(Item i) {
        return handheldItem(name(i));
    }

    private ItemModelBuilder generatedItem(String name) {
        return withExistingParent(name, GENERATED)
                .texture("layer0", prefix("items/" + name));
    }

    private ItemModelBuilder generatedItem(Item i) {
        return generatedItem(name(i));
    }

    private static String name(Item i) {
        return Registry.ITEM.getKey(i).getPath();
    }

    private void registerItemBlock(Set<BlockItem> itemBlocks) {
        //TODO 这里没想到一个优雅的解决方案
        itemBlocks.remove(BlockRegistry.BLOCK_BELLADONNA.get().asItem());
        itemBlocks.remove(BlockRegistry.BLOCK_CHILIPEPPER.get().asItem());
        itemBlocks.remove(BlockRegistry.BLOCK_DATURA.get().asItem());
        itemBlocks.remove(BlockRegistry.BLOCK_SKYFAERY.get().asItem());

        itemBlocks.forEach(i -> {
            String name = Registry.ITEM.getKey(i).getPath();
            withExistingParent(name, prefix("block/" + name));
        });
    }

    private void registerItem(Set<Item> items) {
        items.removeAll(items.stream().filter(i -> i instanceof BlockItem || i instanceof CrossbowItem).collect(Collectors.toSet()));
        items.stream().filter(i -> i instanceof ToolItem).forEach(this::handheldItem);
        items.forEach(this::generatedItem);
    }
}
