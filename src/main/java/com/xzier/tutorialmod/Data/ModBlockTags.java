package com.xzier.tutorialmod.Data;

import com.xzier.tutorialmod.Init.BlockInit;
import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTags extends BlockTagsProvider {
    public ModBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TutorialMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockInit.EXAMPLE_ITEM_CAP_BLOCK.get())
                .add(BlockInit.MOB_SLAYER.get())
                .add(BlockInit.DERP_BLOCK.get());


        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(BlockInit.SAD_BLOCK.get());


        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(BlockInit.SAD_BLOCK.get())
                .add(BlockInit.MOB_SLAYER.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BlockInit.EXAMPLE_ITEM_CAP_BLOCK.get())
                .add(BlockInit.DERP_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BlockInit.XZIER_ORE.get());

    }

}
