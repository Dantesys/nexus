package org.dantesys.nexus.gui.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.blocks.NexusBlocks;
import org.dantesys.nexus.blocks.entity.ExtratorBlockEntity;
import org.dantesys.nexus.blocks.entity.InfusorBlockEntity;
import org.dantesys.nexus.gui.NexusMenus;
import org.dantesys.nexus.items.NexusItems;
import org.dantesys.nexus.util.NexusTags;

import java.util.Objects;

public class ExtratorMenu extends AbstractContainerMenu {
    private final ExtratorBlockEntity blockEntity;
    private final ContainerLevelAccess access;

    // ctor used server-side
    public ExtratorMenu(int id, Inventory playerInventory, ExtratorBlockEntity be) {
        super(NexusMenus.EXTRATOR_MENU.get(), id); // ajuste se o seu registry usa outro caminho
        this.blockEntity = be;
        // DataSlot para progresso
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity.getProgress();
            }
            @Override
            public void set(int value) {
                blockEntity.setProgress(value);
            }
        });

        this.access = ContainerLevelAccess.create(be.getLevel(), be.getBlockPos());

        // BE slots (0..4)
        // inputs 0..3 positioned on GUI (x,y chosen arbitrarily; ajuste conforme seu background)
        addSlot(new SlotItemHandler(be.inventory, 0, 44, 32));
        // output slot 4 (readonly typical) — override if you want special behavior
        addSlot(new SlotItemHandler(be.inventory, 1, 117, 32) {
            @Override
            public boolean mayPlace(ItemStack stack) { return false; }
        });

        // Player inventory (9x3)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        // Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }

        // DataSlot for progress sync (16-bit split handled internally)
        this.addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity.getProgress();
            }

            @Override
            public void set(int value) {
                blockEntity.setProgress(value);
            }
        });
    }
    // factory used by client to construct menu from packet
    public static ExtratorMenu fromNetwork(int id, Inventory inv, FriendlyByteBuf buf) {
        ExtratorBlockEntity be = (ExtratorBlockEntity) inv.player.level().getBlockEntity(buf.readBlockPos());
        return new ExtratorMenu(id, inv, Objects.requireNonNull(be));
    }
    public ExtratorBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, NexusBlocks.EXTRATOR.get());
    }
    public boolean isCrafting(){
        return this.blockEntity.getProgress()>0;
    }
    public int getLoading(){
        int progress = this.blockEntity.getProgress();
        int maxProgress = this.blockEntity.getMaxProgress();
        int arrowPixelSize = 49;

        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }

    // quick helper to transfer stack on shift-click (basic implementation)
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();
            int containerSlots = 5;
            if (index < containerSlots) {
                // from BE to player
                if (!this.moveItemStackTo(stack, containerSlots, this.slots.size(), true)) return ItemStack.EMPTY;
            } else {
                // from player to BE (try inputs only)
                if (!this.moveItemStackTo(stack, 0, 4, false)) return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return result;
    }
}
