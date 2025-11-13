package org.dantesys.nexus.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.dantesys.nexus.Nexus;

public class NexusDatagen {
    public static void gatherData(GatherDataEvent event) {
        PackOutput output = event.getGenerator().getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        event.getGenerator().addProvider(event.includeServer(), new NexusTagItemProvider(output, event.getLookupProvider(), null, helper));
        event.getGenerator().addProvider(event.includeClient(), new NexusBlockStateProvider(output,Nexus.MODID,helper));
        // Registra os modelos dos itens
        event.getGenerator().addProvider(event.includeClient(), new NexusItemModelProvider(output, helper));
        event.getGenerator().addProvider(event.includeServer(), new NexusRecipeProvider(
                event.getGenerator().getPackOutput(), event.getLookupProvider()));
        // Aqui você pode adicionar outros providers futuramente:
        // - Recipes
        // - Loot tables
        // - Tags
        // - BlockStates
    }
}
