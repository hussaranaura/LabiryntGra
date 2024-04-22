let SelectedPos = {
    x: -1,
    y: -1
}

let starterPos = {x: -1, y:-1};
let finishPos = {x: -1, y:-1};

function getMapPosition(event){
    return {x: Math.floor(event.offsetX/20), y: Math.floor(event.offsetY/20)};
}
function getPagePosition(x, y){
    return {x: Math.min(180, x*20), y: Math.min(180, y*20)}
}

const rooms = [
    [],
    [],
    [],
    [],
    [],
    [],
    [],
    [],
    [],
    []
]

function changeDoor(checkboxElement){
    let room = rooms[SelectedPos.x][SelectedPos.y];
    if(!room){
        return;
    }

    let id = parseInt(checkboxElement.name);

    let pos = getOffsetByDirId(id)
    pos.x += SelectedPos.x;
    pos.y += SelectedPos.y;

    if(doesRoomExist(pos)){
        getRoom(pos.x, pos.y).exits[getOppositeDir(id)] = checkboxElement.checked;
    }

    room.exits[id] = checkboxElement.checked;
    redraw();
}

function showCreator(isCreated){
    document.getElementById("create").style.display = (isCreated ? "none" : "initial");
    document.getElementById("remove").style.display = (!isCreated ? "none" : "initial");
}

function getOffsetByDirId(id){
    offset = {x: 0, y:0}
    switch(id){
        case 0:
            offset.y -= 1;
            break;
        case 1:
            offset.y += 1;
            break;
        case 2:
            offset.x += 1;
            break;
        case 3:
            offset.x -= 1;
    }
    return offset;
}

function getOppositeDir(dir){
    switch(dir){
        case 0:
            return 1;
        case 1:
            return 0;
        case 2: 
            return 3;
        case 3:
            return 2;
    }
}

function create(){
    let newRoom = new Room(false, false, false, false);
    rooms[SelectedPos.x][SelectedPos.y] = newRoom;


    for(let i = 0; i < 4; i++){
        let offset = getOffsetByDirId(i);
        let surroundingRoom = getRoom(SelectedPos.x+offset.x, SelectedPos.y+offset.y); //pokój obok

        if(!surroundingRoom){
            continue;
        }

        if(i==0 && surroundingRoom.exits[1]){ //Jeżeli pokoju z góry ma otwarte dolne drzwi
            newRoom.exits[i] = true; //otwórz górę
        }
        if(i==1 && surroundingRoom.exits[0]){ //Jeżeli pokoju z dołu ma otwarte górne drzwi
            newRoom.exits[i] = true; //otwórz dolne
        }
        if(i==2 && surroundingRoom.exits[3]){ //Jeżeli pokoju z prawej ma otwarte lewe drzwi
            newRoom.exits[i] = true; //otwórz prawo
        }
        if(i==3 && surroundingRoom.exits[2]){ //Jeżeli pokoju z góry ma otwarte dolne drzwi
            newRoom.exits[i] = true; //otwórz górę
        }
    }

    updateEditor();
    redraw();
}
function remove(){
    rooms[SelectedPos.x][SelectedPos.y] = null;
    updateEditor();
    redraw();
}

function doesRoomExist(pos){
    return (pos.x > -1 && pos.y > -1) && rooms[pos.x][pos.y];
}
function getRoom(x, y){
    let pos = {x:x, y:y};
    if (pos.x > -1 && pos.y > -1)
        return rooms[pos.x][pos.y];
    return null;
}

function closeRoomsOpenToVoid(){
    let closedSomeRooms = false;
    for(let x = 0; x < 10; x++){
        for(let y = 0; y < 10; y++){
            let room = rooms[x][y];

            if(!room){ //Jeżeli pokój nie istnieje
                continue; //Przejdź do nastepnego pokoju
            }

            for(let i = 0; i < 4; i++){
                if(room.exits[i]){
                    let pos = getOffsetByDirId(i);
                    pos.x += x;
                    pos.y += y;
                    if(!doesRoomExist(pos) || !getRoom(pos.x, pos.y).exits[getOppositeDir(i)]){
                        room.exits[i] = false;
                        closedSomeRooms = true;
                    }
                  
                }
            }
        }
    }
    return closedSomeRooms;
}

