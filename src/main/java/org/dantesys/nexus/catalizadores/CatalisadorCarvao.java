package org.dantesys.nexus.catalizadores;

import net.minecraft.world.item.ItemStack;
import org.dantesys.nexus.data.NexusDataComponent;
import org.dantesys.nexus.util.ForjaContext;
import org.dantesys.nexus.util.ICatalisador;

public class CatalisadorCarvao implements ICatalisador {
    @Override
    public void aplicar(ItemStack resultado, ForjaContext ctx) {
        float atual = resultado.getOrDefault(
                NexusDataComponent.DANO_BONUS.get(),
                0f
        );
        resultado.set(
                NexusDataComponent.DANO_BONUS.get(),
                atual + 0.5f
        );

    }
}