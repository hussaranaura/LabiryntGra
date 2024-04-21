package stachugame.api.maps;

import java.text.Normalizer;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

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
