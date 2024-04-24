package stachugame.api.entities;

import java.util.Set;

import stachugame.api.items.IItem;
import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;

public interface IEntity {
	/**
	 * Metoda zwracajaca aktualn� ilosc zycia
	 * @return ilosc zycia
	 */
	int getHealth ();
	/**
	 * Metoda zwracajaca maksymalna ilosc zycia
	 * @return maksymalna ilosc zycia
	 */
	int getMaxHealth();
	/**
	 * Metoda zwracajaca ekwipunek postaci
	 * @return (Set) itemy
	 */
	Set<IItem> getItems();
	/**
	 * Metoda aktualizujaca postac (poruszanie sie, zmiana ilosci zycia)
	 */
	void update();
	/**
	 * Metoda zwracajaca pokoj w ktorym obecnie znajduje sie postac
	 * @return - Pokoj w ktorym jest postac
	 */
	IRoom getCurrentRoom();
	/**
	 * Metoda zwracajaca imie postaci
	 * @return - Imie postaci
	 */
	String getName();
	/**
	 * Metoda probojaca ruszyc postac w danym kierunku
	 * @param dir - kierunek w ktorym ma ruszyc sie postac
	 */
	void move(Direction dir);
	/**
	 * Metoda przenoszaca postac do wybranego pokoju
	 * @param room - wybrany pokoj do ktorego ma byc przeniesiona postac
	 */
	void setCurrentRoom(IRoom room);
}
