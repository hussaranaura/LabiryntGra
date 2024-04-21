const background = new Image();
background.src = "./resources/grid.png";
const selection = new Image();
selection.src = "./resources/player_marker.png"

const starter = new Image();
starter.src = "./resources/starter.png"

const door = [
    new Image(),
    new Image(),
    new Image(),
    new Image()
]

for(let i = 0; i < 4; i++){
    door[i].src = "./resources/wall_"+i+".png";
}

const door_open = [
    new Image(),
    new Image(),
    new Image(),
    new Image()
]
for(let i = 0; i < 4; i++){
    door_open[i].src = "./resources/wall_"+i+"_open.png";
}

const finish_door = [
    new Image(),
    new Image(),
    new Image(),
    new Image()
]

for(let i = 0; i < 4; i++){
    finish_door[i].src = "./resources/wall_"+i+"_red.png";
}

const finish_door_open = [
    new Image(),
    new Image(),
    new Image(),
    new Image()
]

for(let i = 0; i < 4; i++){
    finish_door_open[i].src = "./resources/wall_"+i+"_open_red.png";
}