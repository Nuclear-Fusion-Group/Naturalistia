package com.NuclearFusion.event.listener;

import com.NuclearFusion.item.interfaces.ILeftClick;
import com.NuclearFusion.network.ModNetworkManager;
import com.NuclearFusion.network.client.LeftClickClient;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class OnLeftClick {
    @SubscribeEvent
    public static void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        ItemStack itemStack = Minecraft.getInstance().player.getHeldItemMainhand();

        if (itemStack.getItem() instanceof ILeftClick) {
            ModNetworkManager.clientSendToServer(new LeftClickClient());
        }
    }
}
