package com.xzier.tutorialmod;

import com.mojang.logging.LogUtils;
import com.xzier.tutorialmod.Compat.TopCompatibility;
import com.xzier.tutorialmod.Data.DataGeneration;
import com.xzier.tutorialmod.Init.*;
import com.xzier.tutorialmod.events.ForgeEventHandlers;
import com.xzier.tutorialmod.network.Channel;
import com.xzier.tutorialmod.util.ModConfiguration;
import com.xzier.tutorialmod.worldgen.dimension.ModDimensions;
import mcjty.theoneprobe.TheOneProbe;
import net.minecraft.world.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib.GeckoLib;

import static com.xzier.tutorialmod.Init.BlockInit.DERP_BLOCK;
import static com.xzier.tutorialmod.Init.ItemInit.*;
import static com.xzier.tutorialmod.Init.MenuTypesInit.MENU_TYPES;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MODID)
public class TutorialMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "tutorialmod";
    public static final Logger LOGGER = LogUtils.getLogger();
    public TutorialMod(){

        GeckoLib.initialize();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        EnchantmentInit.ENCHANTMENTS.register(bus);
        TileEntityInit.TILE_ENTITIES.register(bus);
        EntityInit.ENTITY_TYPES.register(bus);
        ModCreativeTabsInit.CREATIVE_MODE_TABS.register(bus);
        POIInit.POI_TYPE_DEFERRED_REGISTER.register(bus);
        PlacedFeaturesInit.register(bus);
        MENU_TYPES.register(bus);
        ModDimensions.register();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfiguration.COMMON_SPEC,"tutorialmod-common.toml");
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandlers());
        bus.addListener(this::commonSetup);
        bus.addListener(DataGeneration::generate);

    }




    private void commonSetup(final FMLCommonSetupEvent event) {
        Channel.register();
        TopCompatibility.register();
        LOGGER.info(String.valueOf(ModConfiguration.Common.abilities_mixin_enabled.get()));
    }





}