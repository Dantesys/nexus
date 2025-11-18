package org.dantesys.nexus.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.dantesys.nexus.gui.menu.InfusorMenu;
import org.dantesys.nexus.recipe.InfusorRecipe;
import org.dantesys.nexus.recipe.InfusorRecipeInput;
import org.dantesys.nexus.recipe.NexusRecipes;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static org.dantesys.nexus.blocks.NexusBlocks.INFUSOR_BE;

public class InfusorBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler inventory = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    private int progress = 0;
    public InfusorBlockEntity(BlockPos pos, BlockState blockState) {
        super(INFUSOR_BE.get(), pos, blockState);
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
        return Component.translatable("container.nexus.infusor");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new InfusorMenu(i,inventory,this);
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
            }
        } else {
            resetProgress();
        }
    }
    private void craftItem() {
        Optional<RecipeHolder<InfusorRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();
        inventory.extractItem(0, 1, false);
        inventory.extractItem(1, 1, false);
        inventory.extractItem(2, 1, false);
        inventory.extractItem(3, 1, false);
        inventory.setStackInSlot(4, new ItemStack(output.getItem(),
                inventory.getStackInSlot(4).getCount() + output.getCount()));
    }
    private void resetProgress() {
        progress = 0;
    }
    private boolean hasCraftingFinished() {
        return this.progress >= this.getMaxProgress();
    }
    private void increaseCraftingProgress() {
        progress++;
    }
    private boolean hasRecipe() {
        Optional<RecipeHolder<InfusorRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }
    private Optional<RecipeHolder<InfusorRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(NexusRecipes.INFUSOR_TYPE.get(), new InfusorRecipeInput(inventory.getStackInSlot(0),inventory.getStackInSlot(1),inventory.getStackInSlot(2),inventory.getStackInSlot(3)), level);
    }
    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.getStackInSlot(4).isEmpty() ||
                inventory.getStackInSlot(4).getItem() == output.getItem();
    }
    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inventory.getStackInSlot(4).isEmpty() ? 64 : inventory.getStackInSlot(4).getMaxStackSize();
        int currentCount = inventory.getStackInSlot(4).getCount();

        return maxCount >= currentCount + count;
    }
}
