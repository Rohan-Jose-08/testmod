package com.xzier.tutorialmod.Init;

import com.xzier.tutorialmod.Items.*;
import com.xzier.tutorialmod.TutorialMod;
import com.xzier.tutorialmod.util.ModArmorMaterial;
import com.xzier.tutorialmod.util.ModItemTier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MODID);
    public static final RegistryObject<Item> XZIER_GEM = ITEMS.register("xzier_gem",
            () -> new Item(new Item.Properties().stacksTo(128)));
    public static final RegistryObject<Item> RARTED_ITEM = ITEMS.register("rarted_item",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(2).build())));

    public static final RegistryObject<Item> G_FUEL = ITEMS.register("g_fuel",
            () -> new FuelItem(new Item.Properties(),3200));

    public static final RegistryObject<Item> RIPOFF_ENDERIO = ITEMS.register("ripoff_enderio",
            () -> new Ripoff_EnderIO(new Item.Properties().durability(50)));

    public static final RegistryObject<Item> XZIER_AND_STEEL = ITEMS.register("xzier_and_steel", CatalystItem::new);

    public static final RegistryObject<Item> RETARD_ENTITY_SPAWN_EGG = ITEMS.register("retard_entity_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.RETARD_ENTITY,0xF0ABD1,0xAE4C82,new Item.Properties()));

    public static final RegistryObject<Item> FROST_GEM = ITEMS.register("frost_gem",
            () -> new Item(new Item.Properties()));


//Tools

    public static final RegistryObject<Item> XZIER_SWORD = ITEMS.register("xzier_sword",
            () -> new SwordItem(ModItemTier.XZIER, 20, -3.4F, new Item.Properties()));

    public static final RegistryObject<Item> XZIER_PICKAXE = ITEMS.register("xzier_pickaxe",
            () -> new PickaxeItem(ModItemTier.XZIER,1, -1.0F, new Item.Properties()));

    public static final RegistryObject<Item> XZIER_AXE = ITEMS.register("xzier_axe",
            () -> new AxeItem(ModItemTier.XZIER, 25, -3.4F, new Item.Properties()));

    public static final RegistryObject<Item> XZIER_SHOVEL = ITEMS.register("xzier_shovel",
            () -> new ShovelItem(ModItemTier.XZIER, 1, -1.0F, new Item.Properties()));

    public static final RegistryObject<Item> XZIER_HOE = ITEMS.register("xzier_hoe",
            () -> new HoeItem(ModItemTier.XZIER, 0, -1.0F, new Item.Properties()));

//Armour

    public static final RegistryObject<Item> XZIER_HELMET = ITEMS.register("xzier_helmet",
            () -> new ArmorItem(ModArmorMaterial.XZIER, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> XZIER_CHESTPLATE = ITEMS.register("xzier_chestplate",
            () -> new ArmorItem(ModArmorMaterial.XZIER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> XZIER_LEGGINGS = ITEMS.register("xzier_leggings",
            () -> new ArmorItem(ModArmorMaterial.XZIER, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> XZIER_BOOTS = ITEMS.register("xzier_boots",
            () -> new ArmorItem(ModArmorMaterial.XZIER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> FLAMING_CHESTPLATE = ITEMS.register("flaming_chestplate",
            () -> new FlamingArmorItem(ModArmorMaterial.FLAMING, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> FLAMING_HELMET = ITEMS.register("flaming_helmet",
            () -> new FlamingArmorItem(ModArmorMaterial.FLAMING, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> FLAMING_LEGGINGS = ITEMS.register("flaming_leggings",
            () -> new FlamingArmorItem(ModArmorMaterial.FLAMING, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> FLAMING_BOOTS = ITEMS.register("flaming_boots",
            () -> new FlamingArmorItem(ModArmorMaterial.FLAMING, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> EXPLOSIVE_ARROW = ITEMS.register("explosive_arrow",
            () -> new ExplosiveArrowItem(new Item.Properties()));


}

