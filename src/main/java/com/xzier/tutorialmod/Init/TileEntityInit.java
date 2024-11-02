package com.xzier.tutorialmod.Init;

import com.xzier.tutorialmod.Tile.*;
import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.xzier.tutorialmod.Init.BlockInit.*;
import static net.minecraftforge.registries.ForgeRegistries.BLOCK_ENTITY_TYPES;

public class TileEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(BLOCK_ENTITY_TYPES, TutorialMod.MODID);

    public static final RegistryObject<BlockEntityType<MobSlayerTile>> MOB_SLAYER = TILE_ENTITIES.register("mob_slayer",
            () -> BlockEntityType.Builder.of(MobSlayerTile::new, BlockInit.MOB_SLAYER.get()).build(null));


public static final RegistryObject<BlockEntityType<ExampleItemCapBlockEntity>> EXAMPLE_ITEM_CAP_BLOCK_ENTITY = TILE_ENTITIES
            .register("example_item_cap_block",
                    () -> BlockEntityType.Builder.of(ExampleItemCapBlockEntity::new, BlockInit.EXAMPLE_ITEM_CAP_BLOCK.get())
                            .build(null));
    public static final RegistryObject<BlockEntityType<ProcessorBlockEntity>> PROCESSOR_BLOCK_ENTITY = TILE_ENTITIES.register("processor_block",
            () -> BlockEntityType.Builder.of(ProcessorBlockEntity::new, PROCESSOR_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<GeneratorBlockEntity>> GENERATOR_BLOCK_ENTITY = TILE_ENTITIES.register("generator_block",
            () -> BlockEntityType.Builder.of(GeneratorBlockEntity::new, GENERATOR_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<ChargerBlockEntity>> CHARGER_BLOCK_ENTITY = TILE_ENTITIES.register("charger_block",
            () -> BlockEntityType.Builder.of(ChargerBlockEntity::new, CHARGER_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<XzierSmelterBlockEntity>> XZIER_SMELTER_BLOCK_ENTITY = TILE_ENTITIES.register("xzier_smelter",
            () -> BlockEntityType.Builder.of(XzierSmelterBlockEntity::new, XZIER_SMELTER.get()).build(null));
}

