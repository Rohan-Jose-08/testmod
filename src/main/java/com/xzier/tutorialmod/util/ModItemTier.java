package com.xzier.tutorialmod.util;

import com.xzier.tutorialmod.Init.ItemInit;
import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

import static com.xzier.tutorialmod.Init.ItemInit.RARTED_ITEM;
import static com.xzier.tutorialmod.Init.ItemInit.XZIER_GEM;

public enum ModItemTier implements Tier {
        XZIER(5, 20, 20, 50, 20, () -> {
            return Ingredient.of(XZIER_GEM.get());
        }),

    FLAMING(5, 25, 30, 50, 20, () -> {
        return Ingredient.of(RARTED_ITEM.get());
    }),

    FREEZING(5, 25, 30, 50, 20, () -> {
        return Ingredient.of(RARTED_ITEM.get());
    });


    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ModItemTier(int level, int durability, float miningSpeed, float damage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = durability;
        this.speed = miningSpeed;
        this.damage = damage;
        this.enchantmentValue = enchantability;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }


    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
