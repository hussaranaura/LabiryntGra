package stachugame.implementation.entities.player;

import stachugame.api.entities.ICanAttack;
import stachugame.api.entities.IEntity;
import stachugame.api.maps.IRoom;
import stachugame.implementation.entities.AbstractEntity;

public class Player extends AbstractEntity implements ICanAttack {

	public Player(String name, int maxHealth) {
		super(name, maxHealth);
	}

	@Override
	public void update() {

	}

	@Override
	public void setCurrentRoom(IRoom room){
		super.setCurrentRoom(room);
		if(room != null)
			room.setFound();
	}

	@Override
	public void attack(IEntity entity) {
		entity.setHealth(entity.getHealth()-10);
	}
}
