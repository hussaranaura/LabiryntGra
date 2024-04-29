package stachugame.api.maps;

import java.text.Normalizer;

/**
 * Enum Direction<br>
 * Przedstawia wszystkie kierunki geograficzne.
 */
public enum Direction {
    NORTH((byte) 0b0000_0001),
    SOUTH((byte) 0b0000_0100),
    EAST((byte) 0b0000_0010),
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
        switch(Normalizer.normalize(name.toUpperCase(), Normalizer.Form.NFD)){
            case "N":
            case "NORTH":
            case "POLNOC":
                return NORTH;
            case "E":
            case "EAST":
            case "WSCHOD":
                return EAST;
            case "S":
            case "SOUTH":
            case "POLUDNIE":
                return SOUTH;
            case "W":
            case "WEST":
            case "ZACHOD":
                return WEST;
            default:
                return null;
        }
    }
}
