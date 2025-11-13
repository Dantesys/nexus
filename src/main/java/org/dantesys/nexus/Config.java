package org.dantesys.nexus;

import net.neoforged.neoforge.common.ModConfigSpec;


public class Config {
    public static final ModConfigSpec SPEC;
    public static final Common COMMON;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        COMMON = new Common(builder);
        SPEC = builder.build();
    }

    public static class Common {
        // chance base para o craft (0.0 - 1.0)
        public final ModConfigSpec.DoubleValue baseChance;
        // incremento por cada esmeralda adicional do mesmo tipo (0.0 - 1.0)
        public final ModConfigSpec.DoubleValue perMatchIncrease;

        public Common(ModConfigSpec.Builder b) {
            b.push("infusor");
            baseChance = b
                    .comment("Chance base de sucesso para o Infusor (0.0 - 1.0). Ex: 0.25 = 25%")
                    .defineInRange("baseChance", 0.25d, 0.0d, 1.0d);
            perMatchIncrease = b
                    .comment("Aumento percentual por cada esmeralda adicional do mesmo elemento (0.0 - 1.0). Ex: 0.20 = +20% por cada duplicata")
                    .defineInRange("perMatchIncrease", 0.20d, 0.0d, 1.0d);
            b.pop();
        }
    }
}
