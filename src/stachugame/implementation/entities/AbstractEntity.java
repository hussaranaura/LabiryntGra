package stachugame.implementation.entities;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import stachugame.api.entities.IEntity;
import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;
import stachugame.api.items.IItem;

public abstract class AbstractEntity implements IEntity {
	protected int maxHealth;
	protected int currentHealth;
	protected IRoom currentRoom;
	protected LinkedHashSet<IItem> items;
	protected String name;

	public AbstractEntity(String name, int maxHealth) {
		this.name=name;
		this.maxHealth=maxHealth;
		this.currentHealth=maxHealth;
		this.items = new LinkedHashSet<>();
	}
	@Override
	public int getMaxHealth(){
		return maxHealth;
	}
	@Override
	public int getHealth(){
		return currentHealth;
	}
	@Override
	public Set<IItem> getItems(){
		return items;
	}

	@Override
	public abstract void update();
	public IRoom getCurrentRoom() {
		return currentRoom;
	}
	public String getName() {
		return name;
	}
	public void move(Direction dir) {
		currentRoom.moveEntity(this, dir);
	}
	public void setCurrentRoom(IRoom room) {
		if(room == currentRoom)
			return;
		if(room == null) {
			IRoom oldRoom = currentRoom;
			currentRoom = null;
			oldRoom.removeEntity(this);
			return;
		}
		if(currentRoom != null){
			IRoom oldRoom = currentRoom;
			currentRoom = null;
			oldRoom.removeEntity(this);
		}
		currentRoom = room;
		room.addEntity(this);

	}
	public void kill() {
		currentRoom.removeEntity(this);
	}
}
