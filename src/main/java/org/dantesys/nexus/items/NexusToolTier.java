package org.dantesys.nexus.items;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import org.dantesys.nexus.util.NexusTags;

public class NexusToolTier {
    public static final Tier ACO = new SimpleTier(NexusTags.Blocks.INCORRECT_ACO,
            906,7.0F,2.5F,18, () -> Ingredient.of(NexusItems.ACO.get()));
}
