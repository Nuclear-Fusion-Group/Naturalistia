package com.NuclearFusion.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Iterator;

public class TestCommand implements Command<CommandSource> {
    public static TestCommand instance = new TestCommand();

    private ItemStack item;

    @Override
    public int run(CommandContext<CommandSource> context) {

        try {
            item = context.getSource().asPlayer().getHeldItem(Hand.MAIN_HAND);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        CompoundNBT nbt = item.getOrCreateTag();
        Iterator<String> iterator = nbt.keySet().iterator();
        String key;

        //以后可以把这些改成自定义项
        context.getSource().sendFeedback(new TranslationTextComponent("§r物品id:" + item.getTranslationKey()), false);
        context.getSource().sendFeedback(new TranslationTextComponent("§e物品tags内容:" + item.getItem().getTags()), false);
        context.getSource().sendFeedback(new TranslationTextComponent("§b物品nbt内容:"), false);

        while (true) {
            if (iterator.hasNext()) {
                key = iterator.next();
                context.getSource().sendFeedback(new TranslationTextComponent("§b" + key + ":" + nbt.get(key)), false);
            } else {
                break;
            }
        }
        return 0;

    }
}
