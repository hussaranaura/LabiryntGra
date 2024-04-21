package stachugame.api.maps;

public interface IRoomMap {
    /**
     *  funkcja ma przejść po wszystkich postaciach na mapie i uruchomić funkcję update
     */
	void udpateObjects();
	/**
	 * funkcja zwraca pokoje na mapie 
	 * @return tablica pokoji o rozmiarach 10 x 10
	 */
	IRoom[][] getRooms();
}
