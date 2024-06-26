package stachugame.api;

import stachugame.api.entities.IEntity;
import stachugame.api.maps.IRoomMap;
import stachugame.implementation.Game;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Interfejs IGame, <br>
 * Deklaruje funkcje głównej klasy gry
 */
public interface IGame {
    IGame instance = new Game();

    static IGame getInstance() {
        return instance;
    }

    GameState getState();
    /**
     *  funkcja zwraca obiekt obecnej mapy w grze
     * @return obiekt mapy 
     */
    IRoomMap getCurrentMap();

    /**
     * Zmienia wyjście tekstu na podany poniżej
     * @param os OutputStream
     */
    void setOut(OutputStream os) throws UnsupportedEncodingException;

    /**
     * Funkcja �aduje nast�pny poziom do gry
     */
    void loadNextLevel();
    /**
     * Funkcja rozpoczyna gre
     */
    void init();

    /**
     *  Funkcja pozwala grze toczy� si� krok po kroku
     */
    void progressGameLoop();

    /**
     * Przetwarza polecenie przesłane do gry
     * @param cmd polecenie jako String
     */
    void processCommand(String cmd);

    /**
     * @return Obiekt gracza
     */
    IEntity getPlayer();
}
