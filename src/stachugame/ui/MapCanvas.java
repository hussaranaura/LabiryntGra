package stachugame.ui;

import stachugame.api.IGame;
import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;
import stachugame.api.maps.IRoomMap;
import stachugame.implementation.util.MapLoaderUtil;

import javax.swing.*;
import java.awt.*;

public class MapCanvas extends Canvas {

    private final Image background;
    //private final IRoomMap map;
    public MapCanvas(Image background){
        this.background = background;
        setSize(200, 200);
        setMaximumSize(getSize());
        setMinimumSize(getSize());
        //this.map = MapLoaderUtil.loadLevel("59|35|0000000000000000000000000002000000045f100000000a000000000c510000000000000000000000000000000000000000");
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,200,200);
        g.drawImage(background, 0, 0, 200, 200, null);
        
        IRoomMap map=IGame.getInstance().getCurrentMap();
        
        if (map==null)
        	return;
        
        IRoom[][] rooms = map.getRooms();
        g.setColor(Color.RED);
        Point pos = map.getFinishPos();
        g.fillRect(pos.x*20, pos.y*20, 20, 20);
        g.setColor(Color.GREEN);
        pos = map.getStartPos();
        g.fillRect(pos.x*20, pos.y*20, 20, 20);


        g.setColor(Color.BLUE);
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                IRoom room = rooms[x][y];
                if(room == null)
                    continue;

                //g.fillRect(x * 20, y * 20, 20, 20);
                for(Direction dir : Direction.values()){
                    if(!room.isNextRoomOpen(dir)){
                        if(dir == Direction.NORTH)
                            g.drawLine(x*20, y*20, (x+1)*20, y*20);

                        if(dir == Direction.SOUTH)
                            g.drawLine(x*20, (y+1)*20, (x+1)*20, (y+1)*20);

                        if(dir == Direction.EAST)
                            g.drawLine((x+1)*20, y*20, (x+1)*20, (y+1)*20);

                        if(dir == Direction.WEST)
                            g.drawLine(x*20, y*20, x*20, (y+1)*20);
                    }
                }
            }
        }


    }
}
