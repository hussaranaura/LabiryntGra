package stachugame.implementation.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import stachugame.api.entities.IEntity;
import stachugame.api.items.IItem;
import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;

public class GameRoom implements IRoom {
	private HashSet<IEntity> entities;
	private HashSet<IItem> items;
	private final HashMap<Direction, IRoom> exits;
	public GameRoom() {
		exits = new HashMap<>();
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
		wasFound = !wasFound;
	}
}