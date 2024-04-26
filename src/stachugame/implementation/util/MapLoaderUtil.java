package stachugame.implementation.util;

import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;
import stachugame.api.maps.IRoomMap;
import stachugame.implementation.map.GameMap;
import stachugame.implementation.map.GameRoom;

import java.util.Map;

public class MapLoaderUtil {

    public static IRoomMap loadLevel(String mapcode){
        String[] codes = mapcode.split("\\|");

        int startX = Integer.parseInt(codes[0].substring(0, 1));
        int startY = Integer.parseInt(codes[0].substring(1));

        int finishX = Integer.parseInt(codes[1].substring(0, 1));
        int finishY = Integer.parseInt(codes[1].substring(1));

        IRoomMap map = new GameMap(startX, startY, finishX, finishY);

        IRoom[][] rooms = map.getRooms();
        for(int i = 0; i < 100; i++){
            int x = i/10;
            int y = i%10;
            byte roomMask = Byte.parseByte(codes[2].substring(i, i+1), 16);

            if(roomMask == 0)
                continue;

            rooms[x][y] = new GameRoom(map, (finishX == x && finishY == y));

            if(startX == x && startY == y)
                rooms[x][y].setFound();
        }
        for(int i = 0; i < 100; i++){
            int x = i/10;
            int y = i%10;
            IRoom room = rooms[x][y];

            if(room == null)
                continue;

            Map<Direction, IRoom> exits = ((GameRoom) room).getExits();
            byte roomMask = Byte.parseByte(codes[2].substring(i, i+1), 16);

            if((roomMask & Direction.NORTH.getBitmask()) > 0){
                exits.put(Direction.NORTH, rooms[x][y-1]);
            }
            if((roomMask & Direction.EAST.getBitmask()) > 0){
                exits.put(Direction.EAST, rooms[x+1][y]);
            }
            if((roomMask & Direction.SOUTH.getBitmask()) > 0){
                exits.put(Direction.SOUTH, rooms[x][y+1]);
            }
            if((roomMask & Direction.WEST.getBitmask()) > 0){
                exits.put(Direction.WEST, rooms[x-1][y]);
            }

        }



        return map;

    }

    public static IRoomMap getMap(int level) {
        String mapCode;
        switch(level){
            case 0:
                mapCode = "55|09|63636300638c9c9a00c900006900430000c3006965361863c3c3c9249c596900c30000c3006900006900a63632c100c9c9c9";
                break;
            default:
                mapCode = "61|51|0000000000000000000000000000000000006300000000aa000455559a000455553a00000000aa00000000c9000000000000";
                break;
        }

        return loadLevel(mapCode);
    }
}
