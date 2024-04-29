package stachugame.implementation.util;

import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;
import stachugame.api.maps.IRoomMap;
import stachugame.implementation.entities.enemy.Druid;
import stachugame.implementation.items.potions.HealthPotion;
import stachugame.implementation.map.GameMap;
import stachugame.implementation.map.GameRoom;

import java.util.Map;

public class MapLoaderUtil {

    private static String[] mapCodes = new String[]{
            "75|58|000000000000000000000000000000000000000000000000000004557510000000a000000004b00000000080000000000000",
            "35|98|000000000000000000000000000000000006530000000a0a0000000c5d3000004755f300000a008a000008000a0000000049",
            "75|58|000000000000000000000000006553000006b00a00045bc57900000c30a0000000a0c3000004d10800000000000000000000"
    };

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

            if(x == startX && y == startY){
                room.getItems().add(new HealthPotion(10));
                room.getItems().add(new HealthPotion(15));
                room.getItems().add(new HealthPotion(20));
            }

            if(room == null)
                continue;

            if(Math.random() > 0.5){
                if(Math.random() > 0.5){
                    room.addEntity(new Druid(20, 5));
                }else{
                    room.getItems().add(new HealthPotion(10));
                }
            }

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
