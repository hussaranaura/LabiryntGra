package stachugame.api.entities;

import java.util.Set;

import stachugame.api.items.IItem;

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
}
