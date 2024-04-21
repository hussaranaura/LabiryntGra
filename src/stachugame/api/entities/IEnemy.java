package stachugame.api.entities;

public interface IEnemy {
	/**
	 * Metoda (postac atakuje druga postac podana w parametrach)
	 * @param entity - postac ktora ma byc atakowana
	 */
	void attack (IEntity entity);
}
