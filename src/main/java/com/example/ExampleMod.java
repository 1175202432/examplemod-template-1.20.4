package com.example;

import com.example.block.ModBlocks;
import com.example.item.ModCreativeTab;
import com.example.item.ModItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// 此值应与META-INF/mods.toml文件中的条目匹配
@Mod(ExampleMod.MODID)
public class ExampleMod
{
    // 在所有地方定义一个通用的mod ID供引用
    public static final String MODID = "examplemod";

    private static final Logger LOGGER = LogUtils.getLogger();
    public ExampleMod(IEventBus modEventBus)
    {
        modEventBus.addListener(this::commonSetup);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeTab.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }
}
