package com.example.data;

import com.example.block.ModBlocks;
import com.example.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {


    public ModLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add(ModItems.RUBY.get(),"Ruby");
        this.add(ModBlocks.RUBY_BLOCK.get(),"Ruby Block");
        this.add("object.examplemod.example_object","Example Object");
    }
}
