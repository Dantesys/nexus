package org.dantesys.nexus.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils.*;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class NexusRegion extends Region {
    public NexusRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // Aqui definimos alguns pontos climáticos para cada bioma.
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        // ========= AGUA (úmido e fresco – muito comum perto de áreas moderadas)
        new ParameterPointListBuilder()
                .temperature(Temperature.COOL, Temperature.NEUTRAL)
                .humidity(Humidity.NEUTRAL, Humidity.HUMID)
                .continentalness(Continentalness.COAST, Continentalness.INLAND)
                .erosion(Erosion.EROSION_0, Erosion.EROSION_2)
                .depth(Depth.SURFACE)
                .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING)
                .build().forEach(point -> builder.add(point, NexusBiomes.AGUA));

        // ========= FOGO (quente, seco, mais raro)
        new ParameterPointListBuilder()
                .temperature(Temperature.HOT)
                .humidity(Humidity.DRY)
                .continentalness(Continentalness.INLAND, Continentalness.FAR_INLAND)
                .erosion(Erosion.EROSION_0, Erosion.EROSION_1)
                .depth(Depth.SURFACE)
                .weirdness(Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING)
                .build().forEach(point -> builder.add(point, NexusBiomes.FOGO));

        // ========= LUZ (claro, equilibrado, comum)
        new ParameterPointListBuilder()
                .temperature(Temperature.NEUTRAL)
                .humidity(Humidity.NEUTRAL)
                .continentalness(Continentalness.MID_INLAND, Continentalness.INLAND)
                .erosion(Erosion.EROSION_0, Erosion.EROSION_1)
                .depth(Depth.SURFACE)
                .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING)
                .build().forEach(point -> builder.add(point, NexusBiomes.LUZ));

        // ========= ESCURO (mais denso e raro – agora com mais clima)
        new ParameterPointListBuilder()
                .temperature(Temperature.NEUTRAL)
                .humidity(Humidity.NEUTRAL)
                .continentalness(Continentalness.INLAND)
                .erosion(Erosion.EROSION_2, Erosion.EROSION_3)
                .depth(Depth.SURFACE)
                .weirdness(Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.HIGH_SLICE_VARIANT_DESCENDING)
                .build().forEach(point -> builder.add(point, NexusBiomes.ESCURO));

        // ========= ROCHA (montanhoso)
        new ParameterPointListBuilder()
                .temperature(Temperature.COOL)
                .humidity(Humidity.DRY, Humidity.NEUTRAL)
                .continentalness(Continentalness.FAR_INLAND)
                .erosion(Erosion.EROSION_3, Erosion.EROSION_5)
                .depth(Depth.SURFACE)
                .weirdness(Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.PEAK_NORMAL)
                .build().forEach(point -> builder.add(point, NexusBiomes.ROCHA));

        // ========= METAL (bioma raro, clima seco mas estável)
        new ParameterPointListBuilder()
                .temperature(Temperature.NEUTRAL)
                .humidity(Humidity.DRY)
                .continentalness(Continentalness.INLAND)
                .erosion(Erosion.EROSION_1, Erosion.EROSION_2)
                .depth(Depth.SURFACE)
                .weirdness(Weirdness.MID_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_NORMAL_ASCENDING)
                .build().forEach(point -> builder.add(point, NexusBiomes.METAL));

        // ========= NATUREZA (selva-like, quente e úmido)
        new ParameterPointListBuilder()
                .temperature(Temperature.HOT)
                .humidity(Humidity.HUMID)
                .continentalness(Continentalness.INLAND, Continentalness.MID_INLAND)
                .erosion(Erosion.EROSION_1, Erosion.EROSION_3)
                .depth(Depth.SURFACE)
                .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING)
                .build().forEach(point -> builder.add(point, NexusBiomes.NATUREZA));

        // ========= ELETRICO (exótico – usa weirdness alto para formas diferentes)
        new ParameterPointListBuilder()
                .temperature(Temperature.NEUTRAL)
                .humidity(Humidity.NEUTRAL)
                .continentalness(Continentalness.INLAND, Continentalness.FAR_INLAND)
                .erosion(Erosion.EROSION_0, Erosion.EROSION_2)
                .depth(Depth.SURFACE)
                .weirdness(Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_NORMAL_ASCENDING)
                .build().forEach(point -> builder.add(point, NexusBiomes.ELETRICO));

        // aplica tudo
        builder.build().forEach(mapper);
    }
}