function save(){
    let errorText = "";
    let err = false;
    if(!doesRoomExist(starterPos)){
        errorText += "-Pozycja startująca się nie zgadza\n";
        err = true;
    }
    if(!doesRoomExist(finishPos)){
        errorText += "-Pozycja kończąca się nie zgadza\n"; 
        err = true;
    }

    if(err){
        alert("ERROR!\n\n"+errorText);
        return;
    }

    let closedSomeRooms = closeRoomsOpenToVoid()

    if(closedSomeRooms){
        errorText+="-Zamknięto drzwi otwarte do nicości\n";
    }

    let mapCode = starterPos.x + ""+starterPos.y + "|";
    mapCode += finishPos.x + ""+finishPos.y + "|";

    let removedClosedRooms = false;

    for(let x = 0; x < 10; x++){
        for(let y = 0; y < 10; y++){
            let room = rooms[x][y];

            if(!room){ //Jeżeli pokój nie istnieje
                mapCode += 0; //Ustaw wszystkie drzwi na zamknięte
                continue; //Przejdź do nastepnego pokoju
            }

            let mask = 0;
            for(let i = 0; i < 4; i++){
                if(room.exits[i]){
                    if(i == 0){
                        mask+=1; //Jeżeli góra otwarta, dodaj bitmaskę 1
                    }
                    if(i == 2){
                        mask+=2; //Jeżeli prawo otwarte, dodaj bitmaskę 2
                    }
                    if(i == 3){
                        mask+=8; //Jeżeli lewo otwarte, dodaj bitmaskę 8
                    }
                    if(i == 1){
                        mask+=4; //Jeżeli dół otwarty, dodaj bitmaskę 4
                    }
                }
            }

            if(mask == 0){
                rooms[x][y] = null;
                removedClosedRooms = true;
            }

            //Usuwanie oznaczonych punktów, wrazie usunięcia ich pokoji
            if(!doesRoomExist(starterPos)){
                starterPos.x = -1;
                starterPos.y = -1;
            }
            if(!doesRoomExist(finishPos)){
                finishPos.x = -1;
                finishPos.y = -1;
            }
            mapCode += mask.toString(16);

        }
    }
    if(removedClosedRooms){
        errorText+="-Usunięto zamknięte pokoje";
    }

    if(removedClosedRooms || closedSomeRooms){
        alert("INFO:\n\n"+errorText);
        redraw();
        return;
    }
    navigator.clipboard.writeText(mapCode);
    alert("Kod mapy (skopiowany do schowka):\n"+mapCode);
}

function setFinish(){
    if(starterPos.x == SelectedPos.x && starterPos.y == SelectedPos.y){
        starterPos.x = -1
        starterPos.y = -1
    }
    finishPos.x = SelectedPos.x;
    finishPos.y = SelectedPos.y;
    redraw();
}
function setStart(){
    if(finishPos.x == SelectedPos.x && finishPos.y == SelectedPos.y){
        finishPos.x = -1
        finishPos.y = -1
    }
    starterPos.x = SelectedPos.x;
    starterPos.y = SelectedPos.y;
    redraw();
}

function updateEditor(){
    document.getElementById("room_number").textContent = `Pokój{x: ${SelectedPos.x}, y: ${SelectedPos.y}}`

    let room = rooms[SelectedPos.x][SelectedPos.y];

    if(room){
        showCreator(true);
        document.getElementById("configurator").style.display = "initial";
        let checkmarks = document.getElementsByClassName("doorCheck");

        for(let i = 0; i < checkmarks.length; i++){
            checkmarks[i].checked = room.exits[checkmarks[i].name];
        }
    }else{
        document.getElementById("configurator").style.display = "none";
        showCreator(false);

    }
}

