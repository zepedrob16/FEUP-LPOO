package dkeep.logic;

import java.util.Random;
import java.util.*;

public class GameState {
	
	/* STATE MACHINE */	
	
	public enum Level {
		DUNGEON, OGRE
	}
	public enum State {
		VICTORY, DEFEAT, RUNNING
	}
	public enum Event {
		VALID_MOVE, INVALID_MOVE, HERO_CAUGHT, NEXT_LEVEL, LEVEL_COMPLETED, KEEP_COMPLETED
	}
	private Level level;
	private State state;
	
	
	/* GAME ENTITIES */
	
	private GameMap map;
	public Hero hero;
	public Guard guard;
	public int keyX, keyY;
	public ArrayList<Ogre> ogres = new ArrayList<Ogre>();  //Ogres spawned on the map.
	
	public String message;
	
	
	/* METHODS */
	
	public GameState() {
		this.level = Level.DUNGEON;
		this.state = State.RUNNING;
	}
	
	public GameState(GameMap map) {
		super();
		this.map = map;
		this.state = State.RUNNING;
		spawnEntities();
	}
	
	public void stateMachine(Event evt){
		
		if (this.state == State.RUNNING){
			if (evt == Event.VALID_MOVE){
				this.message = "";
				updateEntities();
			}
			else if (evt == Event.INVALID_MOVE){
				this.message = "Invalid move!";
				System.out.println("\nInvalid move!\n");
			}
			else if (evt == Event.HERO_CAUGHT){
				this.message = "GAME OVER!";
				System.out.println("\n\nGAME OVER! You got caught, doofus!\n");
				this.state = State.DEFEAT;
			}
			else if (evt == Event.LEVEL_COMPLETED){
				this.message = "Level complete!";
				this.state = State.VICTORY;
				System.out.println("Level complete!\n");
			}
		}
		else if (this.state == State.VICTORY){
			if (this.level == Level.DUNGEON){
				this.level = Level.OGRE;
				OgreMap ogreMap = new OgreMap();
				setGameMap(ogreMap);
				spawnOgres();
				spawnEntities();
				guard = null;
				drawMap();
				this.state = State.RUNNING;
			}
			else if (this.level == Level.OGRE){
				System.out.println("\n\nYOU WIN! Dungeon Keep cleared.\n");
			}
		}
		return;
	}
	
	public void updateEntities(){
		
		if (level == Level.DUNGEON){
			guard.moveGuard();
		}
		else if (level == Level.OGRE){
			moveEveryOgre();
			hero.moveHeroClub(this);
		}
		drawMap();
		return;
	}
	
	public void spawnEntities(){
		char[][] thisMap = map.getMap();
		
		for (int i = 0; i < thisMap.length; i++){
			for (int j = 0; j < thisMap[i].length; j++){
				if (thisMap[i][j] == 'H'){
					thisMap[i][j] = ' ';
					spawnHero(i, j);
				}
				else if (thisMap[i][j] == 'G'){
					thisMap[i][j] = ' ';
					spawnGuard(i, j);
				}
				else if (thisMap[i][j] == 'k'){
					thisMap[i][j] = ' ';
					this.keyX = i;	this.keyY = j;
				}
				else if (thisMap[i][j] == 'O'){
					thisMap[i][j] = ' ';
					Ogre ogre = new Ogre(i, j);
					this.ogres.add(ogre);
				}
			}
		}
		this.map.setMap(thisMap);
	}
	
	public boolean processMove(String move){
		int m = hero.moveHero(this.map, move);
		
		// Se o herói está na mesma posição da chave/alavanca, abre portas.
		if (this.hero.getX() == this.keyX && this.hero.getY() == this.keyY){
			if (this.map instanceof OgreMap){
				this.keyX = 10;
				this.keyY = 10;
				this.hero.setSymbol('K');				
			}else{
				this.map.openDoors();
			}
		}
		
		if (m == 0){
			stateMachine(Event.VALID_MOVE);
		}
		else if (m == 1){
			stateMachine(Event.LEVEL_COMPLETED);
		}
		else if (m == -1){
			stateMachine(Event.INVALID_MOVE);
		}
		if (this.map instanceof DungeonMap){
			if (hero.heroSpotted(guard)){
				stateMachine(Event.HERO_CAUGHT);
			}
		}
		
		//TODO: Deveríamos arranjar outro lugar para meter isto...
		else if(this.map instanceof OgreMap) {
			for (int i = 0; i < ogres.size(); i++){
				if (ogres.get(i).heroAdjacent(hero)){
					stateMachine(Event.HERO_CAUGHT);
				}
				hero.stunOgre(ogres.get(i));
			}
		}
		return true;
	}
	
