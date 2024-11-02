package com.xzier.tutorialmod.Entities;

import com.xzier.tutorialmod.Init.ItemInit;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ExplosiveArrowEntity extends AbstractArrow {


    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.EXPLOSIVE_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult hit) {
        super.onHitEntity(hit);
        level().explode(this, this.getX(), this.getY(), this.getZ(), 20.0f, true, Level.ExplosionInteraction.TNT);

    }

    @Override
    protected void onHitBlock(BlockHitResult ray) {
        super.onHitBlock(ray);
        BlockState theBlockYouHit = level().getBlockState(ray.getBlockPos());
    }

    @Override
    protected void tickDespawn() {
        if (this.inGroundTime > 60){
            level().explode(this, this.getX(), this.getY(), this.getZ(), 2000.0f, true, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }




}

