package com.xzier.tutorialmod.events;

import com.xzier.tutorialmod.Blocks.ProcessorBlock;
import com.xzier.tutorialmod.Init.BlockInit;
import com.xzier.tutorialmod.network.Channel;
import com.xzier.tutorialmod.network.PacketHitToServer;
import com.xzier.tutorialmod.util.SafeClientTools;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeEventHandlers {
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.LeftClickBlock event) {
        BlockPos pos = event.getPos();
        Level level = event.getLevel();
        BlockState state = level.getBlockState(pos);
        if (state.is(BlockInit.PROCESSOR_BLOCK.get())) {
            Direction facing = state.getValue(BlockStateProperties.FACING);
            if (facing == event.getFace()) {
                event.setCanceled(true);
                if (level.isClientSide) {
                    HitResult hit = SafeClientTools.getClientMouseOver();
                    if (hit.getType() == HitResult.Type.BLOCK) {
                        // Subtract the position of our block from the location that we hit to get the relative location in 3D
                        Vec3 relative = hit.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());
                        int quadrant = ProcessorBlock.getQuadrant(facing, relative);
                        Channel.sendToServer(new PacketHitToServer(pos, quadrant));
                    }
                }
            }
        }
    }
}
