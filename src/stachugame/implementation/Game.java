package stachugame.implementation;

import stachugame.api.GameState;
import stachugame.api.IGame;
import stachugame.api.entities.IEnemy;
import stachugame.api.entities.IEntity;
import stachugame.api.items.IItem;
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
import java.text.ParseException;
import java.util.Arrays;
import java.util.Set;

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
		for(IEntity entity : getCurrentMap().getEntityList()){
			entity.update();
		}
		if(state == GameState.EXPLORING || state == GameState.FIGHTING){
			IRoom room = player.getCurrentRoom();
			boolean isEnemyInRoom = false;
			for(IEntity entity : room.getEntities()){

				if(entity instanceof IEnemy){
					isEnemyInRoom = true;
					break;
				}
			}
			if(isEnemyInRoom){
				state = GameState.FIGHTING;
			}
		}

	}

	@Override
	public void processCommand(String cmd) {
		String[] args = cmd.toLowerCase().split(" ");

		IRoom room = player.getCurrentRoom();

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
		}else if(state == GameState.EXPLORING){
			boolean progressGameloopAfterCommand = true;

			switch(args[0]){
				case "pomoc":
					printOptions();
					progressGameloopAfterCommand = false;
					break;
				case "przedmioty":
					if(player.getItems().isEmpty()){
						out.println("Nie masz nic w ekwipunku\n");
						break;
					}
					break;
				case "podnies":
					if(room.getItems().isEmpty()) {
						out.println("W tym pokoju nic nie ma...\n\n");
						break;
					}
					try{
						int itemIndex = Integer.parseInt(args[1]);
						IItem[] items = room.getItems().toArray(new IItem[0]);
						room.pickUpItem(player, items[itemIndex]);
					}catch(NumberFormatException exception){
						out.println("ZŁE ID PRZEDMIOTU\n");
						progressGameloopAfterCommand = false;
					}
					break;
				case "rozglad":
					if(room.getItems().isEmpty()){
						out.println("W tym pokoju nic nie ma...\n\n");
					}else{
						int i = 1;
						for(IItem item : room.getItems()){
							out.println(String.format(" %d. %s", i, item.getItemName()));
						}
						out.println("\n\nNapisz PODNIES # aby podnieść przedmiot");
					}
					break;
				case "polnoc":
				case "poludnie":
				case "wschod":
				case "zachod":
					Direction dir = Direction.getDirByName(args[0]);
					player.move(dir);

					break;
				default:
					progressGameloopAfterCommand = false;
					printOptions();
			}

			if(progressGameloopAfterCommand){
				progressGameLoop();
			}


		}else if(state == GameState.FIGHTING){
			//Kod walki
		}
	}

	private void printOptions() {
		switch(state){
			case EXPLORING:
				out.println("   Możliwe działania:\n");
				out.println(
						" POMOC - lista możliwych działań\n" +
								" POLNOC - ruch do góry\n" +
								" WSCHOD - ruch w prawo\n" +
								" POLUDNIE - ruch do dołu\n" +
								" ZACHOD - ruch w lewo\n" +
								" ROZGLAD - obejrzyj się po pokoju\n" +
								" PRZEDMIOTY - zobacz swój ekwipunek\n"
				);
				out.println("\n");
				break;
			case FIGHTING:
				int i = 1;
				IRoom room = player.getCurrentRoom();
				for(IEntity enemy : room.getEntities()){
					if(enemy instanceof IEnemy){
						out.println(String.format(" %d. %s", i, enemy.getName()));
						i++;
					}
				}
				out.println("   Możliwe działania:\n");
				out.println(
						" ATAK # - atakuje postać\n" +
						" UZYJ # - uzywa przedmiot\n" +
						" PRZEDMIOTY - lista przedmiotów\n" +
						" INFO - info o graczu\n" +
						" POMOC - lista działań\n"
				);
				out.println("\n");
				break;
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
