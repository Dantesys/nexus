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
import org.dantesys.nexus.blocks.entity.InfusorBlockEntity;
import org.dantesys.nexus.gui.NexusMenus;
import org.dantesys.nexus.items.NexusItems;
import org.dantesys.nexus.util.NexusTags;

import java.util.Objects;

public class InfusorMenu extends AbstractContainerMenu {
    private final InfusorBlockEntity blockEntity;
    private final ContainerLevelAccess access;

    // ctor used server-side
    public InfusorMenu(int id, Inventory playerInventory, InfusorBlockEntity be) {
        super(NexusMenus.INFUSOR_MENU.get(), id); // ajuste se o seu registry usa outro caminho
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
        addSlot(new SlotItemHandler(be.inventory, 0, 44, 16));
        addSlot(new SlotItemHandler(be.inventory, 1, 117, 16));
        addSlot(new SlotItemHandler(be.inventory, 2, 44, 52));
        addSlot(new SlotItemHandler(be.inventory, 3, 117, 52));
        // output slot 4 (readonly typical) — override if you want special behavior
        addSlot(new SlotItemHandler(be.inventory, 4, 80, 34) {
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
    public static InfusorMenu fromNetwork(int id, Inventory inv, FriendlyByteBuf buf) {
        InfusorBlockEntity be = (InfusorBlockEntity) inv.player.level().getBlockEntity(buf.readBlockPos());
        return new InfusorMenu(id, inv, Objects.requireNonNull(be));
    }
    public InfusorBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, org.dantesys.nexus.blocks.NexusBlocks.INFUSOR.get());
    }
    public boolean isCrafting(){
        return this.blockEntity.getProgress()>0;
    }
    public int getLoading(){
        int progress = this.blockEntity.getProgress()/2;
        int maxProgress = this.blockEntity.getMaxProgress()/2;
        int arrowPixelSize = 27;

        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }
    public ResourceLocation getTextureFromSlot(int slot){
        ItemStack stack = this.getSlot(slot).getItem();
        ResourceLocation rs = ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"textures/gui/infusor/ld_padrao.png");
        if(stack.getTags().anyMatch(tag -> tag.equals(NexusTags.Items.EMERALD_ELEMENTAL))){
            if(stack.is(NexusItems.ESMERALDA_AGUA.get())){
                rs = ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"textures/gui/infusor/ld_agua.png");
            }else if(stack.is(NexusItems.ESMERALDA_ELETRICO.get())){
                rs = ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"textures/gui/infusor/ld_eletrico.png");
            }else if(stack.is(NexusItems.ESMERALDA_ESCURO.get())){
                rs = ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"textures/gui/infusor/ld_escuro.png");
            }else if(stack.is(NexusItems.ESMERALDA_FOGO.get())){
                rs = ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"textures/gui/infusor/ld_fogo.png");
            }else if(stack.is(NexusItems.ESMERALDA_LUZ.get())){
                rs = ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"textures/gui/infusor/ld_luz.png");
            }else if(stack.is(NexusItems.ESMERALDA_METAL.get())){
                rs = ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"textures/gui/infusor/ld_metal.png");
            }else if(stack.is(NexusItems.ESMERALDA_NATUREZA.get())){
                rs = ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"textures/gui/infusor/ld_natureza.png");
            }else if(stack.is(NexusItems.ESMERALDA_ROCHA.get())){
                rs = ResourceLocation.fromNamespaceAndPath(Nexus.MODID,"textures/gui/infusor/ld_rocha.png");
            }
        }
        return rs;
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
