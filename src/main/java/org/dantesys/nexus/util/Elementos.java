package org.dantesys.nexus.util;

public enum Elementos {
    AGUA, ELETRICO, ESCCURO, FOGO, LUZ, NATUREZA, METAL, ROCHA;

    public static Elementos getId(int elemento) {
        return switch (elemento) {
            case 1 -> ELETRICO;
            case 2 -> ESCCURO;
            case 3 -> FOGO;
            case 4 -> LUZ;
            case 5 -> NATUREZA;
            case 6 -> METAL;
            case 7 -> ROCHA;
            default -> AGUA;
        };
    }
}
