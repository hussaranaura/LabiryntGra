package stachugame.implementation.map;

import java.util.*;

import stachugame.api.entities.IEntity;
import stachugame.api.items.IItem;
import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;
import stachugame.api.maps.IRoomMap;

/**
 * Klasa przedstawiająca pokój w grze
 */
public class GameRoom implements IRoom {
	private LinkedHashSet<IEntity> entities;
	private LinkedHashSet<IItem> items;
	private final HashMap<Direction, IRoom> exits;
	private final IRoomMap parent;
	private final boolean isFinal;
	public GameRoom(IRoomMap parent, boolean isFinal) {
		exits = new HashMap<>();
		entities = new LinkedHashSet<>();
		items = new LinkedHashSet<>();
		this.parent = parent;
		this.isFinal = isFinal;
	}
	private boolean wasFound = false;
	@Override
	public Set<IEntity> getEntities() {
		// TODO Auto-generated method stub
		return entities;
	}
	@Override
	public Set<IItem> getItems() {
		return items;
	}
	@Override
	public boolean isNextRoomOpen(Direction dir) {
		return this.getNextRoom(dir) != null;
	}

	public Map<Direction, IRoom> getExits(){
		return this.exits;
	}

	@Override
	public IRoom getNextRoom(Direction dir) {
		return this.exits.getOrDefault(dir, null);
	}

	@Override
	public boolean wasFound() {
		return wasFound;
	}
	@Override
	public void setFound() {
		wasFound = true;
	}

	@Override
	public boolean isFinalRoom() {
		return isFinal;
	}

	@Override
	public void pickUpItem(IEntity entity, IItem item) {
		entity.getItems().add(item);
		items.remove(item);
	}

	@Override
	public IRoomMap getParent() {
		return parent;
	}
	@Override
	public void moveEntity(IEntity entity, Direction dir) {
		if(entities.contains(entity) ==false)
			return;
		IRoom nextRoom=exits.get(dir);
		if(nextRoom==null)
			return;
		entities.remove(entity);
		nextRoom.addEntity(entity);
	}
	@Override
	public void addEntity(IEntity entity) {
		parent.getEntityList().add(entity);
		entities.add(entity);
		entity.setCurrentRoom(this);
	}
	@Override
	public void removeEntity(IEntity entity) {
		parent.getEntityList().remove(entity);
		entities.remove(entity);
		entity.setCurrentRoom(null);
	}
}
