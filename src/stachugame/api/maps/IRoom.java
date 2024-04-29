package stachugame.api.maps;

import java.util.Set;

import stachugame.api.entities.IEntity;
import stachugame.api.items.IItem;
import stachugame.implementation.entities.player.Player;

/**
 * Interfejs IRoom, <br>
 * Deklaruje funkcje klasy pokoju
 */
public interface IRoom {
	/**
	 * funkcja do poruszania się między pokojami
	 * @param entity postac do poruszenia
	 * @param dir kierunek do poruszenia
	 */
	void moveEntity(IEntity entity, Direction dir);
	/**
	 * funkcja do dodania postaci
	 * @param entity postac do dodania
	 */
	void addEntity(IEntity entity);
	/**
	 * funkcja do usuniecia postaci
	 * @param entity postac do usuniecia
	 */
	void removeEntity(IEntity entity);
	/**
	 * funkcja zwraca obiekt mapy która przechowuje ten pokój
	 * @return obiekt mapy 
	 */
	IRoomMap getParent();
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
	boolean isNextRoomOpen(Direction dir);

	/**
	 * Zwraca sąsiedni pokój znajdujący się w podanym kierunku
	 * @param dir Kierunek wyjścia
	 * @return Sąsiedni pokój
	 */
	IRoom getNextRoom(Direction dir);

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
	 * Checks if this room is the end of the map
	 * @return true or false
	 */
	boolean isFinalRoom();

	/**
	 * Przenosi przedmiot z listy przedmiotów w pokoju do ekwipunku postaci
	 * @param entity Postac która ma podnieść przedmiot
	 * @param item przedmiot do podniesienia
	 */
	void pickUpItem(IEntity entity, IItem item);
}
