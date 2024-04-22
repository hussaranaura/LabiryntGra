package stachugame.implementation;

import stachugame.api.GameState;
import stachugame.api.IGame;
import stachugame.api.maps.IRoomMap;
import stachugame.implementation.entities.player.Player;

import java.io.OutputStream;
import java.io.PrintStream;

public class Game implements IGame {
	private Player player;
	private IRoomMap map;
	private int level;

	private PrintStream out;

	private GameState state;

	public Game(){
		out = System.out;
		state = GameState.NOT_INITIALIZED;
	}

	@Override
	public GameState getState(){
		return state;
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

	@Override
	public void processCommand(String cmd) {

	}
}