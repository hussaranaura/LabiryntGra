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
	/**
	 * Metoda zwracajaca informacje o tym czy pokoj zostal juz odkryty
	 * @return 	false - jesli nei zostal odkryty
	 * 			true - jesli zostal odkryty
	 */
	boolean wasFound();
	/**
	 * Metoda ustawiajaca informacje o tym ze pokoj zostal znaleziony
	 */
	void setFound();
	/**
	 * Metoda otwierajaca wejscie w danym kierunku
	 * @param dir - kierunek do ktorego ma byc otwarte wejscie
	 */
	void openExit(Direction dir);
}
