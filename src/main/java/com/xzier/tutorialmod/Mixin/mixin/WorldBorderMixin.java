package com.xzier.tutorialmod.Mixin.mixin;

import net.minecraft.world.level.border.BorderStatus;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WorldBorder.class)
public class WorldBorderMixin {
    private net.minecraft.world.level.border.WorldBorder enclosingInstance;
    @Final
    @Shadow
    public static final double MAX_SIZE = (double)1.7976931348623157E308;
    @Shadow
    int absoluteMaxSize = 2147483647;

    @Shadow
    private WorldBorder.BorderExtent extent;
    public WorldBorderMixin(){
        enclosingInstance = new net.minecraft.world.level.border.WorldBorder();
        WorldBorder.BorderExtent tst  = enclosingInstance.new StaticBorderExtent((double)1.7976931348623157E308);
        extent = tst;
    }

}
