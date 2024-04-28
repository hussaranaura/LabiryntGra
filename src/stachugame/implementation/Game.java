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
	private int dialogCount = 0;

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
        out.println("Po heroicznej bitwie z Czarnoksiężnikiem z Grodzkiej Doliny, słynny mag i mędrzec, Stachu Jones, powraca do swojego skromnego domu, tylko po to, aby odkryć, że jego zbiory ulubionego trunku - Jaboli - zostały skradzione przez plemię Druidów. " +
				"\n" +
				"\n" +
				"Zmagając się z wyczerpaniem i brakiem many, Stachu dowiaduje się o legendarnym artefakcie - Nieskończonej Skrzynce Jaboli, która znajduje się w tajemniczym barze \"Gut\" w miejscowości Maków." +
				"\n");

		out.println("\n\n>> Czy jesteś gotów przeżyć cała historię zmagań naszego bohatera?\n");
		state = GameState.INITIALIZING;
	}

	@Override
	public void progressGameLoop() {

	}

	@Override
	public void processCommand(String cmd) {
		if(state == GameState.INITIALIZING){
			if(dialogCount == 0) {
				out.println("\n\n\n\n\n\n   [W piwniczce baru \"Gut\"]" +
						"\n" +
						"\nStachu Jones wkradając się do baru przy osłonie nocy nie tracił czasu na odszukanie artefaktu." +
						"\nSzybko znalazł swą drogę do piwniczki i wykopując drzwi wstąpił do niej." +
						"\n" +
						"\n..." +
						"\n"
				);
			}else if(dialogCount == 1){
				out.println("\nNagle, został teleportowany do wielkiego Labiryntu." +
						"\nOkazało się, że Piwniczka zostało zaklęta przez Magów jeszcze z przed czasów Jones'a - " +
						"okazuje się, że oni też lubili raczyć się dobrymi trunkami, więc zaczarowali Piwniczkę by strzec Nieskończoną Skrzynkę przed innymi." +
						"\n" +
						"\nW normalnych warunkach, Stanisław nie padł by ofiarą takiego prostego czaru, lecz po bitwie z Czarnoksiężnikiem całkowicie wyczerpał swą Manę." +
						"\nJedyne co mu pozostało, to przedostać się przez Labirynt i odnaleźć artefakt by odzyskać moc Siedmiu Księżyców..." +
						"\n" +
						"\n> [ROZPOCZNIJ GRĘ]\n\n\n"
				);
			}
			else{
				loadNextLevel();
				state = GameState.EXPLORING;
				printOptions();
			}

			dialogCount++;

			//state = GameState.EXPLORING;
		}else if(state == GameState.EXPLORING){
			//Kod poruszania się po mapie
		}else if(state == GameState.FIGHTING){
			//Kod walki
		}
	}

	private void printOptions() {

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
