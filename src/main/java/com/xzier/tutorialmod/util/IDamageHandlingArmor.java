package com.xzier.tutorialmod.util;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public interface IDamageHandlingArmor {
    default float onDamaged(LivingEntity entity, EquipmentSlot slot, DamageSource source, float amount, DamageSources sources) {
        return amount;
    }
}
