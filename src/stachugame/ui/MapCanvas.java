package stachugame.ui;

import javax.swing.*;
import java.awt.*;

public class MapCanvas extends Canvas {

    private final Image background;
    public MapCanvas(Image background){
        this.background = background;
        setSize(200, 200);
        setMaximumSize(getSize());
        setMinimumSize(getSize());
    }

    public void paint(Graphics g){
        g.drawImage(background, 0, 0, 200, 200, null);
    }
}
