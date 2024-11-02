package com.xzier.tutorialmod.worldgen;

import com.xzier.tutorialmod.Init.ConfiguredFeaturesInit;
import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ModFeatureUtils {
    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(TutorialMod.MODID, name));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        ConfiguredFeaturesInit.bootstrap(context);
    }
}
