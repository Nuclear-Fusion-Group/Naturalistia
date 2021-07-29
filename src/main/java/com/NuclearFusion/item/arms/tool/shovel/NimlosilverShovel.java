package com.NuclearFusion.item.arms.tool.shovel;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.item.ItemRegister;
import com.NuclearFusion.item.arms.tool.NaturalistiaShovel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;

public class NimlosilverShovel extends NaturalistiaShovel {
    public NimlosilverShovel() {
        super(1.5F, -3.0F, new ModItemTier(3, 1000, 10.0F, 1.5F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 3, ItemRegister.defaultBuilder());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        //.getWindow().getWindow();
        long WINDOW = Minecraft.getInstance().getMainWindow().getHandle();
        tooltip.add(new TranslationTextComponent("message.Shovel.describe"));
        if (InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT)) {
            tooltip.add(new TranslationTextComponent("message.Release_shift"));
            tooltip.add(new TranslationTextComponent("message.Shovel.info"));
        }else {
            tooltip.add(new TranslationTextComponent("message.Press_shift"));
        }
    }

}
