package dkeep.logic;

import java.util.Random;
import java.util.*;

/**
 * Contains all the information regarding the state of the game
 * 
 * @author José Borges and Miguel Mano Fernandes
 * @version 1.0
 *
 */
public class GameState {
	
	// STATE MACHINE
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
	
	// GAME ENTITIES
	private GameMap map;
	public Hero hero;
	public Guard guard;
	public int keyX, keyY, nogres;
	public boolean leverOn;
	public ArrayList<Ogre> ogres = new ArrayList<Ogre>();  //Ogres spawned on the map.
	public String pers;
	public String message;
	
	//LOADED MAP
	private GameMap oMap = new OgreMap();
	/**
	 * Constructor
	 */
	public GameState() {
		this.level = Level.DUNGEON;
		this.state = State.RUNNING;
	}
	/**
	 * Constructor that receives a game map
	 * @param map
	 * 			This can either be a dungeon map or an ogre map
	 */
	
	public GameState(GameMap map) {
		super();
		this.map = map;
		this.leverOn = false;
		this.level = (map instanceof DungeonMap) ? Level.DUNGEON : Level.OGRE;
		this.state = State.RUNNING;
		spawnEntities();
	}
	
	/**
	 * State machine that helps define the current state of the game
	 * 
	 * @param evt
	 * 			An event that determines whether the game continues, ends with a win or ends with a loss
	 */
	
	public void stateMachine(Event evt){
		
		if (this.state == State.RUNNING){
			if (evt == Event.VALID_MOVE){
				this.message = "";
				updateEntities();
			}
			else if (evt == Event.INVALID_MOVE){
				this.message = "Invalid move!";
			}
			else if (evt == Event.HERO_CAUGHT){
				this.message = "GAME OVER!";
				this.state = State.DEFEAT;
			}
			else if (evt == Event.LEVEL_COMPLETED){
				this.message = "Level complete!";
				this.state = State.VICTORY;
			}
			System.out.println("\n" + this.message + "\n");
		}
		else if (this.state == State.VICTORY){
			
			if (this.level == Level.DUNGEON){
				this.level = Level.OGRE;
				OgreMap ogreMap = new OgreMap(oMap.getMap());
				setGameMap(ogreMap);
				spawnOgres(nogres);
				spawnEntities();
				guard = null;
				drawMap();
				this.state = State.RUNNING;
			}
			else if (this.level == Level.OGRE){
				System.out.println("\n\nYOU WIN! Dungeon Keep cleared.\n");
			}
		}
	}
	
	/**
	 * Moves the enemies of the map and redraws it
	 */
	
	public void updateEntities(){
		
		if (level == Level.DUNGEON){
			guard.moveGuard();
		}
		else if (level == Level.OGRE){
			moveEveryOgre();
		}
		drawMap();
	}
	
	/**
	 * Spawns all map elements
	 */
	
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

					if (pers != null){
						spawnGuard(i, j, pers);						
					}else{
						spawnGuard(i, j);
					}
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
	
	/**
	 * Checks the move the player wants to do and sees whether it is valid, invalid or the game winner
	 * 
	 * @param move
	 * @return true
	 */
	
	public boolean processMove(String move){
		int m = hero.moveHero(this.map, move);
		doorOpenVerification();
		
		if (m == 0){
			stateMachine(Event.VALID_MOVE);
		}
		else if (m == 1){
			stateMachine(Event.LEVEL_COMPLETED);
		}
		else if (m == -1){
			stateMachine(Event.INVALID_MOVE);
		}
		mapSpecificVerification();
		return true;
	}
	/**
	 * Checks if the hero is in the position to pick up the key (in the ogre map) or to turn the lever on (in the dungeon map)
	 */
	
	public void doorOpenVerification(){
		if (this.hero.getX() == this.keyX && this.hero.getY() == this.keyY){
			if (this.map instanceof OgreMap){
				this.keyX = -1;
				this.keyY = -1;
				this.hero.setSymbol('K');	
			}else{
				this.map.openDoors();
				this.leverOn = true;
			}
		}
	}
	
	/**
	 * Checks if the hero got caught by the enemy and/or if stunned an ogre
	 */
	
	public void mapSpecificVerification(){
		if (this.map instanceof DungeonMap){
			if (hero.heroSpotted(guard)){
				stateMachine(Event.HERO_CAUGHT);
			}
		}
		else if(this.map instanceof OgreMap) {
			for (int i = 0; i < ogres.size(); i++){
				if (ogres.get(i).heroAdjacent(hero)){
					stateMachine(Event.HERO_CAUGHT);
				}
				hero.stunOgre(ogres.get(i));
			}
		}
	}
	
