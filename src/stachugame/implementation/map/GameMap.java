package stachugame.implementation.map;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import stachugame.api.entities.IEntity;
import stachugame.api.maps.IRoom;
import stachugame.api.maps.IRoomMap;

/**
 * Klasa przedstawiająca zbiór pokoji w grze - mapę
 */
public class GameMap implements IRoomMap {
	private IRoom[][] rooms = new IRoom[10][10];
	private HashSet<IEntity> entities;

	private final Point startPos;
	private final Point finishPos;

	public GameMap(int startX, int startY, int finishX, int finishY){
		entities = new HashSet<>();

		startPos = new Point(startX, startY);
		finishPos = new Point(finishX, finishY);
	}

	@Override
	public void updateObjects() {

	}

	@Override
	public Set<IEntity> getEntityList() {
		return entities;
	}

	@Override
	public IRoom[][] getRooms() {
		return rooms;
	}

	@Override
	public Point getStartPos() {
		return startPos;
	}

	@Override
	public Point getFinishPos() {
		return finishPos;
	}
}
