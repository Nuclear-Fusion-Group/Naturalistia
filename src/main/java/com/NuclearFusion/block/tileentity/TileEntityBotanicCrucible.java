package com.NuclearFusion.block.tileentity;

import com.NuclearFusion.Naturalistia;
import com.NuclearFusion.recipe.IRecipeManager;
import com.NuclearFusion.recipe.crucible.CrucibleRecipe;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import slimeknights.mantle.util.ItemStackList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Part of the codes are written by Vazkii, in Botania repository on GitHub.
 */
public class TileEntityBotanicCrucible extends TileFluidHandler implements ITickableTileEntity {

    public static final int CAPACITY = FluidAttributes.BUCKET_VOLUME * 4;

    private int progress;
    private boolean needRecheckRecipe;
    private CrucibleRecipe onGoingRecipe;
    private CrucibleRecipe prevRecipe;

    public ItemStackHandler itemStackHandler = new ItemStackHandler(6){
        @Override
        protected void onContentsChanged(int slot){
            needRecheckRecipe = true;
            BlockState state = world.getBlockState(pos);
            getWorld().notifyBlockUpdate(pos, state, state, 3);
            world.notifyNeighborsOfStateChange(pos, state.getBlock());
            TileEntityBotanicCrucible.this.markDirty();
        }
    };

    public TileEntityBotanicCrucible() {
        super(TileEntityRegistry.REGISTRY_OBJECT_TILE_ENTITY_BOTANIC_CRUCIBLE.get());
        tank = new FluidTank(CAPACITY){
            @Override
            protected void onContentsChanged(){
                needRecheckRecipe = true;
                markDirty();
                getWorld().notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
            }
        };
    }

    public float getPercentage(){
        return ((float) progress/(float) onGoingRecipe.timeTaken);
    }


    public boolean collideWithItemEntity(ItemEntity itemEntity){
        ItemStack stack = itemEntity.getItem();
        if (stack.isEmpty() || !itemEntity.isAlive()) {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            if(itemStackHandler.getStackInSlot(i)==ItemStack.EMPTY){
                itemStackHandler.setStackInSlot(i, stack);
                world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 0.1F, 10F);
                itemEntity.remove();
                BlockState state = world.getBlockState(pos);
                getWorld().notifyBlockUpdate(pos, state, state, 3);
                world.notifyNeighborsOfStateChange(pos, state.getBlock());
                TileEntityBotanicCrucible.this.markDirty();
                return true;
            }
        }

