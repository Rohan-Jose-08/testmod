package com.xzier.tutorialmod.Init;

import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;

import static com.xzier.tutorialmod.worldgen.ModFeatureUtils.createKey;

public class ConfiguredFeaturesInit {
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_XZIER_GRAVEL = createKey("disk_xzier_gravel");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        register(context, DISK_XZIER_GRAVEL, Feature.DISK ,new DiskConfiguration(RuleBasedBlockStateProvider.simple(BlockInit.XZIER_GRAVEL.get()), BlockPredicate.matchesBlocks(List.of(Blocks.GRAVEL, Blocks.DIRT)), UniformInt.of(2, 6), 2));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }
}
