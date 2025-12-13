package org.dantesys.nexus.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.dantesys.nexus.blocks.entity.BolaCristalBlockEntity;
import org.dantesys.nexus.data.NexusAttachmentType;
import org.dantesys.nexus.magic.ElementalStatics;
import org.dantesys.nexus.magic.MagicStats;
import org.dantesys.nexus.network.PacketSender;
import org.dantesys.nexus.util.Elementos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static org.dantesys.nexus.blocks.NexusBlocks.BOLA_CRISTAL_BE;

public class BolaCristal extends BaseEntityBlock {
    public static final MapCodec<BolaCristal> CODEC = simpleCodec(BolaCristal::new);
    public BolaCristal(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BolaCristalBlockEntity(blockPos,blockState);
    }
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return BolaCristal.box(0,0,0,16,22,16);
    }
    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(state.getBlock() != newState.getBlock()) {
            if(level.getBlockEntity(pos) instanceof BolaCristalBlockEntity pedestalBlockEntity) {
                pedestalBlockEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos,
                                              Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide) {
            MagicStats stats = pPlayer.getData(NexusAttachmentType.MAGIC_STATS);
            PacketSender.syncMagicStats((ServerPlayer) pPlayer, stats);
            if (!stats.isCreated()) {
                RandomSource r = pLevel.random;
                int qtd = quantidadeAleatoria(r);
                desbloquearAleatorios(stats, qtd, r);
                // Marca como criado para NUNCA mais gerar de novo
                stats.setCreated(true);
                // Sincroniza com o cliente
                if (pPlayer instanceof ServerPlayer sp) {
                    PacketSender.syncMagicStats(sp, stats);
                }
            }
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof BolaCristalBlockEntity bolaCristalBlockEntity) {
                pPlayer.openMenu(new SimpleMenuProvider(bolaCristalBlockEntity, Component.translatable("container.nexus.bola_cristal")), pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return ItemInteractionResult.SUCCESS;
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        if(level.isClientSide()) {
            return null;
        }
        return createTickerHelper(type, BOLA_CRISTAL_BE.get(),
                (level1, blockPos, blockState, blockEntity) -> blockEntity.tick(level1, blockPos, blockState));
    }
    private static int quantidadeAleatoria(RandomSource r) {
        int roll = r.nextInt(100); // 0–99
        if (roll < 10) return 0;
        if (roll < 30) return 1;
        if (roll < 50) return 2;
        if (roll < 80) return 3;
        if (roll < 85) return 4;
        if (roll < 90) return 5;
        if (roll < 95) return 6;
        if (roll < 99) return 7;
        return Elementos.values().length; // 2% começa com todos
    }
    private static void desbloquearAleatorios(MagicStats stats, int quantidade, RandomSource r) {
        List<Elementos> lista = new ArrayList<>(Arrays.asList(Elementos.values()));
        Collections.shuffle(lista, new Random(r.nextLong()));
        for (int i = 0; i < quantidade && i < lista.size(); i++) {
            Elementos tipo = lista.get(i);
            ElementalStatics e = stats.get(tipo);
            e.setUnlocked(true);
            e.setMana(0);
            e.setMaxMana(r.nextInt(1001));
            e.setAfinidade(r.nextInt(1001));
            e.setRegeneracao(r.nextInt(1001));
            e.setSinergia(r.nextInt(1001));
        }
    }
}