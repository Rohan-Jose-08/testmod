package com.xzier.tutorialmod.Init;

import com.xzier.tutorialmod.TutorialMod;
import com.xzier.tutorialmod.containers.GeneratorContainer;
import com.xzier.tutorialmod.containers.ProcessorContainer;
import com.xzier.tutorialmod.containers.XzierSmelterContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypesInit {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TutorialMod.MODID);

    public static final RegistryObject<MenuType<ProcessorContainer>> PROCESSOR_CONTAINER = MENU_TYPES.register("processor_block",
            () -> IForgeMenuType.create((windowId, inv, data) -> new ProcessorContainer(windowId, inv.player, data.readBlockPos())));

    public static final RegistryObject<MenuType<GeneratorContainer>> GENERATOR_CONTAINER = MENU_TYPES.register("generator_block",
            () -> IForgeMenuType.create((windowId, inv, data) -> new GeneratorContainer(windowId, inv.player, data.readBlockPos())));

    public static final RegistryObject<MenuType<XzierSmelterContainer>> XZIER_SCREEN_CONTAINER = MENU_TYPES.register("xzier_smelter",
            () -> IForgeMenuType.create((windowId, inv, data) -> new XzierSmelterContainer(windowId, inv.player, data.readBlockPos())));
}
