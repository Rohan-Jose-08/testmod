package com.xzier.tutorialmod.events;

import com.xzier.tutorialmod.Entities.RetardEntity;
import com.xzier.tutorialmod.Init.EntityInit;
import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event){
        event.put(EntityInit.RETARD_ENTITY.get(), RetardEntity.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event){
        event.register(EntityInit.RETARD_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE,RetardEntity::canSpawn,SpawnPlacementRegisterEvent.Operation.OR);
    }
}
