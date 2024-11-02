package com.xzier.tutorialmod.worldgen.dimension;

import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.registries.ForgeRegistries;

public class ModDimensions {

    public static final ResourceKey<Level> XZIER_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(TutorialMod.MODID, "xzier_dimension"));
    public static final ResourceKey<DimensionType> XZIER_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, XZIER_KEY.registry());

    public static void register() {
        System.out.println("Registering ModDimensions for " + TutorialMod.MODID);
    }

}
