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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.dantesys.nexus.gui.menu.ExtratorMenu;
import org.dantesys.nexus.recipe.*;

import javax.annotation.Nullable;
import java.util.Optional;

import static org.dantesys.nexus.blocks.NexusBlocks.EXTRATOR_BE;

public class ExtratorBlockEntity extends BlockEntity implements MenuProvider{
    public final ItemStackHandler inventory = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    private int progress = 0;
    public ExtratorBlockEntity(BlockPos pos, BlockState blockState) {
        super(EXTRATOR_BE.get(), pos, blockState);
    }
    public int getProgress(){
        return this.progress;
    }
    public void setProgress(int p){
        this.progress=p;
    }
    public int getMaxProgress(){
        return 100;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putInt("progresso",progress);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        this.progress=tag.getInt("progresso");
    }
    @Override
    public Component getDisplayName() {
        return Component.translatable("container.nexus.extrator");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ExtratorMenu(i,inventory,this);
    }
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    public <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
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
        }
    }
    private void craftItem() {
        Optional<RecipeHolder<ExtratorRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();
        inventory.extractItem(0, 1, false);
        inventory.setStackInSlot(1, new ItemStack(output.getItem(),
                inventory.getStackInSlot(1).getCount() + output.getCount()));
    }
    private void resetProgress() {
        progress = 0;
    }
    private boolean hasCraftingFinished() {
        return this.progress >= this.getMaxProgress();
    }
    private void increaseCraftingProgress() {
        progress++;
        if (this.level != null && !this.level.isClientSide) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }
    private boolean hasRecipe() {
        Optional<RecipeHolder<ExtratorRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }
    private Optional<RecipeHolder<ExtratorRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(NexusRecipes.EXTRATOR_TYPE.get(), new ExtratorRecipeInput(inventory.getStackInSlot(0)), level);
    }
    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.getStackInSlot(1).isEmpty() ||
                inventory.getStackInSlot(1).getItem() == output.getItem();
    }
    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inventory.getStackInSlot(1).isEmpty() ? 64 : inventory.getStackInSlot(1).getMaxStackSize();
        int currentCount = inventory.getStackInSlot(1).getCount();

        return maxCount >= currentCount + count;
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }
}