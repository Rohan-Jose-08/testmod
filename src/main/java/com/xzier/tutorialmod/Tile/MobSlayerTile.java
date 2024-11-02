package com.xzier.tutorialmod.Tile;

import com.xzier.tutorialmod.Init.TileEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TextComponentTagVisitor;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;

import net.minecraft.world.damagesource.DamageSource;

import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingDamageEvent;


import java.util.List;



public class MobSlayerTile extends BlockEntity {

    int timer = 0;
    boolean isActive = true;
    public MobSlayerTile(BlockPos pos, BlockState state) {
        super(TileEntityInit.MOB_SLAYER.get(), pos, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        MobSlayerTile tile = (MobSlayerTile) be;
        if (!level.isClientSide() && tile.isActive){
            tile.timer++;
            if (tile.timer > 20){
                tile.timer = 0;

                // only do this once per second
                tile.hurtMobs();
            }
        }

    }

    public void toggle(Player player){
        this.isActive = !this.isActive;
    }

    final int RANGE = 5;
    private void hurtMobs() {

        BlockPos topCorner = this.worldPosition.offset(RANGE, RANGE, RANGE);
        BlockPos bottomCorner = this.worldPosition.offset(-RANGE, -RANGE, -RANGE);
        AABB box = new AABB(topCorner, bottomCorner);

        assert this.level != null;
        List<Entity> entities = this.level.getEntities(null, box);
        for (Entity target : entities){
            if (target instanceof LivingEntity && !(target instanceof Player)){
                target.hurt(level.damageSources().magic(), 5);
            }

        }

    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putBoolean("active", this.isActive);
    }


}
