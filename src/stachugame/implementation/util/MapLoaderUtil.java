package stachugame.implementation.util;

import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;
import stachugame.api.maps.IRoomMap;
import stachugame.implementation.map.GameMap;
import stachugame.implementation.map.GameRoom;

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
            byte roomMask = Byte.parseByte(codes[2].substring(i, i+1), 16);

            if(roomMask == 0)
                continue;

            boolean northOpen = (roomMask & Direction.NORTH.getBitmask()) > 0;
            boolean eastOpen = (roomMask & Direction.EAST.getBitmask()) > 0;
            boolean southOpen = (roomMask & Direction.SOUTH.getBitmask()) > 0;
            boolean westOpen = (roomMask & Direction.WEST.getBitmask()) > 0;
            rooms[i/10][i%10] = new GameRoom(northOpen, eastOpen, southOpen, westOpen);

        }



        return map;

    }
}
