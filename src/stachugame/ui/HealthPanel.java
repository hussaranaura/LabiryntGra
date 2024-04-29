package stachugame.ui;

import stachugame.Main;
import stachugame.api.GameState;
import stachugame.api.IGame;
import stachugame.api.entities.IEntity;
import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;
import stachugame.api.maps.IRoomMap;
import stachugame.implementation.util.ImageCache;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiedzialna za rysowanie życia
 */
public class HealthPanel extends JPanel {
    private final Image background;
    private final Image bar;
    public HealthPanel() {
        this.background = ImageCache.get("bar_bg");
        this.bar = ImageCache.get("bar_health");
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.clearRect(0, 0, 223, 90);

        IEntity player = IGame.getInstance().getPlayer();
        GameState state = IGame.getInstance().getState();
        if(state != GameState.INITIALIZING && state != GameState.NOT_INITIALIZED) {
            float percent = (float) player.getHealth() / player.getMaxHealth();
            g.drawImage(background, 0, 0, 223, 23, null, null);
            g.drawImage(bar, 23, 3, (int) (177*percent), 17, null, null);

            g.setFont(Main.monocraft_12_3.deriveFont(Font.PLAIN, 15));
            String health = player.getHealth() + " / " + player.getMaxHealth() + " HP";
            int width = g.getFontMetrics().stringWidth(health);
            g.drawString(health, (223-width)/2, 40);

        }

    }
}