        return false;
    }

    private final LazyOptional<IItemHandler> itemHolder = LazyOptional.of(() -> itemStackHandler);

    public ActionResultType activate(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit){
        ItemStack heldItem = player.getHeldItem(hand);

        if(heldItem.getItem() == Items.ACACIA_DOOR){
            for (int i = 0; i < 6; i++) {
                ItemStack itemStack = this.itemStackHandler.getStackInSlot(i);
            }
            return ActionResultType.PASS;
        }

        if (heldItem != ItemStack.EMPTY) {

            if ((FluidUtil.getFluidHandler(heldItem).isPresent() || heldItem.getItem() instanceof BucketItem)) {
                boolean didFill = FluidUtil.interactWithFluidHandler(player, hand, this.tank);
                if (didFill) {
                    return ActionResultType.SUCCESS;
                }
            } else {

                Naturalistia.LOGGER.info(1);

                for (int i = 0; i < 6; i++) {
                    ItemStack copy = heldItem.copy();
                    ItemStack leftOver = this.itemStackHandler.insertItem(i, heldItem, false);
                    player.setHeldItem(hand, leftOver);
                    if (!(copy.getCount()==leftOver.getCount() & copy.getItem()==leftOver.getItem())) return ActionResultType.SUCCESS;
                }
            }
        }

        for(int i = 5; i >= 0; i--){
            if(!this.itemStackHandler.getStackInSlot(i).isEmpty()){
                if (heldItem == ItemStack.EMPTY) {
                    player.setHeldItem(hand, itemStackHandler.extractItem(i, itemStackHandler.getStackInSlot(i).getCount(), false));
                    return ActionResultType.SUCCESS;
                } else {
                    if(!worldIn.isRemote) {
                        world.addEntity(new ItemEntity(world, player.getPosX(), player.getPosY(), player.getPosZ(), itemStackHandler.getStackInSlot(i)));
                        itemStackHandler.setStackInSlot(i, ItemStack.EMPTY);
                        return ActionResultType.SUCCESS;
                    }
                }
            }
        }

        return ActionResultType.FAIL;
    }

    private boolean onLit(){
        BlockPos pos = this.getPos();
        BlockState state = getWorld().getBlockState(pos.down(1));
        return state.getBlock() instanceof AbstractFireBlock;
    }

    @Override
    public void tick() {
        if(!getWorld().isRemote) {

            if(needRecheckRecipe){

                Naturalistia.LOGGER.info("1");

                ItemStackList list = ItemStackList.withSize(6);
                for (int i = 0; i < 6; i++) {
                    if(itemStackHandler.getStackInSlot(i)!=ItemStack.EMPTY){
                        list.set(i, itemStackHandler.getStackInSlot(i));
                    } else {
                        break;
                    }
                }

                Naturalistia.LOGGER.info(tank.getFluid());

                Naturalistia.LOGGER.info(list);

                onGoingRecipe = CrucibleRecipe.getRecipe(tank.getFluid(), list);

                if(onGoingRecipe != prevRecipe){

                    Naturalistia.LOGGER.info("2");

                    progress = 0;
                    if(onGoingRecipe != null){
                        prevRecipe = onGoingRecipe;
                    }
                }

                needRecheckRecipe = false;

            } else {
                if(onGoingRecipe!=null){
                    if(tank.getFluid().getAmount() + onGoingRecipe.outputFluid.getAmount() > tank.getCapacity()){
                        progress = 0;
                    }

                    Naturalistia.LOGGER.info("3");

                    if(progress == onGoingRecipe.timeTaken){

                        if(tank.getFluid().getFluid() == onGoingRecipe.outputFluid.getFluid()){
                            tank.getFluid().setAmount(tank.getFluid().getAmount() + onGoingRecipe.outputFluid.getAmount());
                            for (int i = 0; i < 6; i++) {
                                itemStackHandler.extractItem(i, 64, false);
                            }
                        } else if(tank.isEmpty()){
                            for (int i = 0; i < 6; i++) {
                                itemStackHandler.extractItem(i, 64, false);
                            }
                            tank.setFluid(onGoingRecipe.outputFluid);
                            progress = 0;
                        }
                    }
                    if(onLit()){
                        progress++;
                        Naturalistia.LOGGER.info(progress);
                    }
                }
            }

            if(!onLit() & progress>0) progress--;

            BlockState state = world.getBlockState(pos);
            getWorld().notifyBlockUpdate(pos, state, state, 3);
        }
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        itemStackHandler.deserializeNBT(nbt.getCompound("items"));

        if(!nbt.getString("onGoingRecipe").isEmpty()){
            onGoingRecipe = IRecipeManager.getCrucibleRecipeFromId(new ResourceLocation(nbt.getString("onGoingRecipe")));
        }
        if(!nbt.getString("prevRecipe").isEmpty()){
            prevRecipe = IRecipeManager.getCrucibleRecipeFromId(new ResourceLocation(nbt.getString("prevRecipe")));
        }

        progress = nbt.getInt("progress");

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT nbt = super.write(compound);
        nbt.put("items", itemStackHandler.serializeNBT());
        if(onGoingRecipe != null)
            nbt.putString("onGoingRecipe", onGoingRecipe.recipeId.toString());
        if(prevRecipe != null)
            nbt.putString("prevRecipe", prevRecipe.recipeId.toString());
        nbt.putInt("progress", progress);
        return nbt;
    }

    @Override
    public final SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT tag = new CompoundNBT();
        write(tag);
        return new SUpdateTileEntityPacket(pos, -999, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        super.onDataPacket(net, packet);
        read(getBlockState() ,packet.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return write(new CompoundNBT()); // 记得写要同步的数据进去
    }


    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing)
    {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return itemHolder.cast();
        return super.getCapability(capability, facing);
    }



    public void renderHUD(Minecraft mc, MatrixStack ms){
        int xc = mc.getMainWindow().getScaledWidth() / 2;
        int yc = mc.getMainWindow().getScaledHeight() / 2;

        float angle = -90;
        int radius = 24;
        int amt = 0;
        for (int i = 0; i < 6; i++) {
            if (itemStackHandler.getStackInSlot(i).isEmpty()) {
                break;
            }
            amt++;
        }

        if (amt > 0) {
            float anglePer = 360F / amt;

            /**
            world.getRecipeManager().getRecipe(ModRecipeTypes.RUNE_TYPE, getItemHandler(), world).ifPresent(recipe -> {
                RenderSystem.enableBlend();
                RenderSystem.enableRescaleNormal();
                RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

                float progress = (float) mana / (float) manaToGet;

                mc.textureManager.bindTexture(HUDHandler.manaBar);
                RenderSystem.color4f(1F, 1F, 1F, 1F);
                RenderHelper.drawTexturedModalRect(ms, xc + radius + 9, yc - 8, progress == 1F ? 0 : 22, 8, 22, 15);

                if (progress == 1F) {
                    mc.getItemRenderer().renderItemIntoGUI(new ItemStack(ModBlocks.livingrock), xc + radius + 16, yc + 8);
                    // change to MatrixStack ops when renderItemIntoGUI starts taking MatrixStack
                    RenderSystem.translated(0, 0, 100);
                    mc.getItemRenderer().renderItemIntoGUI(new ItemStack(ModItems.twigWand), xc + radius + 24, yc + 8);
                    RenderSystem.translated(0, 0, -100);
                }

                RenderHelper.renderProgressPie(ms, xc + radius + 32, yc - 8, progress, recipe.getCraftingResult(getItemHandler()));

                if (progress == 1F) {
                    mc.fontRenderer.drawString(ms, "+", xc + radius + 14, yc + 12, 0xFFFFFF);
                }
            });
            */

            for (int i = 0; i < amt; i++) {
                double xPos = xc + Math.cos(angle * Math.PI / 180D) * radius - 8;
                double yPos = yc + Math.sin(angle * Math.PI / 180D) * radius - 8;
                // change to MatrixStack ops when renderItemIntoGUI starts taking MatrixStack
                RenderSystem.translated(xPos, yPos, 0);
                mc.fontRenderer.drawText(ms, new StringTextComponent(String.valueOf(itemStackHandler.getStackInSlot(i).getCount())), 8+8, 6+8, 0xFFFFFF);
                mc.getItemRenderer().renderItemIntoGUI(itemStackHandler.getStackInSlot(i), 0, 0);
                RenderSystem.translated(-xPos, -yPos, 0);

                angle += anglePer;
            }
        }
    }

}
