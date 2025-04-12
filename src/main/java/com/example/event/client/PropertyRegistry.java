package com.example.event.client;

import com.example.ExampleMod;
import com.example.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class PropertyRegistry {

    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            ItemProperties.register(ModItems.MAGIC_INGOT.get(),new ResourceLocation(ExampleMod.MODID,"size"),(itemStack, level, livingEntity, num)->{
                return itemStack.getCount();
            });
        });
    }

}
