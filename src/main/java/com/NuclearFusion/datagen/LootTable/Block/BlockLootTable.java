package com.NuclearFusion.datagen.LootTable.Block;

import com.NuclearFusion.block.Blocks;

public class BlockLootTable extends BaseBlockLootTables {

    @Override
    protected void addTables() {
        skip(Blocks.crucible_made_of_plants.get());
        dropSelfWithContents(Blocks.getBlocks());
    }
}
