package com.xzier.tutorialmod.Items;

import com.xzier.tutorialmod.Init.ItemInit;
import com.xzier.tutorialmod.util.IDamageHandlingArmor;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;

import static com.xzier.tutorialmod.Init.ItemInit.*;

public class FlamingArmorItem extends ArmorItem implements IDamageHandlingArmor {
    public FlamingArmorItem(ArmorMaterial material, ArmorItem.Type slot, Properties properties) {
        super(material, slot, properties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        boolean fullset = player.getItemBySlot(EquipmentSlot.HEAD).getItem() == FLAMING_HELMET.get() &&
                          player.getItemBySlot(EquipmentSlot.CHEST).getItem() == FLAMING_CHESTPLATE.get() &&
                          player.getItemBySlot(EquipmentSlot.LEGS).getItem() == FLAMING_LEGGINGS.get() &&
                          player.getItemBySlot(EquipmentSlot.FEET).getItem() == FLAMING_BOOTS.get();

        if (!world.isClientSide() && fullset){
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200));
        }
    }

    @Override
    public float onDamaged(LivingEntity entity, EquipmentSlot slot, DamageSource source , float amount,DamageSources sources) {
        Entity attacker = source.getEntity();
        if (attacker instanceof LivingEntity){
            attacker.hurt(sources.onFire(), amount / 2);
            attacker.setSecondsOnFire(4);
            return amount / 2;
        } else {
            return amount;
        }
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return true;
    }


}
