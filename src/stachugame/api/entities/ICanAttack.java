package stachugame.api.entities;

public interface ICanAttack {
    /**
     * Atakuje podaną postac
     * @param entity postać do zaatakowania
     */
    void attack(IEntity entity);

    /**
     * Funkcja zwraca ilość obrażeń, ile przeciwnik zadaje
     * @return ilość obrażeń
     */
    int getDamage();
}
