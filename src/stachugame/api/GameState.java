package stachugame.api;

/**
 * Enumerator GameState<br>
 * Zawiera wszystkie możliwe stany, w których może znalezć się gra
 */
public enum GameState {
    NOT_INITIALIZED,
    INITIALIZING,
    EXPLORING,
    FIGHTING,
    GAME_OVER,
    GAME_WON;

}
