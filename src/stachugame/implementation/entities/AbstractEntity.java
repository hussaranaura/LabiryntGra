package stachugame.implementation.entities;

import java.util.HashSet;
import java.util.Set;

import stachugame.api.entities.IEntity;
import stachugame.api.maps.IRoom;
import stachugame.api.items.IItem;

public abstract class AbstractEntity implements IEntity {
	protected int maxHealth;
	protected int currentHealth;
	protected IRoom currentRoom;
	protected HashSet<IItem> items;

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

}
