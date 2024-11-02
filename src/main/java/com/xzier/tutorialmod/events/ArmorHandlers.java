package com.xzier.tutorialmod.events;

import com.xzier.tutorialmod.util.IDamageHandlingArmor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ArmorHandlers {
    @SubscribeEvent
    public static void armorAttackHandler(LivingDamageEvent event){
        for (ItemStack armor : event.getEntity().getArmorSlots()){
            if (armor.getItem() instanceof IDamageHandlingArmor){
                float newDamage = ((IDamageHandlingArmor)armor.getItem()).onDamaged(event.getEntity(), armor.getEquipmentSlot(), event.getSource(), event.getAmount(),event.getEntity().damageSources());
                event.setAmount(newDamage);
            }
        }
    }
}
