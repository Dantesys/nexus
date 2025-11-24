package org.dantesys.nexus.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import org.dantesys.nexus.Nexus;
import org.dantesys.nexus.items.NexusItems;

import java.util.LinkedHashMap;

public class NexusItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

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
        handheldItem(NexusItems.ACO_AXE.get());
        handheldItem(NexusItems.ACO_HOE.get());
        handheldItem(NexusItems.ACO_PICKAXE.get());
        handheldItem(NexusItems.ACO_SWORD.get());
        handheldItem(NexusItems.ACO_SHOVEL.get());
        trimmedArmorItem(NexusItems.ACO_HELMET);
        trimmedArmorItem(NexusItems.ACO_CHESTPLATE);
        trimmedArmorItem(NexusItems.ACO_LEGGINGS);
        trimmedArmorItem(NexusItems.ACO_BOOTS);
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
    private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
        final String MOD_ID = Nexus.MODID;
        if(itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;
                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };
                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }
}
