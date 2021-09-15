package com.NuclearFusion.datagen.LootTable.Block;

import com.NuclearFusion.block.BlockRegistry;

public class BlockLootTable extends BaseBlockLootTables {

    @Override
    protected void addTables() {
        skip(BlockRegistry.BLOCK_BELLADONNA.get(), BlockRegistry.BLOCK_SKYFAERY.get(), BlockRegistry.BLOCK_CHILIPEPPER.get(), BlockRegistry.BLOCK_DATURA.get());
        dropSelfWithContents(BlockRegistry.getBlocks());
    }
}