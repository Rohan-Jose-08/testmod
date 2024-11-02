package com.xzier.tutorialmod.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.TntBlock.explode;

public class XzierTnt extends FallingBlock {

    public XzierTnt(Properties properties) {
        super(properties);
    }

    @Override
    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity igniter) {
        level.explode(igniter, pos.getX(),pos.getY(), pos.getZ(), 20.0F, true, Level.ExplosionInteraction.TNT);
    }

    @Override
    public void wasExploded(Level pLevel, BlockPos pPos, Explosion pExplosion) {
        explode(pLevel, pPos, pExplosion.getIndirectSourceEntity());
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!level.isClientSide()) {
            if (player.isCreative()) {
                destroy(level, pos, state);
            } else {
                explode(level, pos, player);
            }
        }
        return false;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 300;
    }

    @Override

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack held = pPlayer.getItemInHand(pHand);
        if (!pLevel.isClientSide() && held.getItem() == Items.FLINT_AND_STEEL) {
            explode(pLevel, pPos, pPlayer);
            return InteractionResult.SUCCESS;
        } else if (!pLevel.isClientSide() && held.getItem() == Items.DIAMOND) {
            pLevel.setBlock(pPos.above(), Blocks.DIAMOND_BLOCK.defaultBlockState(),2);
            pLevel.setBlock(pPos.below(), Blocks.DIAMOND_BLOCK.defaultBlockState(),2);
            pLevel.setBlock(pPos.east(), Blocks.DIAMOND_BLOCK.defaultBlockState(),2);
            pLevel.setBlock(pPos.west(), Blocks.DIAMOND_BLOCK.defaultBlockState(),2);
            pLevel.setBlock(pPos.south(), Blocks.DIAMOND_BLOCK.defaultBlockState(),2);
            pLevel.setBlock(pPos.north(), Blocks.DIAMOND_BLOCK.defaultBlockState(),2);
            return InteractionResult.SUCCESS;
        } else if (!pLevel.isClientSide() && held.getItem() == Items.FLINT) {
          EntityType.CREEPER.spawn((ServerLevel)pLevel,held, pPlayer, pPos.above(), MobSpawnType.MOB_SUMMONED, false, false);
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Deprecated //Forge: Prefer using IForgeBlock#catchFire
    private static void explode(Level pLevel, BlockPos pPos, @javax.annotation.Nullable LivingEntity pEntity) {
        if (!pLevel.isClientSide) {
            PrimedTnt primedtnt = new PrimedTnt(pLevel, (double)pPos.getX() + 0.5D, (double)pPos.getY(), (double)pPos.getZ() + 0.5D, pEntity);
            for(int i = 0; i<10;i++)  pLevel.addFreshEntity(primedtnt);
            pLevel.playSound((Player)null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.gameEvent(pEntity, GameEvent.PRIME_FUSE, pPos);
            pLevel.explode(pEntity, pPos.getX(), pPos.getY(), pPos.getZ(), 20.0F, true, Level.ExplosionInteraction.TNT);
        }
    }

}

