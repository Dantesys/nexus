package org.dantesys.nexus.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.dantesys.nexus.item.ModItems;
import org.dantesys.nexus.item.Orb;
import org.dantesys.nexus.screen.ColetorMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
public class ColetorNexusBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public ColetorNexusBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COLETOR_NEXUS_BE.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Coletor");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new ColetorMenu(pContainerId, pInventory, this);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        inventory.setItem(0, itemHandler.getStackInSlot(0));
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, ColetorNexusBlockEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity)) {
            craftItem(pBlockEntity);
        }
    }
    private static boolean hasRecipe(ColetorNexusBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(0).getItem() == ModItems.NEXUS_ORB.get();
    }
    private static void craftItem(ColetorNexusBlockEntity entity) {
        ItemStack itemStack = entity.itemHandler.extractItem(0, 1, false);
        Item item = itemStack.getItem();
        int tipo = 0;
        System.out.println("AQUI");
        if(item == ModItems.NEXUS_ORB.get()){
            Orb orb = (Orb) item;
            System.out.println("É ORB");
            Level world = entity.getLevel();
            if (world != null) {
                ResourceKey<Level> dimensionKey = world.dimension();
                if(dimensionKey.equals(Level.OVERWORLD)) {
                    if(world.isDay()){
                        tipo=1;
                    }else{
                        tipo=2;
                    }
                }else if(dimensionKey.equals(Level.NETHER)){
                    tipo=3;
                }else if(dimensionKey.equals(Level.END)){
                    tipo=4;
                }
            }
            if (tipo != 0) {
                orb.setCarregar(itemStack,1,tipo);
                itemStack = new ItemStack(orb);
                entity.itemHandler.setStackInSlot(0,itemStack);
            }
        }
    }

    private static boolean hasNotReachedStackLimit(ColetorNexusBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(0).getCount() == entity.itemHandler.getStackInSlot(0).getMaxStackSize();
    }
}
