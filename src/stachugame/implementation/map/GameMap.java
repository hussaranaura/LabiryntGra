package stachugame.implementation.map;

import java.util.HashSet;

import stachugame.api.entities.IEntity;
import stachugame.api.maps.IRoom;

class GameMap {
	private IRoom[][] rooms = new IRoom[10][10];
	private HashSet<IEntity> entities;
}
