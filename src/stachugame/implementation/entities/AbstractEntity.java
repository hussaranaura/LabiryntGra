package stachugame.implementation.entities;

import java.util.Set;

import stachugame.api.entities.IEntity;
import stachugame.api.maps.IRoom;
import stachugame.api.items.IItem;

public abstract class AbstractEntity implements IEntity {
	protected int maxHealth;
	protected int currentHealth;
	protected IRoom currentRoom;
	protected Set<IItem> items;
}
