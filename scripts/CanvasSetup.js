const canvas = document.getElementById('map_canvas');
const ctx = canvas.getContext("2d");

canvas.addEventListener("click", (event) =>{
    //console.log(getPosition(event))
    SelectedPos = getMapPosition(event)

    updateEditor();

    redraw();
});
canvas.addEventListener("contextmenu", (event) => {
    event.preventDefault();
    finishPos = getMapPosition(event);
    console.log(finishPos);
    rooms[finishPos.x][finishPos.y] = new Room(false, false, true, false);
    return false;
})
background.onload = redraw;


function drawRooms(){
    for(let x = 0; x < 10; x++){
        for(let y = 0; y < 10; y++){
            let room = rooms[x][y];
            
            if( !room ){
                continue;
            }
            let doorSet = [
                door,
                door_open
            ];
            if(starterPos.x == x && starterPos.y == y){
                ctx.drawImage(starter, x*20, y*20);
            }
            if(finishPos.x == x && finishPos.y == y){
                doorSet = [
                    finish_door,
                    finish_door_open
                ]
            }

            for(let e = 0; e < 4; e++){
                ctx.drawImage(doorSet[room.exits[e]+0][e], x*20, y*20);
            }

        }
    }
}

function redraw(){

    canvas.width = background.width;
    canvas.height = background.height

    ctx.reset()

    ctx.drawImage(background, 0, 0)
    drawRooms();

    if(SelectedPos.x != -1 && SelectedPos.y != -1){
        ctx.drawImage(selection, SelectedPos.x*20, SelectedPos.y*20);
        updateEditor()
    }
}



