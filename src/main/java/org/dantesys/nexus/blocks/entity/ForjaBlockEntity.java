package org.dantesys.nexus.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.dantesys.nexus.blocks.NexusBlocks;
import org.dantesys.nexus.gui.menu.ForjaMenu;
import org.dantesys.nexus.items.NexusItems;
import org.dantesys.nexus.util.NexusTags;
import org.jetbrains.annotations.Nullable;

public class ForjaBlockEntity extends BlockEntity implements MenuProvider {
    // Slots da Forja
    public static final int SLOT_ACO          = 0; // aço
    public static final int SLOT_LAVA         = 1; // balde de lava
    public static final int SLOT_CATALYST_1   = 2;
    public static final int SLOT_CATALYST_2   = 3;
    public static final int SLOT_CATALYST_3   = 4;
    public static final int SLOT_MOLDE        = 5; // molde
    public static final int SLOT_ESMERALDA    = 6; // esmeralda
    public static final int SLOT_RESULTADO    = 7; // output
    public static final int TOTAL_SLOTS = 8;
    private int lava;
    private int acoderretido;
    private int progress = 0;
    private int derretimento = 0;
    private static final int MAX_PROGRESS = 100;
    private static final int MAX_LAVA = 4000; // 4 baldes
    private static final int MAX_ACODERRETIDO = 4000; // 4 baldes
    private static final int LAVA_COST_PER_TICK = 1;
    private static final int VALOR_DERRETIDO = 500;
    private static final int LAVA_COST_CRAFT = 50;
    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> lava;
                case 1 -> MAX_LAVA;
                case 2 -> acoderretido;
                case 3 -> MAX_ACODERRETIDO;
                case 4 -> progress;
                case 5 -> MAX_PROGRESS;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> lava = value;
                case 2 -> acoderretido = value;
                case 4 -> progress = value;
            }
        }

        @Override
        public int getCount() {
            return 6;
        }
    };

    public final ItemStackHandler inventory = new ItemStackHandler(TOTAL_SLOTS) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return switch (slot) {
                case SLOT_ACO -> stack.is(NexusItems.ACO.get());
                case SLOT_LAVA -> stack.is(net.minecraft.world.item.Items.LAVA_BUCKET);
                case SLOT_CATALYST_1, SLOT_CATALYST_2, SLOT_CATALYST_3 ->
                        stack.is(NexusTags.Items.CATALIZADORES);
                case SLOT_MOLDE -> stack.is(NexusTags.Items.MOLDES);
                case SLOT_ESMERALDA -> stack.is(NexusTags.Items.EMERALD_ELEMENTAL);
                case SLOT_RESULTADO -> false;
                default -> false;
            };
        }
    };
    public ForjaBlockEntity(BlockPos pos, BlockState blockState) {
        super(NexusBlocks.FORJA_BE.get(), pos, blockState);
    }
    public int getLava() {
        return lava;
    }
    public int getMaxLava() {
        return MAX_LAVA;
    }
    public int getAcoderretido() {
        return acoderretido;
    }
    public int getMaxAcoderretido() {
        return MAX_ACODERRETIDO;
    }
    public int getProgress(){
        return this.progress;
    }
    public int getDerretimento(){
        return this.derretimento;
    }
    public void setProgress(int p){
        this.progress=p;
    }
    public int getMaxProgress(){
        return MAX_PROGRESS;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.nexus.forja");
    }
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putInt("lava", lava);
        tag.putInt("aco", acoderretido);
        tag.putInt("progresso",progress);
        tag.putInt("derretimento",derretimento);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        this.lava = tag.getInt("lava");
        this.progress=tag.getInt("progresso");
        this.acoderretido=tag.getInt("aco");
        this.derretimento=tag.getInt("derretimento");
    }
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ForjaMenu(i,inventory,this,data);
    }
    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }
    private void tryConsumeLava() {
        ItemStack stack = inventory.getStackInSlot(SLOT_LAVA);
        if (stack.is(net.minecraft.world.item.Items.LAVA_BUCKET) && lava <= MAX_LAVA - 1000) {
            inventory.setStackInSlot(SLOT_LAVA, new ItemStack(net.minecraft.world.item.Items.BUCKET));
            lava += 1000;
            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    public <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (level.isClientSide) return;
        tryConsumeLava();
        if(hasDerretido()){
            increaseDerretimentoProgress();
            setChanged(level, blockPos, blockState);
            if(derretido()){
                derreter();
                resetDerreter();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
        /*if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level, blockPos, blockState);
            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        } else {
            resetProgress();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }*/
    }
    private void resetDerreter() {
        derretimento = 0;
    }
    private void derreter(){
        if (acoderretido + VALOR_DERRETIDO <= MAX_ACODERRETIDO) {
            inventory.extractItem(SLOT_ACO, 1, false);
            acoderretido += VALOR_DERRETIDO;
        }
    }

    private boolean derretido() {
        return derretimento >= MAX_PROGRESS;
    }
    private boolean hasDerretido(){
        return acoderretido < MAX_ACODERRETIDO
                && lava > 0
                && !inventory.getStackInSlot(SLOT_ACO).isEmpty();
    }

    private void increaseDerretimentoProgress() {
        derretimento ++;
        lava-=LAVA_COST_PER_TICK;
        if (this.level != null && !this.level.isClientSide) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }
    private void resetProgress() {
        progress = 0;
    }
    private boolean hasCraftingFinished() {
        return this.progress >= this.getMaxProgress() && lava >= LAVA_COST_CRAFT;
    }
    private void increaseCraftingProgress() {
        progress++;
        lava-=LAVA_COST_PER_TICK;
        if (this.level != null && !this.level.isClientSide) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }
    private boolean hasRecipe() {
        return lava <= MAX_LAVA && lava>0;
    }
    private void craftItem() {
        lava-=LAVA_COST_CRAFT;
    }
}
