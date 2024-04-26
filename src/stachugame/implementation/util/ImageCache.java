package stachugame.implementation.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class ImageCache {

    private static final HashMap<String, Image> imageMap;

    static {
        imageMap = new HashMap<>();

        try {
            putImage("grid");
            putImage("player_marker");
            loadDoors(false, false);
            loadDoors(false, true);
            loadDoors(true, false);
            loadDoors(true, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void loadDoors(boolean open, boolean red) throws IOException {
        for(int i = 0; i < 4; i++){
            String name = "wall_"+i + (open ? "_open" : "" ) + (red ? "_red" : "" );
            putImage(name);
        }

    }

    private static void putImage(String name) throws IOException {
        imageMap.put(name, ImageIO.read(Objects.requireNonNull(ImageCache.class.getResource("/" + name + ".png"))));;
    }

    public static Image get(String name){
        return imageMap.get(name);
    }
}
