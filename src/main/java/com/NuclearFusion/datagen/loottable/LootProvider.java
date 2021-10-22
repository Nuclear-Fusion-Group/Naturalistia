package com.NuclearFusion.datagen.loottable;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.datagen.loottable.Block.BaseBlockLootTables;
import com.NuclearFusion.datagen.loottable.Block.BlockLootTable;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class LootProvider extends BaseLootProvider {
    public LootProvider(DataGenerator gen) {
        super(gen, Naturalistia.MOD_ID);
    }

    @Override
    protected BaseBlockLootTables getBlockLootTable() {
        return new BlockLootTable();
    }

    //屏蔽掉无用的信息
    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {

    }
}
