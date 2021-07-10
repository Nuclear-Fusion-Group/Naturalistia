package com.NuclearFusion.datagen.LootTable.Block;

import com.NuclearFusion.block.BlockRegistry;

public class BlockLootTable extends BaseBlockLootTables {

    @Override
    protected void addTables() {
        skip(BlockRegistry.BLOCK_BOTANIC_CRUCIBLE.get());
        dropSelfWithContents(BlockRegistry.getBlocks());
    }
}
