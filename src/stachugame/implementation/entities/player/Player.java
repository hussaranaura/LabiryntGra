package stachugame.implementation.entities.player;

import stachugame.api.maps.IRoom;
import stachugame.implementation.entities.AbstractEntity;

public class Player extends AbstractEntity {

	public Player(String name, int maxHealth) {
		super(name, maxHealth);
	}

	@Override
	public void update() {

	}

	@Override
	public void setCurrentRoom(IRoom room){
		super.setCurrentRoom(room);
		room.setFound();
	}

}
