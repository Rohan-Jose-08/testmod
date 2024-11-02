package com.xzier.tutorialmod.Blocks;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.xzier.tutorialmod.Init.BlockInit;
import com.xzier.tutorialmod.Init.TileEntityInit;
import com.xzier.tutorialmod.Tile.MobSlayerTile;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static com.mojang.blaze3d.vertex.VertexMultiConsumer.create;
import static com.xzier.tutorialmod.Init.TileEntityInit.*;

public class MobSlayerBlock extends Block implements EntityBlock {
    public MobSlayerBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return TileEntityInit.MOB_SLAYER.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return type == TileEntityInit.MOB_SLAYER.get() ? MobSlayerTile::tick : null;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide() && hand == InteractionHand.MAIN_HAND){
            BlockEntity tile = world.getBlockEntity(pos);
            if (tile instanceof MobSlayerTile){
                ((MobSlayerTile) tile).toggle(player);

                world.playSound(null,pos, SoundEvents.ANVIL_LAND,SoundSource.BLOCKS,1.0F,1.0F);

                return InteractionResult.SUCCESS;

            }
        }
        return super.use(state, world, pos, player, hand, hit);
    }




}
