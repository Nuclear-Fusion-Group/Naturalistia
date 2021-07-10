package com.NuclearFusion.datagen.LootTable.Block;

import com.NuclearFusion.api.IHasTileEntity;
import com.NuclearFusion.api.NBTConstants;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.functions.CopyNbt;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

//该函数参考了mek的实现
public abstract class BaseBlockLootTables extends BlockLootTables {

    private final Set<Block> knownBlocks = new ObjectOpenHashSet<>();
    private final Set<Block> toSkip = new ObjectOpenHashSet<>();

    @Override
    protected abstract void addTables();

    /**
     * 添加注册的方块
     *
     * @param block
     * @param table
     */
    @Override
    protected void registerLootTable(Block block, LootTable.Builder table) {
        super.registerLootTable(block, table);
        knownBlocks.add(block);
    }

    /**
     * 获取注册的方块
     *
     * @return
     */
    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return knownBlocks;
    }

    /**
     * 添加跳过方块
     *
     * @param blocks
     */
    protected void skip(Block... blocks) {
        Collections.addAll(toSkip, blocks);
    }

    /**
     * 判断是否为已注册或忽略的方块
     *
     * @param block
     * @return
     */
    protected boolean skipBlock(Block block) {
        return knownBlocks.contains(block) || toSkip.contains(block);
    }

    /**
     * 批量注册方块战利品表
     *
     * @param blocks
     */
    protected void dropSelfWithContents(List<RegistryObject<Block>> blocks) {
        for (RegistryObject<Block> block1 : blocks) {
            //获取方块
            Block block = block1.get();
            //判断是否为已注册方块
            if (skipBlock(block)) {
                continue;
            }
            //获取默认nbt构造
            CopyNbt.Builder nbtBuilder = CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY);

            boolean hasData = false;
            @Nullable
            TileEntity tile = null;
            //获取TileEntity
            if (block instanceof IHasTileEntity) {
                tile = ((IHasTileEntity) block).getTileType().create();
            }
            if (tile != null) {
                if (!(tile instanceof IItemHandler) || ((IItemHandler) tile).getSlots() > 0) {
                    nbtBuilder.replaceOperation(NBTConstants.ITEMS, NBTConstants.DATA + "." + NBTConstants.ITEMS);
                    hasData = true;
                }
            }
            //判断是否有数据 如果没有就生成默认战利品表
            if (!hasData) {
                registerLootTable(block, LootTable.builder().addLootPool(withExplosionDecay(block, LootPool.builder()
                                .name("main")
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(block))
                        ))
                );
            } else {
                registerLootTable(block, LootTable.builder().addLootPool(withExplosionDecay(block, LootPool.builder()
                                .name("main")
                                .rolls(ConstantRange.of(1))
                                .addEntry(ItemLootEntry.builder(block).acceptFunction(nbtBuilder))
                        ))
                );
            }
        }
    }
}
