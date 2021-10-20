package com.NuclearFusion.datagen.model;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.block.crop.Shrub;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.TieredItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Set;
import java.util.stream.Collectors;

import static com.NuclearFusion.api.ResourceLocationHelper.prefix;

//此处借鉴了植物魔法
public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {
    private static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    private static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");

    public ItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Naturalistia.MOD_ID, existingFileHelper);
    }

    private static String name(Item i) {
        return Registry.ITEM.getKey(i).getPath();
    }

    @Override
    protected void registerModels() {
        Set<Item> items = Registry.ITEM.stream()
                .filter(i -> Naturalistia.MOD_ID.equals(Registry.ITEM.getKey(i).getNamespace()))//筛选出我们mod的物品注册
                .collect(Collectors.toSet());
        registerItemBlock(items.stream().filter(i -> i instanceof BlockItem).map(i -> (BlockItem) i).collect(Collectors.toSet()));
        registerItem(items);
    }

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

    private void registerItemBlock(Set<BlockItem> itemBlocks) {
        //清除一些不想自动注册的模型
        itemBlocks.removeAll(itemBlocks.stream()
                .filter(i -> i.getBlock() instanceof Shrub)
                .collect(Collectors.toSet()));

        itemBlocks.forEach(i -> {
            String name = Registry.ITEM.getKey(i).getPath();
            withExistingParent(name, prefix("block/" + name));
        });
    }

    private void registerItem(Set<Item> items) {
        items.removeAll(items.stream().filter(i -> i instanceof BlockItem || i instanceof CrossbowItem).collect(Collectors.toSet()));
        items.forEach(this::generatedItem);
        items.stream().filter(i -> i instanceof TieredItem).forEach(this::handheldItem);
    }
}
