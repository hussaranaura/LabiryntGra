package stachugame.api.maps;

import java.text.Normalizer;

public enum Direction {
    NORTH((byte) 0b0000_0001),
    EAST((byte) 0b0000_0010),
    SOUTH((byte) 0b0000_0100),
    WEST((byte) 0b0000_1000);

    private final byte bitmask;

    /**
     * Konstruktor enumeratora `Direction`
     * @param bitmask Bitmaska kierunku używana w dekodowaniu map
     */
    Direction(byte bitmask){
        this.bitmask = bitmask;
    }

    /**
     * Zwraca bitmaskę kierunku używaną w dekodowaniu map
     * @return Bitmaska kierunku
     */
    public byte getBitmask() {
        return bitmask;
    }

    public static Direction getDirByName(String name){
        return switch(Normalizer.normalize(name.toUpperCase(), Normalizer.Form.NFD)){
            case "N", "NORTH", "POLNOC" -> NORTH;
            case "E", "EAST", "WSCHOD" -> EAST;
            case "S", "SOUTH", "POLUDNIE" -> SOUTH;
            case "W", "WEST", "ZACHOD" -> WEST;
            default -> null;
        };
    }
}
