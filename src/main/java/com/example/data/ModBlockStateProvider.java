package com.example.data;

import com.example.ExampleMod;
import com.example.block.ModBlocks;
import com.example.block.custom.LampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlockWithItem(ModBlocks.RUBY_BLOCK.get(),cubeAll(ModBlocks.RUBY_BLOCK.get()));
        this.propertyBlock(ModBlocks.LAMP_BLOCK.get());
    }

    public void propertyBlock(Block block){
        var block_off = models().cubeAll("lamp_off",new ResourceLocation(ExampleMod.MODID,ModelProvider.BLOCK_FOLDER+"/"+"zircon_lamp_off"));
        var block_on = models().cubeAll("lamp_on",new ResourceLocation(ExampleMod.MODID, ModelProvider.BLOCK_FOLDER+"/"+"zircon_lamp_on"));
        getVariantBuilder(block).partialState().with(LampBlock.LIT,true)
                .modelForState().modelFile(block_on).addModel()
                .partialState().with(LampBlock.LIT,false)
                .modelForState().modelFile(block_off).addModel();
        simpleBlockItem(block,block_off);
    }



}
