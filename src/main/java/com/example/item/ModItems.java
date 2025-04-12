package com.example.item;

import com.example.ExampleMod;
import com.example.block.ModBlocks;
import com.example.item.custom.MagicIngot;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, ExampleMod.MODID);

    public static final Supplier<Item> RUBY = ITEMS.register("ruby",() -> new Item(new Item.Properties()));

    public static final Supplier<Item> MAGIC_INGOT = ITEMS.register("magic_ingot", MagicIngot::new);

    // 支援信标
    public static final Supplier<Item> SUPPORT_BEACON = ITEMS.register("support_beacon", () -> new SupportBeaconItem(new Item.Properties()));

    public static final Supplier<Item> HIGH_ENERGY_CRYSTAL = ITEMS.register("high_energy_crystal",()->new BlockItem(ModBlocks.HIGH_ENERGY_CRYSTAL_BLOCK.get(), new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
