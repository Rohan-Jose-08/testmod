package com.xzier.tutorialmod.Init;

import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class PlacedFeaturesInit {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registries.PLACED_FEATURE, TutorialMod.MODID);

    public static final ResourceKey<PlacedFeature> DISK_XZIER_GRAVEL = ResourceKey.create(Registries.PLACED_FEATURE,new ResourceLocation(TutorialMod.MODID,"disk_xzier_gravel"));

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
