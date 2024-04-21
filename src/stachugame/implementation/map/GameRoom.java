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
	private HashMap<Direction, Boolean> exits;
	public GameRoom(boolean northOpen, boolean eastOpen, boolean southOpen, boolean westOpen) {
		this.exits = new HashMap<>();
	}
	private boolean wasFound = false;
	@Override
	public Set<IEntity> getEntities() {
		// TODO Auto-generated method stub
		return entities;
	}
	@Override
	public Set<IItem> getItems() {
		// TODO Auto-generated method stub
		return items;
	}
	@Override
	public boolean isExitOpen(Direction dir) {
		// TODO Auto-generated method stub
		return this.exits.getOrDefault(dir, false);
	}
	@Override
	public boolean wasFound() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setFound() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void openExit(Direction dir) {
		// TODO Auto-generated method stub
		
	}
}
