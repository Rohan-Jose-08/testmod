package com.xzier.tutorialmod.network;

import mcjty.theoneprobe.network.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    public PacketHandler() {
    }

    public static int nextID() {
        return ID++;
    }


}
