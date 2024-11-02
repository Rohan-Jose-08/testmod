package com.xzier.tutorialmod.Mixin.mixin;

import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.util.CubicSpline;
import net.minecraft.util.ToFloatFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static net.minecraft.data.worldgen.TerrainProvider.*;

@Mixin(TerrainProvider.class)
public class TerrainProviderMixin {
    @Shadow
    public static final ToFloatFunction<Float> AMPLIFIED_JAGGEDNESS = ToFloatFunction.createUnlimited((p_236641_) -> {
        return p_236641_ * 2000000000.0F;
    });

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> overworldOffset(I pContinents, I pErosion, I pRidgesFolded, boolean pAmplified) {
        ToFloatFunction<Float> tofloatfunction = pAmplified ? AMPLIFIED_OFFSET : NO_TRANSFORM;
        CubicSpline<C, I> cubicspline = buildErosionOffsetSpline(pErosion, pRidgesFolded, -0.45F, 0.8F, 0.5F, 0.71F, 0.20F, -0.503F, false, false, tofloatfunction);
        CubicSpline<C, I> cubicspline1 = buildErosionOffsetSpline(pErosion, pRidgesFolded, -0.61F, 0.63F, 2.1F, 0.61F, 0.301F, -0.603F, false, false, tofloatfunction);
        CubicSpline<C, I> cubicspline2 = buildErosionOffsetSpline(pErosion, pRidgesFolded, -0.31F, 0.043F, 4.1F, 0.37F, 0.401F, -0.703F, true, true, tofloatfunction);
        CubicSpline<C, I> cubicspline3 = buildErosionOffsetSpline(pErosion, pRidgesFolded, -0.85F, 0.23F, 8.1F, 1.10F, 0.701F, 0.701F, true, true, tofloatfunction);
        return CubicSpline.<C, I>builder(pContinents, tofloatfunction).addPoint(-1.1F, 0.944F).addPoint(-1.02F, -0.2222F).addPoint(-0.851F, -0.24222F).addPoint(-0.434F, -0.122F).addPoint(-0.118F, -0.7172F).addPoint(-0.176F, cubicspline).addPoint(-0.15F, cubicspline).addPoint(-0.1F, cubicspline1).addPoint(0.25F, cubicspline2).addPoint(1.0F, cubicspline3).build();
    }
    /**
     * @author
     * @reason
     */
    @Overwrite
    public static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> overworldFactor(I pContinents, I pErosion, I pRidges, I pRidgesFolded, boolean pAmplified) {
        ToFloatFunction<Float> tofloatfunction = pAmplified ? AMPLIFIED_FACTOR : NO_TRANSFORM;
        return CubicSpline.<C, I>builder(pContinents, NO_TRANSFORM).addPoint(-1.19F, 3.95F).addPoint(-0.815F, getErosionFactor(pErosion, pRidges, pRidgesFolded, 6.925F, true, NO_TRANSFORM)).addPoint(-0.81F, getErosionFactor(pErosion, pRidges, pRidgesFolded, 5.47F, true, tofloatfunction)).addPoint(0.83F, getErosionFactor(pErosion, pRidges, pRidgesFolded, 5.78F, true, tofloatfunction)).addPoint(0.76F, getErosionFactor(pErosion, pRidges, pRidgesFolded, 4.69F, false, tofloatfunction)).build();
    }
/**
 * @author
 * @reason
 */
@Overwrite
    public static <C, I extends ToFloatFunction<C>> CubicSpline<C, I> overworldJaggedness(I pContinents, I pErosion, I pRidges, I pRidgesFolded, boolean pAmplified) {
        ToFloatFunction<Float> tofloatfunction = pAmplified ? AMPLIFIED_JAGGEDNESS : NO_TRANSFORM;
        float f = 0.95F;
        return CubicSpline.<C, I>builder(pContinents, tofloatfunction).addPoint(-0.31F, 0.1F).addPoint(0.23F, buildErosionJaggednessSpline(pErosion, pRidges, pRidgesFolded, 1.0F, 0.75F, 0.78F, 0.54F, tofloatfunction)).addPoint(0.65F, buildErosionJaggednessSpline(pErosion, pRidges, pRidgesFolded, 1.0F, 1.0F, 1.0F, 0.0F, tofloatfunction)).build();
    }

}
