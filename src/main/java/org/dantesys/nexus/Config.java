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
        public Common(ModConfigSpec.Builder b) {
        }
    }
}
