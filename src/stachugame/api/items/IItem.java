package stachugame.api.items;

import stachugame.api.entities.IEntity;

public interface IItem {
	/**
	 * funkcja która zwraca nazwę przedmiotu
	 * @return nazwa przedmiotu
	 */
	String getItemName();
	/**
	 * funkcja która zawiera logikę użycia przedmiotu
	 * @param user postac która używa przedmiot
	 */
	void useItem(IEntity user);
}
