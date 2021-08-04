package com.NuclearFusion.datagen.LootTable.Block;

import com.NuclearFusion.block.BlockRegistry;

public class BlockLootTable extends BaseBlockLootTables {

    @Override
    protected void addTables() {
        skip();
        dropSelfWithContents(BlockRegistry.getBlocks());
    }
}