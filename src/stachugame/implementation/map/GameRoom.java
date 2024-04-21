package stachugame.implementation.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import stachugame.api.entities.IEntity;
import stachugame.api.items.IItem;
import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;

public class GameRoom implements IRoom {
	public GameRoom(boolean northOpen, boolean eastOpen, boolean southOpen, boolean westOpen) {
		
	}
	private HashSet<IEntity> entities;
	private HashSet<IItem> items;
	private HashMap<Direction, Boolean> exits;
	private boolean wasFound = false;
	@Override
	public Set<IEntity> getEntities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<IItem> getItems() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isExitOpen(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}
}
