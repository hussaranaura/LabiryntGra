package stachugame.implementation.items;

import stachugame.api.entities.IEntity;
import stachugame.api.items.IItem;

public abstract class AbstractItem implements IItem {

	protected String name;
	
	AbstractItem(String name){
		this.name=name;
	}
	
	@Override
	public String getItemName() {
		return name;
	}

	@Override
	public abstract void useItem(IEntity user); 
}
