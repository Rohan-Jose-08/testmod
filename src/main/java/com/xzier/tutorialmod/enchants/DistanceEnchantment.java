package com.xzier.tutorialmod.enchants;

import com.xzier.tutorialmod.Init.ItemInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class DistanceEnchantment extends Enchantment {
    static EnchantmentCategory TELEPORT_STAFF_TYPE = EnchantmentCategory.create("ripoff_enderio ", item -> item == ItemInit.RIPOFF_ENDERIO.get());

    public DistanceEnchantment() {
        super(Rarity.COMMON, TELEPORT_STAFF_TYPE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }
}