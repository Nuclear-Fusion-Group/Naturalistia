package com.NuclearFusion.datagen.LootTable;

import com.NuclearFusion.datagen.LootTable.Block.BaseBlockLootTables;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class BaseLootProvider extends LootTableProvider {
    private final String modid;

    public BaseLootProvider(DataGenerator gen, String modid) {
        super(gen);
        this.modid = modid;
    }

    @Nonnull
    @Override
    public String getName() {
        return super.getName() + ":" + modid;
    }

    @Nullable
    protected BaseBlockLootTables getBlockLootTable(){
        return null;
    }



    @Nonnull
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        ImmutableList.Builder<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> builder = new ImmutableList.Builder<>();
        BaseBlockLootTables blockLootTable = getBlockLootTable();
        if (blockLootTable != null) {
            builder.add(Pair.of(() -> blockLootTable, LootParameterSets.BLOCK));
        }
        return builder.build();
    }
}
