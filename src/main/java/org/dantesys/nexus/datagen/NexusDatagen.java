package org.dantesys.nexus.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.dantesys.nexus.Nexus;

import java.util.List;
import java.util.Set;

public class NexusDatagen {
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        generator.addProvider(event.includeServer(), new LootTableProvider(output,Set.of(),List.of(
                new LootTableProvider.SubProviderEntry(NexusLootTableBlocks::new, LootContextParamSets.BLOCK)
        ),event.getLookupProvider()));
        generator.addProvider(event.includeServer(), new NexusRecipeProvider(output, event.getLookupProvider()));
        BlockTagsProvider blockTagsProvider = new NexusTagBlockProvider(output,  event.getLookupProvider(), helper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new NexusTagItemProvider(output, event.getLookupProvider(), blockTagsProvider.contentsGetter(), helper));
        generator.addProvider(event.includeClient(), new NexusBlockStateProvider(output,Nexus.MODID,helper));
        generator.addProvider(event.includeClient(), new NexusItemModelProvider(output, helper));
        generator.addProvider(event.includeServer(), new NexusDataPackProvider(output, event.getLookupProvider()));
        generator.addProvider(event.includeServer(), new NexusGlobalLootTable(output, event.getLookupProvider()));
    }
}
