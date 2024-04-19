package stachugame;

import stachugame.api.maps.IRoomMap;
import stachugame.implementation.entities.player.Player;

public class Game {
	private Player player;
	private IRoomMap map;
	private int level;
	private static Game instance;

	private Game(){

	}

	public static Game getInstance(){
	  if (instance == null)
	    instance = new Game();

	  return instance;
	}
	/**
	 * Funkcja �aduje nast�pny poziom do gry
	 */
	public void loadNextLevel() {
		
	}
	/**
	 * Funkcja rozpoczyna gre
	 */
	public void init() {
		
	}
	/**
	 *  Funkcja pozwala grze toczy� si� krok po kroku
	 */
	private void progressGameLoop() {
		
	}
}
