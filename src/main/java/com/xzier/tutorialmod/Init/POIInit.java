package com.xzier.tutorialmod.Init;


import com.google.common.collect.ImmutableSet;
import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class POIInit {
    public static final DeferredRegister<PoiType> POI_TYPE_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.POI_TYPES, TutorialMod.MODID);

    public static final RegistryObject<PoiType> XZIER_PORTAL = POI_TYPE_DEFERRED_REGISTER.register("xzier_portal", () -> new PoiType(ImmutableSet.copyOf(BlockInit.XZIER_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));
}
