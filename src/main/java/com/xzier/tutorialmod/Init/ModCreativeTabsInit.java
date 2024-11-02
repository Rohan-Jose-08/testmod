package com.xzier.tutorialmod.Init;

import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.xzier.tutorialmod.Init.BlockInit.*;
import static com.xzier.tutorialmod.Init.ItemInit.*;

public class ModCreativeTabsInit {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS= DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MODID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("tutorial_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> XZIER_GEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(XZIER_GEM.get());
                output.accept(RARTED_ITEM.get());
                output.accept(G_FUEL.get());
                output.accept(RIPOFF_ENDERIO.get());
                output.accept(XZIER_AND_STEEL.get());
                output.accept(XZIER_HELMET.get());
                output.accept(XZIER_CHESTPLATE.get());
                output.accept(XZIER_LEGGINGS.get());
                output.accept(XZIER_BOOTS.get());
                output.accept(XZIER_SWORD.get());
                output.accept(XZIER_AXE.get());
                output.accept(XZIER_SHOVEL.get());
                output.accept(XZIER_HOE.get());
                output.accept(XZIER_PICKAXE.get());
                output.accept(FLAMING_HELMET.get());
                output.accept(FLAMING_CHESTPLATE.get());
                output.accept(FLAMING_LEGGINGS.get());
                output.accept(FLAMING_BOOTS.get());
                output.accept(DERP_BLOCK.get());
                output.accept(SAD_BLOCK.get());
                output.accept(MOB_SLAYER.get());
                output.accept(XZIER_ORE.get());
                output.accept(DEEPSLATE_XZIER_ORE.get());
                output.accept(RETARD_ENTITY_SPAWN_EGG.get());
                output.accept(PROCESSOR_BLOCK.get());
                output.accept(GENERATOR_BLOCK.get());
                output.accept(CHARGER_BLOCK.get());
                output.accept(XZIER_SMELTER.get());
                output.accept(RETARD_ENTITY_SPAWN_EGG.get());
                output.accept(EXPLOSIVE_ARROW.get());
                output.accept(XZIER_GRAVEL.get());
                output.accept(XZIER_TNT.get());
                output.accept(EXAMPLE_ITEM_CAP_BLOCK.get());

             // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());


}
