package com.xzier.tutorialmod.Init;

import com.xzier.tutorialmod.Entities.ExplosiveArrowEntity;
import com.xzier.tutorialmod.Entities.RetardEntity;
import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TutorialMod.MODID);

    public static final RegistryObject<EntityType<ExplosiveArrowEntity>> EXPLOSIVE_ARROW = ENTITY_TYPES.register("explosive_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<ExplosiveArrowEntity>) ExplosiveArrowEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).build("explosive_arrow"));

    public static final RegistryObject<EntityType<RetardEntity>> RETARD_ENTITY = ENTITY_TYPES.register("retard_entity",
            () -> EntityType.Builder.<RetardEntity>of(RetardEntity::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(TutorialMod.MODID, "retard_entity").toString())
    );
}
