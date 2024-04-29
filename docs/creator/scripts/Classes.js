class Room {
    exits = [false, false, false, false];

    constructor(north, east, south, west){
        this.exits[0] = north;
        this.exits[1] = east;
        this.exits[2] = south;
        this.exits[3] = west;
    }

    setExit(index, open){
        this.exits[index] = !!open;
    }


}
