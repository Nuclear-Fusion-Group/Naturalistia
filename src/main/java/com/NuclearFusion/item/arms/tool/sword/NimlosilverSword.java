package com.NuclearFusion.item.arms.tool.sword;

import com.NuclearFusion.api.ModItemTier;
import com.NuclearFusion.api.NimlosilverBuff;
import com.NuclearFusion.item.ItemRegister;
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

public class NimlosilverSword extends NimlosilverBuff {

    public NimlosilverSword() {
        super(new ModItemTier(3, 250, 10.0F, 3.0F, 30, ItemRegister.ITEM_INGOT_NIMLOSILVER.get()), 1, -2.4F, ItemRegister.defaultBuilder());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        //.getWindow().getWindow();
        long WINDOW = Minecraft.getInstance().getMainWindow().getHandle();
        tooltip.add(new TranslationTextComponent("message.Sword.describe"));
        if (InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT)) {
            tooltip.add(new TranslationTextComponent("message.Release_shift"));
            tooltip.add(new TranslationTextComponent("message.Sword.info"));
        } else {
            tooltip.add(new TranslationTextComponent("message.Press_shift"));
        }
    }
}

