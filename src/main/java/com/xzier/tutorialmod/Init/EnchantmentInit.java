package com.xzier.tutorialmod.Init;

import com.xzier.tutorialmod.TutorialMod;
import com.xzier.tutorialmod.enchants.BridgeEnchantment;
import com.xzier.tutorialmod.enchants.DistanceEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, TutorialMod.MODID);

    public static final RegistryObject<Enchantment> BRIDGE = ENCHANTMENTS.register("bridge", BridgeEnchantment::new);
    public static final RegistryObject<Enchantment> DISTANCE = ENCHANTMENTS.register("distance", DistanceEnchantment::new);




}
