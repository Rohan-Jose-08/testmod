package com.xzier.tutorialmod.client.render;

import com.xzier.tutorialmod.Entities.RetardEntity;
import com.xzier.tutorialmod.Init.EntityInit;
import com.xzier.tutorialmod.Init.MenuTypesInit;
import com.xzier.tutorialmod.Screen.GeneratorScreen;
import com.xzier.tutorialmod.Screen.ProcessorScreen;
import com.xzier.tutorialmod.Screen.XzierSmelterScreen;
import com.xzier.tutorialmod.TutorialMod;
import com.xzier.tutorialmod.client.model.RetardEntityModel;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TutorialMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityInit.EXPLOSIVE_ARROW.get(), ExplosiveArrowRenderer::new);
        EntityRenderers.register(EntityInit.RETARD_ENTITY.get(), RetardEntityRenderer::new);
        event.enqueueWork(() -> {
            MenuScreens.register(MenuTypesInit.PROCESSOR_CONTAINER.get(), ProcessorScreen::new);
            MenuScreens.register(MenuTypesInit.GENERATOR_CONTAINER.get(), GeneratorScreen::new);
            MenuScreens.register(MenuTypesInit.XZIER_SCREEN_CONTAINER.get(), XzierSmelterScreen::new);
        }
    );
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(RetardEntityModel.LAYER_LOCATION, RetardEntityModel::createBodyLayer);
    }
}
