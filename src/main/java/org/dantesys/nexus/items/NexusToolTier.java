package org.dantesys.nexus.items;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.Tags;
import org.dantesys.nexus.util.NexusTags;

public class NexusToolTier {
    public static final Tier ACO = new SimpleTier(NexusTags.Blocks.INCORRECT_ACO,
            906,7.0F,2.5F,18, () -> Ingredient.of(NexusItems.ACO.get()));
    public static final Tier AGUA = new SimpleTier(Tags.Blocks.NEEDS_NETHERITE_TOOL,
            2400,8.5F,3.5F,18,() -> Ingredient.of(NexusItems.ESMERALDA_AGUA.get())
    );
    public static final Tier ELETRICO = new SimpleTier(Tags.Blocks.NEEDS_NETHERITE_TOOL,
            1800, 11.0F, 3.0F, 20, () -> Ingredient.of(NexusItems.ESMERALDA_ELETRICO.get())
    );
    public static final Tier SOMBRA = new SimpleTier(Tags.Blocks.NEEDS_NETHERITE_TOOL,
            2100, 8.0F, 5.0F, 12, () -> Ingredient.of(NexusItems.ESMERALDA_ESCURO.get())
    );
    public static final Tier FOGO = new SimpleTier(Tags.Blocks.NEEDS_NETHERITE_TOOL,
            2000, 9.0F, 4.5F, 15, () -> Ingredient.of(NexusItems.ESMERALDA_FOGO.get())
    );
    public static final Tier LUZ = new SimpleTier(Tags.Blocks.NEEDS_NETHERITE_TOOL,
            2300, 9.0F, 4.0F, 25, () -> Ingredient.of(NexusItems.ESMERALDA_LUZ.get())
    );
    public static final Tier METAL = new SimpleTier(Tags.Blocks.NEEDS_NETHERITE_TOOL,
            2800, 7.5F, 5.5F, 13, () -> Ingredient.of(NexusItems.ESMERALDA_METAL.get())
    );
    public static final Tier NATUREZA = new SimpleTier(Tags.Blocks.NEEDS_NETHERITE_TOOL,
            2600, 8.0F, 3.5F, 17, () -> Ingredient.of(NexusItems.ESMERALDA_NATUREZA.get())
    );
    public static final Tier ROCHA = new SimpleTier(Tags.Blocks.NEEDS_NETHERITE_TOOL,
            3000, 7.0F, 6.0F, 10,
            () -> Ingredient.of(NexusItems.ESMERALDA_ROCHA.get())
    );
}
