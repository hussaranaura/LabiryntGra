package stachugame.api;

import stachugame.implementation.Game;

import java.io.OutputStream;

public interface IGame {
    IGame instance = new Game();

    static IGame getInstance() {
        return instance;
    }

    /**
     * Zmienia wyjście tekstu na podany poniżej
     * @param os OutputStream
     */
    void setOut(OutputStream os);

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
}
