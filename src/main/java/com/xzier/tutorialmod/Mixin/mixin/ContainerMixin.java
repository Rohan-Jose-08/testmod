package com.xzier.tutorialmod.Mixin.mixin;

import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Container.class)
public interface ContainerMixin {
    @Shadow
    int LARGE_MAX_STACK_SIZE = 128;
    /**
     * @author
     * @reason
     */
    @Overwrite
    default int getMaxStackSize() {
        return 128;
    }


}
