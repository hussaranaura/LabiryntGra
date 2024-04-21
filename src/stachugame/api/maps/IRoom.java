package stachugame.api.maps;

import java.util.Set;

import stachugame.api.entities.IEntity;
import stachugame.api.items.IItem;

public interface IRoom {
	/**
	 * funkcja zwraca wszystkie postacie w pokoju
	 * @return zbiór postaci w pokoju
	 */
	Set<IEntity> getEntities();
	/**
	 * funkcja zwraca wszystkie przedmioty w pokoju
	 * @return zbiór przedmiotów w pokoju
	 */
	Set<IItem> getItems();
	/**
	 * funkcja zwraca informacje czy dane wyjście jest otwarte
	 * @return czy drzwi w danym kierunku są otwarte
	 */
	boolean isExitOpen(Direction dir);
}
