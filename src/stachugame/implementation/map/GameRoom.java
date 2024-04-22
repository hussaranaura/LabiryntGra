package stachugame.implementation.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import stachugame.api.entities.IEntity;
import stachugame.api.items.IItem;
import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;

public class GameRoom implements IRoom {
	private HashSet<IEntity> entities;
	private HashSet<IItem> items;
	private final HashMap<Direction, Boolean> exits;
	public GameRoom(boolean northOpen, boolean eastOpen, boolean southOpen, boolean westOpen) {
		this.exits = new HashMap<>();
		this.exits.put(Direction.NORTH, northOpen);
		this.exits.put(Direction.EAST, eastOpen);
		this.exits.put(Direction.SOUTH, southOpen);
		this.exits.put(Direction.WEST, westOpen);
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
	public boolean isExitOpen(Direction dir) {
		return this.exits.getOrDefault(dir, false);
	}
	@Override
	public boolean wasFound() {
		return wasFound;
	}
	@Override
	public void setFound() {
		if(wasFound == true) wasFound = false;
		else wasFound = true;
	}
}
