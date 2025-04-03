package com.example.code;

import com.example.LoyaltyMod;
import com.example.item.SupportBeaconItem;
import com.example.item.HighEnergyCrystal;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class Registrar {

    // 创建一个延迟注册表，用于注册属于"examplemod"命名空间的方块
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LoyaltyMod.MODID);
    // 创建一个延迟注册表，用于注册属于"examplemod"命名空间的物品
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LoyaltyMod.MODID);
    // 创建一个延迟注册表，用于注册属于"examplemod"命名空间的创造模式标签
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LoyaltyMod.MODID);

    // 支援信标
    public static final DeferredItem<Item> SUPPORT_BEACON = ITEMS.register("support_beacon", () -> new SupportBeaconItem(new Item.Properties()));

    public static final Supplier<Block> HIGH_ENERGY_CRYSTAL_BLOCK = BLOCKS.register("high_energy_crystal_block",() -> new HighEnergyCrystal(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(6f).requiresCorrectToolForDrops()
            .lightLevel(state->state.getValue(HighEnergyCrystal.LIT)?15:0)));

    public static final Supplier<Item> HIGH_ENERGY_CRYSTAL = ITEMS.register("high_energy_crystal",()->new BlockItem(HIGH_ENERGY_CRYSTAL_BLOCK.get(), new Item.Properties()));

    // 创建一个创造模式标签，其ID为"examplemod:example_tab"，放置在战斗标签之后
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LOYALTY_TYPE = CREATIVE_MODE_TABS.register("loyalty_type", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.loyaltymod")) // 创造模式标签的语言键
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> SUPPORT_BEACON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(SUPPORT_BEACON.get());
                output.accept(HIGH_ENERGY_CRYSTAL.get());
                output.accept(HIGH_ENERGY_CRYSTAL_BLOCK.get());
            }).build());

    public static void into(IEventBus modEventBus) {
        // 注册延迟注册表到mod事件总线以便注册方块
        BLOCKS.register(modEventBus);
        // 注册延迟注册表到mod事件总线以便注册物品
        ITEMS.register(modEventBus);
        // 注册延迟注册表到mod事件总线以便注册标签
        CREATIVE_MODE_TABS.register(modEventBus);

//        ITEMS.register("support_beacon",() -> new SupportBeaconItem(new Item.Properties()));

    }

}
