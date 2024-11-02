package com.xzier.tutorialmod.Data;

import com.xzier.tutorialmod.Init.BlockInit;
import com.xzier.tutorialmod.Init.ItemInit;
import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.stream.Collectors;

public class ModLootTables extends VanillaBlockLoot {
    @Override
    protected void generate() {
        dropSelf(BlockInit.SAD_BLOCK.get());
        dropSelf(BlockInit.MOB_SLAYER.get());
        dropSelf(BlockInit.EXAMPLE_ITEM_CAP_BLOCK.get());
        dropSelf(BlockInit.DERP_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(TutorialMod.MODID))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}
