package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * Class that contains all information regarding the hero
 * 
 * @author José Borges and Miguel Mano Fernandes
 * @version 1.0
 */
public class Hero implements Serializable{
	private int x, y;
	private char symbol;
	
	public Hero(){}
	
	/**
	 * Creates a hero with a specific position in a map
	 * 
	 * @param x
	 * 		x coordinate
	 * @param y
	 * 		y coordinate
	 * @param name
	 * 		name of the map where he spawns
	 */
	
	public Hero(int x, int y, String name){
		this.x = x;
		this.y = y;
		
		if (name == "DUNGEON"){
			this.symbol = 'H';			
		}
		else if (name == "CRAZY OGRE"){
			this.symbol = 'A';
		}
	}
	
	/**
	 * Moves the hero up
	 * 
	 * @param map
	 * 		map where the hero is currently at
	 * @return
	 * 		Returns a value based on whether the move was successfull or not or if it was a game winner or a door opener
	 */
	
	public int moveUp(GameMap map) {
		if (map.moveTo(x-1, y) == -2 && symbol == 'K') {
			map.openDoors();
			return 0;
		}
		
		if (map.moveTo(x-1, y) == 0 || map.moveTo(x-1, y) == 1 || map.moveTo(x-1, y) == 2){ //Valid moves, hero position updated.
			this.x--;
			return map.moveTo(x, y);
		}
		return map.moveTo(x-1, y);	//In case of invalid movement, hero stays put.
	}
	
	/**
	 * Moves the hero left
	 * 
	 * @param map
	 * 		map where the hero is currently at
	 * @return
	 * 		Returns a value based on whether the move was successfull or not or if it was a game winner or a door opener
	 */
	
	public int moveLeft(GameMap map) {
		if (map.moveTo(x, y-1) == -2 && symbol == 'K') {
			map.openDoors();
			return 0;
		}
	
		if (map.moveTo(x, y-1) == 0 || map.moveTo(x, y-1) == 1 || map.moveTo(x, y-1) == 2){
			this.y--;
			return map.moveTo(x, y);
		}
		return map.moveTo(x, y-1);
	}
	
	/**
	 * Moves the hero down
	 * 
	 * @param map
	 * 		map where the hero is currently at
	 * @return
	 * 		Returns a value based on whether the move was successfull or not or if it was a game winner or a door opener
	 */
	
	public int moveDown(GameMap map) {
		if (map.moveTo(x+1, y) == -2 && symbol == 'K') {
			map.openDoors();
			return 0;
		}
		
		if (map.moveTo(x+1, y) == 0 || map.moveTo(x+1, y) == 1 || map.moveTo(x+1, y) == 2){
			this.x++;
			return map.moveTo(x, y);
		}
		return map.moveTo(x+1, y);
	}
	
	/**
	 * Moves the hero right
	 * 
	 * @param map
	 * 		map where the hero is currently at
	 * @return
	 * 		Returns a value based on whether the move was successfull or not or if it was a game winner or a door opener
	 */
	
	public int moveRight(GameMap map) {
		if (map.moveTo(x, y+1) == -2 && symbol == 'K') {
			map.openDoors();
			return 0;
		}
		
		if (map.moveTo(x, y+1) == 0 || map.moveTo(x, y+1) == 1 || map.moveTo(x, y+1) == 2){
			this.y++;
			return map.moveTo(x, y);
		}
		return map.moveTo(x,y+1);
	}
	
	/**
	 * Moves the hero
	 * 
	 * @param map
	 * 		map where the hero is currently placed
	 * @param move
	 * 		String that determines whether the hero goes up, down, left or right
	 * @return
	 * 		Returns a value based on whether the move was successfull or not or if it was a game winner or a door opener
	 */
	
	public int moveHero(GameMap map, String move){
		if (move.equals("w")) {
			return moveUp(map);
		}
		else if (move.equals("a")){
			return moveLeft(map);
		}
		else if (move.equals("s")){
			return moveDown(map);
		}
		else if (move.equals("d")){
			return moveRight(map);
		}
		return -1;	//In case of invalid input, it's discarded.
	}
	
	/**
	 * Checks whether the hero was spotted by the guard or not
	 * 
	 * @param guard
	 * 		The current guard
	 * @return
	 * 		True or false depending on whether or not he got caught
	 */
	
	public boolean heroSpotted(Guard guard){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if (((x == guard.getX() + 1 && y == guard.getY()) || (x == guard.getX() - 1 && y == guard.getY()) || (y == guard.getY() + 1 && x == guard.getX()) || (y == guard.getY() - 1 && x == guard.getX())) && guard.getSymbol() == 'G'){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Sets the hero's current symbol
	 * 
	 * @param symbol
	 */
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Checks if the hero has stunned an ogre
	 * 
	 * @param ogre
	 * 		Ogre currently being analyzed
	 * @return
	 * 		True or false depending whether or not the ogre got stunned
	 */
	
	public boolean stunOgre(Ogre ogre) {
		if ((x == ogre.getX() - 1 && y == ogre.getY()) || (x == ogre.getX() + 1 && y == ogre.getY()) || (x == ogre.getX() && y == ogre.getY() - 1) || (x == ogre.getX() && y == ogre.getY() + 1)) {
			ogre.stun();
			return true;
		}
		
		return false;
	}
	/**
	 * Returns the hero's x position
	 * 
	 * @return
	 */

	//Get methods.
	public int getX(){
		return x;
	}
	/**
	 * Returns the hero's y position
	 * @return
	 */
	public int getY(){
		return y;
	}
	/**
	 * Returns the hero's symbol
	 * @return
	 */
	public char getSymbol(){
		return symbol;
	}
	
}