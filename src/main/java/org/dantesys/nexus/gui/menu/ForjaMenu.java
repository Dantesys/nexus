package org.dantesys.nexus.gui.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.dantesys.nexus.blocks.entity.ForjaBlockEntity;
import org.dantesys.nexus.gui.NexusMenus;

import java.util.Objects;

public class ForjaMenu extends AbstractContainerMenu {
    private final ForjaBlockEntity blockEntity;
    private final ContainerLevelAccess access;
    private final ContainerData data;

    // ctor used server-side
    public ForjaMenu(int id, Inventory playerInventory, ForjaBlockEntity be, ContainerData data) {
        super(NexusMenus.FORJA_MENU.get(), id); // ajuste se o seu registry usa outro caminho
        this.blockEntity = be;
        // DataSlot para progresso
        this.data = data;
        addDataSlots(data);
        this.access = ContainerLevelAccess.create(be.getLevel(), be.getBlockPos());

        // BE slots (0..4)
        // inputs 0..3 positioned on GUI (x,y chosen arbitrarily; ajuste conforme seu background)
        addSlot(new SlotItemHandler(be.inventory, ForjaBlockEntity.SLOT_ACO, 26, 16));
        addSlot(new SlotItemHandler(be.inventory, ForjaBlockEntity.SLOT_LAVA, 8, 16));
        addSlot(new SlotItemHandler(be.inventory, ForjaBlockEntity.SLOT_CATALYST_1, 80, 27));
        addSlot(new SlotItemHandler(be.inventory, ForjaBlockEntity.SLOT_CATALYST_2, 98, 27));
        addSlot(new SlotItemHandler(be.inventory, ForjaBlockEntity.SLOT_CATALYST_3, 80, 45));
        addSlot(new SlotItemHandler(be.inventory, ForjaBlockEntity.SLOT_MOLDE, 53, 36));
        addSlot(new SlotItemHandler(be.inventory, ForjaBlockEntity.SLOT_ESMERALDA, 98, 45));
        // output slot 4 (readonly typical) — override if you want special behavior
        addSlot(new SlotItemHandler(be.inventory, ForjaBlockEntity.SLOT_RESULTADO, 152, 36) {
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
    public static ForjaMenu fromNetwork(int id, Inventory inv, FriendlyByteBuf buf) {
        ForjaBlockEntity be = (ForjaBlockEntity) inv.player.level().getBlockEntity(buf.readBlockPos());
        return new ForjaMenu(id, inv, Objects.requireNonNull(be),new SimpleContainerData(6));
    }
    public ForjaBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, org.dantesys.nexus.blocks.NexusBlocks.FORJA.get());
    }
    public int getLava() {
        return data.get(0);
    }

    public int getMaxLava() {
        return data.get(1);
    }

    public int getAcoDerretido() {
        return data.get(2);
    }

    public int getMaxAcoDerretido() {
        return data.get(3);
    }

    public int getProgress() {
        return data.get(4);
    }

    public int getMaxProgress() {
        return data.get(5);
    }

    // quick helper to transfer stack on shift-click (basic implementation)
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();
            int containerSlots = ForjaBlockEntity.TOTAL_SLOTS;
            if (index < containerSlots) {
                if (!this.moveItemStackTo(stack, containerSlots, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else {
                if (!this.moveItemStackTo(stack, 0, containerSlots - 1, false))
                    return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
        }
        return result;
    }

    public int getDerreteBar() {
        int progress = this.blockEntity.getDerretimento();
        int maxProgress = this.blockEntity.getMaxProgress();
        int arrowPixelSize = 16;
        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }

    public boolean isDerretendo(){
        return this.blockEntity.getDerretimento()>0;
    }

    public int getLavaBar() {
        int progress = this.blockEntity.getLava();
        int maxProgress = this.blockEntity.getMaxLava();
        int arrowPixelSize = 33;
        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }

    public int getAcoBar() {
        int progress = this.blockEntity.getAcoderretido();
        int maxProgress = this.blockEntity.getMaxAcoderretido();
        int arrowPixelSize = 33;
        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }
    public boolean isForjando(){
        return this.blockEntity.getProgress()>0;
    }

    public int getForjando() {
        int progress = this.blockEntity.getProgress();
        int maxProgress = this.blockEntity.getMaxProgress();
        int arrowPixelSize = 30;
        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }
}