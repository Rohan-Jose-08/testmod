package com.xzier.tutorialmod.Mixin.mixin;

import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.Properties.class)
public class ItemPropertiesMixin {
    @Shadow
    int maxStackSize = 128;
}