	public GameMap getGameMap(){
		return this.map;
	}
	
	public State getState(){
		return this.state;
	}
	
	public void setGameMap(GameMap map){
		this.map = map;
		if (map instanceof DungeonMap)
			this.level = Level.DUNGEON;
		else if (map instanceof OgreMap)
			this.level = Level.OGRE;
		spawnEntities();
	}
	
	public String drawMap(){
		char[][] currentMap = map.getMap();
		String map = "";
		
		for (int i = 0; i < currentMap.length; i++){
			resume:
			for (int j = 0; j < currentMap[i].length; j++){
				if (i == hero.getX() && j == hero.getY() && hero.getX() != 0 && hero.getY() != 0){
					System.out.print(hero.getSymbol() + " ");  //Display do herói.
					map += (hero.getSymbol() + " ");
					continue;
				}
				else if (i == hero.getClubX() && j == hero.getClubY()) {
					System.out.print(hero.getClubSymbol() + " "); //Display do club do herói
					map += (hero.getClubSymbol() + " ");
					continue;
				}
				if (guard != null) {
					if (i == guard.getX() && j == guard.getY() && guard.getX() != 0 && guard.getY() != 0){
						System.out.print(guard.getSymbol() + " ");  //Display do guarda.
						map += (guard.getSymbol() + " ");
						continue;
					}
				}
				if (i == this.keyX && j == this.keyY){
					System.out.print("k ");
					map += "k ";
					continue;
				}
				for (int k = 0; k < ogres.size(); k++){  //Verifica se existe um ogre nesta posição.
					if (ogres.get(k).getX() == i && ogres.get(k).getY() == j){
						System.out.print(ogres.get(k).getSymbol() + " ");  //Display de um ogre.
						map += (ogres.get(k).getSymbol() + " ");
						continue resume;  //Resume a iteração pelo mapa.
					}
					else if (ogres.get(k).getClubX() == i && ogres.get(k).getClubY() == j){
						System.out.print(ogres.get(k).getClubSymbol() + " ");  //Display do club de um ogre.
						map += (ogres.get(k).getClubSymbol() + " ");
						continue resume;
					}
				}
				System.out.print(currentMap[i][j] + " ");  //Display da célula da planta (nenhum objeto encontrado).
				map += (currentMap[i][j] + " ");
			}
			System.out.print("\n");
			map += "\n";
		}
		return map;
	}
	
	// SPAWN ENTITIES
	
	public void spawnHero(int x, int y){
		hero = new Hero(x, y, this.map.getName());
	}
	
	public void spawnGuard(int x, int y){
		Random rnd = new Random();
		int guardGen = rnd.nextInt(3);
		
		if (guardGen == 0){
			guard = new GuardRookie(x,y);
		}
		else if (guardGen == 1){
			guard = new GuardDrunk(x,y);
		}
		else if (guardGen == 2){
			guard = new GuardSuspicious(x,y);
		}
		return;
	}
	public void spawnGuard(int x, int y, String personality){
		if (personality == "Rookie"){
			guard = new GuardRookie(x,y);
		}
		else if (personality == "Drunk"){
			guard = new GuardDrunk(x,y);
		}
		else if (personality == "Suspicious"){
			guard = new GuardSuspicious(x,y);
		}
		return;
	}
	
	public void spawnOgres(){
		
		Random rnd = new Random();
		int ogresToGenerate = rnd.nextInt(4) + 1;
		
		for (int i = 0; i < ogresToGenerate; i++){
			int xPosition = rnd.nextInt(9), yPosition = rnd.nextInt(9);
			
			if (this.hero.getX() != xPosition && this.hero.getY() != yPosition && this.getGameMap().getMap()[xPosition][yPosition] == ' '){
				Ogre ogre = new Ogre(xPosition, yPosition);
				ogres.add(ogre);
			}else{
				i--;
			}
		}
		return;
	}
	
	public void spawnOgres(int generations){
		
		Random rnd = new Random();
		
		for (int i = 0; i < generations; i++){
			int xPosition = rnd.nextInt(9), yPosition = rnd.nextInt(9);
			
			if (this.hero.getX() != xPosition && this.hero.getY() != yPosition && this.getGameMap().getMap()[xPosition][yPosition] == ' '){
				Ogre ogre = new Ogre(xPosition, yPosition);
				ogres.add(ogre);
			}else{
				i--;
			}
		}
		return;
	}
	
	public void spawnKey(int x, int y){
		this.keyX = x;
		this.keyY = y;
		return;
	}
	
	public void moveEveryOgre(){
		for (int i = 0; i < ogres.size(); i++){
			ogres.get(i).move(this);
		}
		return;
	}
	
}
