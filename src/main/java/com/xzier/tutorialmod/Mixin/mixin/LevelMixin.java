package com.xzier.tutorialmod.Mixin.mixin;

import com.xzier.tutorialmod.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Level.class)
public abstract class LevelMixin extends net.minecraftforge.common.capabilities.CapabilityProvider<Level> implements LevelAccessor, AutoCloseable, net.minecraftforge.common.extensions.IForgeLevel {
    @Final
    @Shadow
    public static final int MAX_LEVEL_SIZE = 2147483647;

    protected LevelMixin(Class<Level> baseClass) {
        super(baseClass);
    }

    /**
     * @author Xzier9
     * @reason Change bounds of world
     */
    @Overwrite
    private static boolean isInWorldBoundsHorizontal(BlockPos pPos) {
        return pPos.getX() >= -MAX_LEVEL_SIZE && pPos.getZ() >= -MAX_LEVEL_SIZE && pPos.getX() < MAX_LEVEL_SIZE && pPos.getZ() < MAX_LEVEL_SIZE;
    }

    /**
     * @author Xzier9
     * @reason Change bounds of world
     */
    @Overwrite
    private static boolean isOutsideSpawnableHeight(int pY) {
        return pY < -MAX_LEVEL_SIZE || pY >= MAX_LEVEL_SIZE;
    }

}
