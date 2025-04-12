package com.example.item;

import com.example.ExampleMod;
import com.example.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExampleMod.MODID);

    public static final String EXAMPLE_MOD_TAB_STRING = "creativetab.example_tab";

    public static final Supplier<CreativeModeTab> EXAMPLE_TAB  = CREATIVE_MODE_TABS.register("example_tab",() -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable(EXAMPLE_MOD_TAB_STRING))
            .icon(() -> ModItems.SUPPORT_BEACON.get().getDefaultInstance())
            .displayItems((pParameters, output) -> {
                output.accept(ModItems.RUBY.get());
                output.accept(ModItems.MAGIC_INGOT.get());
                output.accept(ModItems.SUPPORT_BEACON.get());
                output.accept(ModItems.HIGH_ENERGY_CRYSTAL.get());
                output.accept(ModBlocks.RUBY_BLOCK.get());
                output.accept(ModBlocks.LAMP_BLOCK.get());
                output.accept(ModBlocks.HIGH_ENERGY_CRYSTAL_BLOCK.get());
            })
            .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
