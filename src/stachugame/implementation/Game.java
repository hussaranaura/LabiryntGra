package stachugame.implementation;

import stachugame.api.IGame;
import stachugame.api.maps.IRoomMap;
import stachugame.implementation.entities.player.Player;

import java.io.OutputStream;
import java.io.PrintStream;

public class Game implements IGame {
	private Player player;
	private IRoomMap map;
	private int level;
	private static Game instance;

	private PrintStream out;

	public Game(){
		out = System.out;
	}

	@Override
	public void setOut(OutputStream os) {
		this.out = new PrintStream(os);
	}

	@Override
	public void loadNextLevel() {

	}

	@Override
	public void init() {

	}

	@Override
	public void progressGameLoop() {

	}
}
