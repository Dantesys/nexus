package org.dantesys.nexus.gui.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.dantesys.nexus.blocks.NexusBlocks;
import org.dantesys.nexus.blocks.entity.BolaCristalBlockEntity;
import org.dantesys.nexus.gui.NexusMenus;
import org.dantesys.nexus.items.NexusItems;
import org.dantesys.nexus.util.NexusTags;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BolaCristalMenu extends AbstractContainerMenu {
    private final BolaCristalBlockEntity blockEntity;
    private final ContainerLevelAccess access;
    private final SimpleContainerData data;

    // ctor used server-side
    public BolaCristalMenu(int id, Inventory playerInventory, BolaCristalBlockEntity be) {
        super(NexusMenus.BOLA_CRISTAL_MENU.get(), id); // ajuste se o seu registry usa outro caminho
        this.blockEntity = be;
        this.access = ContainerLevelAccess.create(be.getLevel(), be.getBlockPos());
        this.data = new SimpleContainerData(4); // mana, regen, afinidade, sinergia
        addDataSlots(data);
        if (!playerInventory.player.level().isClientSide()) {
            be.updateFromEmerald(playerInventory.player);
        }
        // BE slots (0..4)
        // inputs 0..3 positioned on GUI (x,y chosen arbitrarily; ajuste conforme seu background)
        addSlot(new SlotItemHandler(be.inventory, 0, 80, 5) {

            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getTags().anyMatch(NexusTags.Items.EMERALD_ELEMENTAL::equals);
            }
        });
        addSlot(new SlotItemHandler(be.inventory, 1, 80, 64) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(NexusItems.NUCLEO.get()) && stack.isEnchanted();
            }
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
    }
    // factory used by client to construct menu from packet
    public static BolaCristalMenu fromNetwork(int id, Inventory inv, FriendlyByteBuf buf) {
        BolaCristalBlockEntity be = (BolaCristalBlockEntity) inv.player.level().getBlockEntity(buf.readBlockPos());
        return new BolaCristalMenu(id, inv, Objects.requireNonNull(be));
    }
    public BolaCristalBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
        data.set(0, blockEntity.mana);
        data.set(1, blockEntity.regen);
        data.set(2, blockEntity.afinidade);
        data.set(3, blockEntity.sinergia);
    }
    public void setStats(int mana, int regen, int afinidade, int sinergia) {
        data.set(0, mana);
        data.set(1, regen);
        data.set(2, afinidade);
        data.set(3, sinergia);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, NexusBlocks.BOLA_CRISTAL.get());
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
    public Component getStatus(int status){
        return switch (status){
            case 1 -> getRegen();
            case 2 -> getAfinidade();
            case 3 -> getSinergia();
            default -> getMana();
        };
    }
    private Component getMana(){
        String status = getString(data.get(0));
        return Component.literal(status);
    }
    public int getTextColor() {
        var elemento = blockEntity.getElemento();

        if (elemento == null) return 0x8000ff;

        return switch (elemento) {
            case AGUA -> 0x0000FF;
            case ELETRICO -> 0xFFFF00;
            case SOMBRA -> 0x363636;
            case FOGO -> 0xFF0000;
            case LUZ -> 0xFFFFFF;
            case METAL -> 0x909090;
            case NATUREZA -> 0x00FF00;
            case ROCHA -> 0xA58C79;
        };
    }


    private static @NotNull String getString(int elementalStatics) {
        String status;
        if(elementalStatics<1){
            status = "F";
        }else if (elementalStatics < 170) {
            status = "E";
        } else if (elementalStatics < 340) {
            status = "D";
        } else if (elementalStatics < 510) {
            status = "C";
        } else if (elementalStatics < 680) {
            status = "B";
        } else if (elementalStatics < 850) {
            status = "A";
        } else {
            status = "S";
        }
        return status;
    }

    private Component getRegen(){
        String status = getString(data.get(1));
        return Component.literal(status);
    }

    private Component getAfinidade(){
        String status = getString(data.get(2));
        return Component.literal(status);
    }

    private Component getSinergia(){
        String status = getString(data.get(3));
        return Component.literal(status);
    }
    public String getBgStats() {
        var elemento = blockEntity.getElemento();

        if (elemento == null)
            return "textures/gui/status/nada.png";

        return switch (elemento) {
            case AGUA -> "textures/gui/status/agua.png";
            case ELETRICO -> "textures/gui/status/eletrico.png";
            case SOMBRA -> "textures/gui/status/escuro.png";
            case FOGO -> "textures/gui/status/fogo.png";
            case LUZ -> "textures/gui/status/luz.png";
            case METAL -> "textures/gui/status/metal.png";
            case NATUREZA -> "textures/gui/status/natureza.png";
            case ROCHA -> "textures/gui/status/rocha.png";
        };
    }
}