package org.dantesys.nexus.catalizadores;

import net.minecraft.world.item.ItemStack;
import org.dantesys.nexus.data.NexusDataComponent;
import org.dantesys.nexus.util.ForjaContext;
import org.dantesys.nexus.util.ICatalisador;

public class CatalisadorNetherite implements ICatalisador {
    @Override
    public void aplicar(ItemStack resultado, ForjaContext ctx) {
        int atual = resultado.getOrDefault(
                NexusDataComponent.DURABILIDADE_BONUS.get(),
                0
        );
        resultado.set(
                NexusDataComponent.DURABILIDADE_BONUS.get(),
                atual + 100
        );
    }
}