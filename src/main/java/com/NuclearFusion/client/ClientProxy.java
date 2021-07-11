package com.NuclearFusion.client;

import com.NuclearFusion.IProxy;
import com.NuclearFusion.client.handler.HUDHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class ClientProxy extends IProxy {

    public ClientProxy(){

    }

    @Override
    public void registerHandlers(){
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(HUDHandler::onDrawScreenPost);
    }
}
