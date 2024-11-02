package com.xzier.tutorialmod.Init;

import com.xzier.tutorialmod.Blocks.*;
import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.minecraftforge.registries.ForgeRegistries.*;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MODID);
    public static final RegistryObject<Block> DERP_BLOCK = register("derp_block",() ->
            new Block(BlockBehaviour.Properties
                    .of()
                    .strength(2.5f,1.5f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)),new Item.Properties()

                        );


    public static final RegistryObject<Block> XZIER_ORE = register("xzier_ore",() ->
            new Block(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f,0.5f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)),new Item.Properties()

    );

    public static final RegistryObject<Block> XZIER_GRAVEL = register("xzier_gravel",() ->
            new GravelBlock(BlockBehaviour.Properties
                    .copy(Blocks.GRAVEL)
    ),new Item.Properties());
    public static final RegistryObject<Block> DEEPSLATE_XZIER_ORE = register("deepslate_xzier_ore",() ->
            new Block(BlockBehaviour.Properties
                    .of()
                    .strength(1.5f,0.5f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)),new Item.Properties()

    );

    public static final RegistryObject<Block> PROCESSOR_BLOCK = register("processor_block",() ->
            new ProcessorBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.5f)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
                    .friction(0.5f)),new Item.Properties()

    );
    public static final RegistryObject<Block> GENERATOR_BLOCK = register("generator_block",() ->
            new GeneratorBlock(BlockBehaviour.Properties
                    .of()
                    .strength(3.5f)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
                    .friction(0.5f)),new Item.Properties()

    );




    public static final RegistryObject<Block> SAD_BLOCK = register("sad_block",() ->
            new SadBlock(BlockBehaviour.Properties
                    .of()
                    .strength(2.5f,1.5f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)),new Item.Properties()

    );

    public static final RegistryObject<Block> CHARGER_BLOCK = register("charger_block",() ->
            new ChargerBlock(BlockBehaviour.Properties
                    .of()
                    .strength(2.5f,1.5f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)),new Item.Properties()

    );

    public static final RegistryObject<ExampleItemCapBlock> EXAMPLE_ITEM_CAP_BLOCK = register("example_item_cap_block",
            () -> new ExampleItemCapBlock(BlockBehaviour.Properties
                    .copy(Blocks.BONE_BLOCK)),new Item.Properties()
            );



    public static final RegistryObject<Block> MOB_SLAYER = register("mob_slayer",
            () -> new MobSlayerBlock(BlockBehaviour.Properties
                    .of()
                    .strength(2.5f,1.5f)
                    .requiresCorrectToolForDrops()
                    .friction(0.5f)),new Item.Properties()

                );

    public static final RegistryObject<Block>XZIER_TNT = register("xzier_tnt",
            () -> new XzierTnt(BlockBehaviour.Properties
                    .of()
                    .strength(2.5f,1.5f)
                    .requiresCorrectToolForDrops()
                    .ignitedByLava()
                    .friction(0.5f)),new Item.Properties()

    );

    public static final RegistryObject<Block>XZIER_SMELTER = register("xzier_smelter",
            () -> new XzierSmelter(BlockBehaviour.Properties
                    .of()
                    .strength(2.5f,1.5f)
                    .requiresCorrectToolForDrops()
                    .ignitedByLava()
                    .friction(0.5f)),new Item.Properties()

    );




    public static final RegistryObject<Block> XZIER_PORTAL = BLOCKS.register("xzier_portal",
            XzierPortalBlock::new);



    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> supplier, Item.Properties properties){
        RegistryObject<T> block = BLOCKS.register(name,supplier);
        ItemInit.ITEMS.register(name,() -> new BlockItem(block.get(),properties));
        return block;
    }



}
