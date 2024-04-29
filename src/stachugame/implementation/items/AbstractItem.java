package stachugame.implementation.items;

import stachugame.api.entities.IEntity;
import stachugame.api.items.IItem;
import stachugame.implementation.entities.AbstractEntity;

/**
 * Klasa bazowa wszystkich przedmiotów
 */
public abstract class AbstractItem implements IItem {

	protected String name;

	public AbstractItem(String name){
		this.name=name;
	}

	public abstract String getItemName();

	@Override
	public abstract void useItem(IEntity user); 
}
