package org.dantesys.nexus.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.dantesys.nexus.Nexus;

public class NexusRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Nexus.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Nexus.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<InfusorRecipe>> INFUSOR_SERIALIZER =
            SERIALIZERS.register("infusor", InfusorRecipe.Serializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ExtratorRecipe>> EXTRATOR_SERIALIZER =
            SERIALIZERS.register("extrator", ExtratorRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<InfusorRecipe>> INFUSOR_TYPE =
            TYPES.register("infusor", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "infusor";
                }
            });
    public static final DeferredHolder<RecipeType<?>, RecipeType<ExtratorRecipe>> EXTRATOR_TYPE =
            TYPES.register("extrator", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "extrator";
                }
            });


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
