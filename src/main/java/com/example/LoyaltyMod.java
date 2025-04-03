package com.example;

import com.example.code.Registrar;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// 此值应与META-INF/mods.toml文件中的条目匹配
@Mod(LoyaltyMod.MODID)
public class LoyaltyMod
{
    // 在所有地方定义一个通用的mod ID供引用
    public static final String MODID = "loyaltymod";
    // 直接引用slf4j日志记录器
    private static final Logger LOGGER = LogUtils.getLogger();

    // Mod类的构造函数是mod加载时运行的第一段代码。
    // FML会自动识别某些参数类型（如IEventBus或ModContainer）并传递它们。
    public LoyaltyMod(IEventBus modEventBus)
    {
        // 注册commonSetup方法以进行mod加载
        modEventBus.addListener(this::commonSetup);

        Registrar.into(modEventBus);

        // 注册我们自己以响应感兴趣的服务器和其他游戏事件。
        // 仅当此类（ExampleMod）需要直接响应事件时才需要此行。如果没有此类中的@SubscribeEvent注解函数，则不应添加此行。
        NeoForge.EVENT_BUS.register(this);

        // 注册物品到创造模式标签
        modEventBus.addListener(this::addCreative);

        // 注册mod的ModConfigSpec，以便FML可以为我们创建和加载配置文件
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    /**
     * 公共设置方法，在mod加载期间执行一些通用设置代码。
     *
     * @param event FML公共设置事件对象，提供事件上下文
     */
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // 执行一些通用设置代码
        LOGGER.info("来自公共设置的问候");

        if (Config.logDirtBlock)
            LOGGER.info("泥土方块 >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

//        Config.items.forEach((item) -> LOGGER.info("物品 >> {}", item.toString()));
    }

    /**
     * 将示例方块物品添加到建筑方块标签中。
     *
     * @param event 构建创造模式标签内容事件对象，提供事件上下文
     */
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(EXAMPLE_BLOCK_ITEM);
    }

    /**
     * 当服务器启动时调用此方法，并通过事件总线发现可调用的方法。
     *
     * @param event 服务器启动事件对象，提供事件上下文
     */
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // 当服务器启动时执行某些操作
        LOGGER.info("来自服务器启动的问候");
    }

    /**
     * 使用EventBusSubscriber自动注册所有带有@SubscribeEvent注解的静态方法。
     */
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        /**
         * 客户端设置方法，在客户端初始化时执行一些客户端设置代码。
         *
         * @param event FML客户端设置事件对象，提供事件上下文
         */
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // 执行一些客户端设置代码
            LOGGER.info("来自客户端设置的问候");
            LOGGER.info("MINECRAFT 名称 >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
