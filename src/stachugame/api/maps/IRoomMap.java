package stachugame.api.maps;

import stachugame.api.entities.IEntity;

import java.awt.*;
import java.util.Set;

public interface IRoomMap {
    /**
     *  funkcja ma przejść po wszystkich postaciach na mapie i uruchomić funkcję update
     */
	void updateObjects();
	Set<IEntity> getEntityList();
	/**
	 * funkcja zwraca pokoje na mapie 
	 * @return tablica pokoji o rozmiarach 10 x 10
	 */
	IRoom[][] getRooms();

	/**
	 * Funkcja zwraca lokalizację startu poziomu
	 * @return Punkt startu poziomu
	 */
	Point getStartPos();

	/**
	 * Funkcja zwraca lokalizację końca poziomu
	 * @return Punkt do końca poziomu
	 */
	Point getFinishPos();
}