	/**
	 * Returns the current map
	 * 
	 * @return
	 * 		game map
	 */
	
	public GameMap getGameMap(){
		return this.map;
	}
	
	/**
	 * Returns the current state of the game
	 * 
	 * @return state
	 */
	
	public State getState(){
		return this.state;
	}
	
	/**
	 * Sets a new game map
	 * 
	 * @param map
	 * 		new game map
	 */
	
	public void setGameMap(GameMap map){
		this.map = map;
		this.level = (map instanceof DungeonMap) ? Level.DUNGEON : Level.OGRE;
		spawnEntities();
	}
	
	/**
	 * Loads a string with chars related with the entities and static objects on the map and prints it on the console
	 * 
	 * @return string map
	 * 		String containing the complete map
	 */
	
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
	/**
	 * Sets a new ogre map
	 * 
	 * @param oMap
	 * 		new ogre map
	 */
	
	public void setOgreMap(GameMap oMap){
		this.oMap = oMap;
	}
	
	// SPAWN ENTITIES
	
	/**
	 * Creates a new hero
	 * 
	 * @param x
	 * 		x position
	 * @param y
	 *		y position
	 */
	
	public void spawnHero(int x, int y){
		hero = new Hero(x, y, this.map.getName());
	}
	
	/**
	 * Creates a new guard with a random personality
	 * 
	 * 
	 * @param x
	 * 		x position
	 * @param y
	 * 		y position
	 */
	
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
	
	/**
	 * Creates a new guard with a specific personality
	 * 
	 * @param x
	 * 		x position
	 * @param y
	 * 		y position
	 * @param personality
	 * 		Drunken, Suspicious or Rookie
	 */
	
	public void spawnGuard(int x, int y, String personality){
		if (personality.equals("Rookie")){
			guard = new GuardRookie(x,y);
		}
		else if (personality.equals("Drunk")){
			guard = new GuardDrunk(x,y);
		}
		else if (personality.equals("Suspicious")){
			guard = new GuardSuspicious(x,y);
		}
		return;
	}
	
	/**
	 * Creates 1 to 5 ogres
	 */
	
	public void spawnOgres(){
		
		Random rnd = new Random();
		int ogresToGenerate = rnd.nextInt(5) + 1;
		ogres.clear();
		
		for (int i = 0; i < ogresToGenerate; i++){
			int xPosition = rnd.nextInt(map.getMap().length - 2) + 1, yPosition = rnd.nextInt(map.getMap().length - 2) + 1;
			
			if (this.hero.getX() != xPosition && this.hero.getY() != yPosition && this.getGameMap().getMap()[xPosition][yPosition] == ' '){
				Ogre ogre = new Ogre(xPosition, yPosition);
				ogres.add(ogre);
			}else{
				i--;
			}
		}
		return;
	}
	
	/**
	 * Creates a specific number of ogres
	 * 
	 * @param generations
	 * 		number of ogres to spawn
	 */
	
	public void spawnOgres(int generations){
		
		Random rnd = new Random();
		ogres.clear();
		
		for (int i = 0; i < generations; i++){
			int xPosition = rnd.nextInt(map.getMap().length - 2) + 1, yPosition = rnd.nextInt(map.getMap().length - 2) + 1;
			
			if (this.hero.getX() != xPosition && this.hero.getY() != yPosition && this.getGameMap().getMap()[xPosition][yPosition] == ' '){
				Ogre ogre = new Ogre(xPosition, yPosition);
				ogres.add(ogre);
			}else{
				i--;
			}
		}
		return;
	}
	
	/**
	 * Creates a key on a specific location
	 * 
	 * @param x
	 * 		x position
	 * @param y
	 * 		y position
	 */
	
	public void spawnKey(int x, int y){
		this.keyX = x;
		this.keyY = y;
		return;
	}
	
	/**
	 * Moves all ogres
	 */
	
	public void moveEveryOgre(){
		for (int i = 0; i < ogres.size(); i++){
			ogres.get(i).move(this);
		}
		return;
	}
	
	/**
	 * Sets a number of ogres on the map
	 * @param num
	 * 		Number of ogres
	 */
	
	public void setOgres(int num) {
		this.nogres = num;
	}
	
	/**
	 * Sets the guard personality 
	 * @param p
	 * 		Drunken, Suspicious or Rookie
	 */
	
	public void setPers(String p) {
		this.pers = p;
	}
}
