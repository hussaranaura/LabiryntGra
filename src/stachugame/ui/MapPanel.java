package stachugame.ui;

import stachugame.api.IGame;
import stachugame.api.entities.IEntity;
import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;
import stachugame.api.maps.IRoomMap;
import stachugame.implementation.util.ImageCache;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    private final Image background;

    public MapPanel(){
        this.background = ImageCache.get("grid");
        setSize(200, 200);
        setMaximumSize(getSize());
        setMinimumSize(getSize());
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 200, 200, Color.BLACK, null);

        IRoomMap map= IGame.getInstance().getCurrentMap();

        if (map==null)
            return;

        IRoom[][] rooms = map.getRooms();

        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                IRoom room = rooms[x][y];
                if(room == null)
                    continue;

                IEntity player = IGame.getInstance().getPlayer();
                if(player != null && room.equals(player.getCurrentRoom())){
                    g.drawImage(ImageCache.get("player_marker"), x*20, y*20, null, null);
                }
                if(!room.wasFound() && !room.isFinalRoom())
                    continue;

                for(Direction dir : Direction.values()){
                    g.drawImage(ImageCache.get(getImageName(room, dir)),x*20, y*20, null, null);
                }

            }
        }


    }

    private String getImageName(IRoom room, Direction dir){
        return "wall_"+dir.ordinal()+ (room.isNextRoomOpen(dir) ? "_open" : "") + (room.isFinalRoom() ? "_red" : "");
    }
}
