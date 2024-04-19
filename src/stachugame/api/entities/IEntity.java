package stachugame.api.entities;

import java.util.Set;

import stachugame.api.items.IItem;

public interface IEntity {
	/**
	 * Metoda zwracajaca aktualn¹ ilosc zycia
	 * @return ilosc zycia
	 */
	int GetHealth ();
	/**
	 * Metoda zwracajaca maksymalna ilosc zycia
	 * @return maksymalna ilosc zycia
	 */
	int GetMaxHealth();
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
