package com.xzier.tutorialmod.Mixin.mixin;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(WorldgenRandom.class)
public class WorldgenRandomMixin extends LegacyRandomSource {

    @Shadow private int count;

    @Shadow @Final private RandomSource randomSource;

    public WorldgenRandomMixin(long pSeed) {
        super(pSeed);
    }

    /**
 * @author
 * @reason
 */
@Overwrite
    public void setLargeFeatureSeed(long pBaseSeed, int pChunkX, int pChunkZ) {
        this.setSeed(pBaseSeed);
        long i = this.nextLong();
        long j = this.nextLong();
        long k = (long)pChunkX * i ^ (long)pChunkZ * j ^ (long)Math.sqrt(pBaseSeed * 64L);
        this.setSeed(k);
    }
/**
 * @author
 * @reason
 */
@Overwrite
    public int next(int pBits) {
        ++this.count;
        RandomSource randomsource = this.randomSource;
        if (randomsource instanceof LegacyRandomSource legacyrandomsource) {
            return legacyrandomsource.next(pBits);
        } else {
            return (int)(this.randomSource.nextLong() >>> 128 - (pBits));
        }
    }
}
