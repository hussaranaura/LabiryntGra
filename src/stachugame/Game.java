package stachugame;

import stachugame.api.maps.IGameMap;
import stachugame.implementation.entities.Player;

public class Game {
	private Player player;
	private IGameMap map;
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
	 * Funkcja ³aduje nastêpny poziom do gry
	 */
	public void loadNextLevel() {
		
	}
	/**
	 * Funkcja rozpoczyna gre
	 */
	public void init() {
		
	}
	/**
	 *  Funkcja pozwala grze toczyæ siê krok po kroku
	 */
	private void progressGameLoop() {
		
	}
}
