package stachugame.implementation;

import stachugame.api.GameState;
import stachugame.api.IGame;
import stachugame.api.entities.IEntity;
import stachugame.api.maps.Direction;
import stachugame.api.maps.IRoom;
import stachugame.api.maps.IRoomMap;
import stachugame.implementation.entities.player.Player;
import stachugame.implementation.util.MapLoaderUtil;

import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Game implements IGame {
	private Player player;
	private IRoomMap map;
	private int level = -1;

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
	public void setOut(OutputStream os) throws UnsupportedEncodingException {
		this.out = new PrintStream(os);
	}

	@Override
	public void loadNextLevel() {
		level++;
		map = MapLoaderUtil.getMap(level);

		IRoom[][] rooms = map.getRooms();
		Point pos = map.getStartPos();
		player.setCurrentRoom(rooms[pos.x][pos.y]);
	}

	@Override
	public void init() {
		player = new Player("Stachu Jones", 6969);

		//@TODO Zmień tekst fabularny na rozpoczecie gry.
        out.println("Potężny mag, znany jako Stachu Jones, pewnego dnia........\n" +
				"\n.........." +
				"\n........." +
				"\n\nNACIŚNIJ DOWOLNY PRZYCISK\n\n");

		state = GameState.INITIALIZING;
	}

	@Override
	public void progressGameLoop() {

	}

	@Override
	public void processCommand(String cmd) {
		if(state == GameState.INITIALIZING){
			loadNextLevel();
			state = GameState.EXPLORING;
		}else if(state == GameState.EXPLORING){
			//Kod poruszania się po mapie
		}else if(state == GameState.FIGHTING){
			//Kod walki
		}
	}

	@Override
	public IEntity getPlayer() {
		return player;
	}

	@Override
	public IRoomMap getCurrentMap() {
		// TODO Auto-generated method stub
		return map;
	}
}
