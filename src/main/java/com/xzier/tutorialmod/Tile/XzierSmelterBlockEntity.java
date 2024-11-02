package com.xzier.tutorialmod.Tile;

import com.xzier.tutorialmod.Init.TileEntityInit;
import com.xzier.tutorialmod.TutorialMod;
import com.xzier.tutorialmod.containers.XzierSmelterContainer;
import com.xzier.tutorialmod.util.AdaptedEnergyStorage;
import com.xzier.tutorialmod.util.AdaptedItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.NoSuchElementException;

public class XzierSmelterBlockEntity extends BlockEntity {

    public static final String ITEMS_TAG = "Inventory";
    public static final String ENERGY_TAG = "Energy";
    public static final int SLOT_INPUT = 0;
    public static boolean isLit = false;
    public static final int SLOT_INPUT_COUNT = 1;
    public static final int SLOT_OUTPUT = 0;
    public static final int SLOT_OUTPUT_COUNT = 6;
    public static final int CONSUME = 100;
    public static final int MAXTRANSFER = 1000;
    public static final int CAPACITY = 100000;
    public static final int SLOT_COUNT = SLOT_INPUT_COUNT + SLOT_OUTPUT_COUNT;
    private final ItemStackHandler inputItems = createItemHandler(SLOT_INPUT_COUNT);
    private final ItemStackHandler outputItems = createItemHandler(SLOT_OUTPUT_COUNT);


    private final LazyOptional<IItemHandler> itemHandler = LazyOptional.of(() -> new CombinedInvWrapper(inputItems, outputItems));
    private final LazyOptional<IItemHandler> inputItemHandler = LazyOptional.of(() -> new AdaptedItemHandler(inputItems) {
        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            return ItemStack.EMPTY;
        }
    });
    private final LazyOptional<IItemHandler> outputItemHandler = LazyOptional.of(() -> new AdaptedItemHandler(outputItems) {
        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            return stack;
        }
    });

    private final EnergyStorage energy = createEnergyStorage();
    private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> new AdaptedEnergyStorage(energy) {
        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            return 0;
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            setChanged();
            return super.receiveEnergy(maxReceive, simulate);
        }

        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    });



    private void processEnergy() {
        energy.extractEnergy(CONSUME,false);
        setChanged();
        }
    public XzierSmelterBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(TileEntityInit.XZIER_SMELTER_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public void tickServer() {
        boolean powered = energy.getEnergyStored() > 0;
        if (level.getGameTime() % 10 != 0) {
            return;
        }
        ItemStack stack = inputItems.extractItem(SLOT_INPUT, 1, false);
        if (!stack.isEmpty() && !meltItem(stack).isEmpty()  &&  energy.getEnergyStored() >= CONSUME) {
            insertOrEject(meltItem(stack));
        }
       if (powered != getBlockState().getValue(BlockStateProperties.POWERED)) {
            level.setBlockAndUpdate(worldPosition, getBlockState().setValue(BlockStateProperties.POWERED, powered));
        }

    }

    public ItemStackHandler getInputItems() {
        return inputItems;
    }
    public ItemStackHandler getOutputItems() {
        return outputItems;
    }

    @Nonnull
    private ItemStackHandler createItemHandler(int slots) {
        return new ItemStackHandler(slots) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }
    public int getStoredPower() {
        return energy.getEnergyStored();
    }

    private void insertOrEject(ItemStack stack) {
        ItemStack itemStack = ItemHandlerHelper.insertItem(outputItems, stack, false);
        if (!itemStack.isEmpty()) {
            ItemEntity entityitem = new ItemEntity(level, worldPosition.getX()+.5, worldPosition.getY() + 1, worldPosition.getZ()+.5, itemStack);
            entityitem.setPickUpDelay(100);
            entityitem.setDeltaMovement(entityitem.getDeltaMovement().multiply(0, 1, 0));
            level.addFreshEntity(entityitem);
        }
    }

    private ItemStack meltItem(ItemStack stack) {
        SimpleContainer container = new SimpleContainer(stack);
            processEnergy();
             ItemStack stacks = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, container, this.level)
                    .map(recipe -> recipe.assemble(container, level.registryAccess())).orElse(stack);
             return stacks;
    }


    @Nonnull
    private EnergyStorage createEnergyStorage() {
        return new EnergyStorage(CAPACITY, MAXTRANSFER, MAXTRANSFER);
    }
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put(ENERGY_TAG, energy.serializeNBT());
    }
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains(ENERGY_TAG)) {
            energy.deserializeNBT(tag.get(ENERGY_TAG));
        }
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return itemHandler.cast();
        } else if (cap == ForgeCapabilities.ENERGY) {
            return energyHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }



}
