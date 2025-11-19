package org.dantesys.nexus.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.items.NexusItems;

public class NexusItemModelProvider extends ItemModelProvider {

    public NexusItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Nexus.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generateFragmentoModel(NexusItems.FRAGMENTO_FOGO.get(), "fogo");
        generateFragmentoModel(NexusItems.FRAGMENTO_AGUA.get(), "agua");
        generateFragmentoModel(NexusItems.FRAGMENTO_NATUREZA.get(), "natureza");
        generateFragmentoModel(NexusItems.FRAGMENTO_ELETRICO.get(), "eletrico");
        generateFragmentoModel(NexusItems.FRAGMENTO_ESCURO.get(), "escuro");
        generateFragmentoModel(NexusItems.FRAGMENTO_LUZ.get(), "luz");
        generateFragmentoModel(NexusItems.FRAGMENTO_METAL.get(), "metal");
        generateFragmentoModel(NexusItems.FRAGMENTO_ROCHA.get(), "rocha");
        generateEsmeraldaModel(NexusItems.ESMERALDA_FOGO.get(), "fogo");
        generateEsmeraldaModel(NexusItems.ESMERALDA_AGUA.get(), "agua");
        generateEsmeraldaModel(NexusItems.ESMERALDA_NATUREZA.get(), "natureza");
        generateEsmeraldaModel(NexusItems.ESMERALDA_ELETRICO.get(), "eletrico");
        generateEsmeraldaModel(NexusItems.ESMERALDA_ESCURO.get(), "escuro");
        generateEsmeraldaModel(NexusItems.ESMERALDA_LUZ.get(), "luz");
        generateEsmeraldaModel(NexusItems.ESMERALDA_METAL.get(), "metal");
        generateEsmeraldaModel(NexusItems.ESMERALDA_ROCHA.get(), "rocha");
        basicItem(NexusItems.ACO.get());
    }
    private void generateFragmentoModel(Item item, String textureName) {
        getBuilder(BuiltInRegistries.ITEM.getKey(item).getPath())
                .parent(new ModelFile.UncheckedModelFile(Nexus.MODID+":item/fragmento_base"))
                .texture("0", Nexus.MODID+":item/fragmento_" + textureName);
    }
    private void generateEsmeraldaModel(Item item, String textureName) {
        getBuilder(BuiltInRegistries.ITEM.getKey(item).getPath())
                .parent(new ModelFile.UncheckedModelFile(Nexus.MODID+":item/esmeralda_base"))
                .texture("0", Nexus.MODID+":item/esmeralda_" + textureName);
    }
}
