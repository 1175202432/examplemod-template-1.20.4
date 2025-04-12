package com.example.block;

import com.example.ExampleMod;
import com.example.block.custom.HighEnergyCrystal;
import com.example.block.custom.LampBlock;
import com.example.block.custom.RubyBlock;
import com.example.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, ExampleMod.MODID);

    public static final Supplier<Block> RUBY_BLOCK = registerBlock("ruby_block", RubyBlock::new);
    public static final Supplier<Block> LAMP_BLOCK = registerBlock("lamp_block",()->new LampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(6f).requiresCorrectToolForDrops()
            .lightLevel(state->state.getValue(LampBlock.LIT)?15:0)));

    public static final Supplier<Block> HIGH_ENERGY_CRYSTAL_BLOCK = BLOCKS.register("high_energy_crystal_block",() -> new HighEnergyCrystal(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(6f).requiresCorrectToolForDrops()
            .lightLevel(state->state.getValue(HighEnergyCrystal.LIT)?15:0)));

    public static Supplier<Block> registerBlock(String name,Supplier<Block> block){
        Supplier<Block> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }
    public static void registerBlockItem(String name, Supplier<Block> block){
        registerBlockItem(name, block, new Item.Properties());
    }
    public static void registerBlockItem(String name, Supplier<Block> block, Item.Properties properties){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
