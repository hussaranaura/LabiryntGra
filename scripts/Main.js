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
    room.exits[checkboxElement.name] = checkboxElement.checked;
    redraw();
}

function showCreator(isCreated){
    document.getElementById("create").style.display = (isCreated ? "none" : "initial");
    document.getElementById("remove").style.display = (!isCreated ? "none" : "initial");
}

function create(){
    rooms[SelectedPos.x][SelectedPos.y] = new Room(false, false, false, false);
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
                    if(i == 0){ //Jeżeli góra otwarta
                        if(!doesRoomExist({x: x, y: y-1})){
                            room.exits[i] = false;
                            closedSomeRooms = true;
                        }
                    }
                    if(i == 2){ //Jeżeli prawo otwarte
                        if(!doesRoomExist({x: x+1, y: y})){
                            room.exits[i] = false;
                            closedSomeRooms = true;
                        }
                    }
                    if(i == 3){ //Jeżeli lewo otwarte
                        if(!doesRoomExist({x: x-1, y: y})){
                            room.exits[i] = false;
                            closedSomeRooms = true;
                        } 
                    }
                    if(i == 1){ //Jeżeli dół otwarty
                        if(!doesRoomExist({x: x, y: y+1})){
                            room.exits[i] = false;
                            closedSomeRooms = true;
                        } 
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
    
    alert("Kod mapy:\n"+mapCode);
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

