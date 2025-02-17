package com.xzier.tutorialmod.containers;

import com.xzier.tutorialmod.Init.BlockInit;
import com.xzier.tutorialmod.Init.MenuTypesInit;
import com.xzier.tutorialmod.Tile.XzierSmelterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import static com.xzier.tutorialmod.Tile.XzierSmelterBlockEntity.*;

public class XzierSmelterContainer extends AbstractContainerMenu {
    private int power;
    private final BlockPos pos; // block position
    public XzierSmelterContainer(int windowId, Player player, BlockPos pos) {
        super(MenuTypesInit.XZIER_SCREEN_CONTAINER.get(), windowId);
        this.pos = pos;
        if (player.level().getBlockEntity(pos) instanceof XzierSmelterBlockEntity smelter) {
            addSlot(new SlotItemHandler(smelter.getInputItems(), SLOT_INPUT, 64, 24));
            addSlot(new SlotItemHandler(smelter.getInputItems(), SLOT_INPUT, 64, 24));
            addSlot(new SlotItemHandler(smelter.getOutputItems(), XzierSmelterBlockEntity.SLOT_OUTPUT+0, 108, 24));
            addSlot(new SlotItemHandler(smelter.getOutputItems(), XzierSmelterBlockEntity.SLOT_OUTPUT+1, 126, 24));
            addSlot(new SlotItemHandler(smelter.getOutputItems(), XzierSmelterBlockEntity.SLOT_OUTPUT+2, 144, 24));
            addSlot(new SlotItemHandler(smelter.getOutputItems(), XzierSmelterBlockEntity.SLOT_OUTPUT+3, 108, 42));
            addSlot(new SlotItemHandler(smelter.getOutputItems(), XzierSmelterBlockEntity.SLOT_OUTPUT+4, 126, 42));
            addSlot(new SlotItemHandler(smelter.getOutputItems(), XzierSmelterBlockEntity.SLOT_OUTPUT+5, 144, 42));
            addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    return smelter.getStoredPower() & 0xffff;
                }

                @Override
                public void set(int pValue) {
                    XzierSmelterContainer.this.power = (  XzierSmelterContainer.this.power & 0xffff0000) | (pValue & 0xffff);
                }
            });
            addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    return (smelter.getStoredPower() >> 16) & 0xffff;
                }

                @Override
                public void set(int pValue) {
                    XzierSmelterContainer.this.power = (  XzierSmelterContainer.this.power & 0xffff) | ((pValue & 0xffff) << 16);
                }
            });
        }
        layoutPlayerInventorySlots(player.getInventory(), 10, 70);
    }
    public int getPower() {
        return power;
    }
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index < SLOT_COUNT) {
                if (!this.moveItemStackTo(stack, SLOT_COUNT, Inventory.INVENTORY_SIZE + SLOT_COUNT, true)) {
                    return ItemStack.EMPTY;
                }
            }
            if (!this.moveItemStackTo(stack, SLOT_INPUT, SLOT_INPUT+1, false)) {
                if (index < 27 + SLOT_COUNT) {
                    if (!this.moveItemStackTo(stack, 27 + SLOT_COUNT, 36 + SLOT_COUNT, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < Inventory.INVENTORY_SIZE + SLOT_COUNT && !this.moveItemStackTo(stack, SLOT_COUNT, 27 + SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemstack;
    }



    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(player.level(), pos), player, BlockInit.XZIER_SMELTER.get());
    }

    private void layoutPlayerInventorySlots(Container playerInventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);
        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    private int addSlotBox(Container playerInventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(playerInventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private int addSlotRange(Container playerInventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new Slot(playerInventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }
}
