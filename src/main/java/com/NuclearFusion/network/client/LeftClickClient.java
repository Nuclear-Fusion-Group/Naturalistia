package com.NuclearFusion.network.client;

import com.NuclearFusion.item.interfaces.ILeftClick;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author DustW
 */
public class LeftClickClient implements IClientMessage{
    public static void encode(LeftClickClient msg, PacketBuffer packetBuffer) {

    }

    public static LeftClickClient decode(PacketBuffer packetBuffer) {
        return new LeftClickClient();
    }

    public static void handle(LeftClickClient msg, Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            PlayerEntity player = (PlayerEntity) Minecraft.getInstance().player;
            ItemStack itemStack = player.getHeldItemMainhand();
            if (itemStack.getItem() instanceof ILeftClick) {
                ((ILeftClick) itemStack.getItem()).onLeftClick(itemStack, player);
            }
        });
    }
}
