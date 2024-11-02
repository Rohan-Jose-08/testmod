package com.xzier.tutorialmod.Mixin.mixin;

import com.xzier.tutorialmod.util.ModConfiguration;
import net.minecraft.world.entity.player.Abilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Abilities.class)
public class AbilitiesMixin {

        @Shadow
        private float flyingSpeed = ModConfiguration.Common.abilities_mixin_enabled.get()?1.0F:0.05F;
        @Shadow
        private float walkingSpeed = ModConfiguration.Common.abilities_mixin_enabled.get()? 1.1F:0.1F;

}
