package com.xzier.tutorialmod.Data;

import com.xzier.tutorialmod.Init.BlockInit;
import com.xzier.tutorialmod.TutorialMod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStates extends BlockStateProvider {
    public ModBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockInit.CHARGER_BLOCK.get());
        simpleBlock(BlockInit.GENERATOR_BLOCK.get());
    }
}
