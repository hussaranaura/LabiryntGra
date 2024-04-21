let SelectedPos = {
    x: -1,
    y: -1
}

let starterPos = {x: 3, y:1};
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

