package dkeep.logic;

import java.io.Serializable;

/**
 * Class containing all information regarding the ogre map
 * 
 * @author José Borges and Miguel Mano Fernandes
 * @version 1.0
 */

public class OgreMap implements GameMap, Serializable{
	
	private String name = "CRAZY OGRE";
	private char[][] gameMap;
	
	/**
	 * Creates a default ogre map
	 */
	
	public OgreMap(){
		System.out.println("\nLoading CRAZY OGRE level...\n");
		
		this.gameMap = new char[][]{
			{'X','X','X','X','X','X','X','X','X'},
			{'I',' ',' ',' ',' ',' ',' ','k','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','H',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X'}
		};
	}
	
	/**
	 * Creates  a specific ogre map
	 * @param map
	 * 		Map to be created
	 */
	
	public OgreMap(char[][] map){
		this.gameMap = map;
	}
	
	/**
	 * Checks whether the position is a valid one or not
	 * 
	 * @param x
	 * 		x coordinate
	 * @param y
	 * 		y coordinate
	 * @return
	 * 		Returns a value based on whether the move was successfull or not or if it was a game winner or a door opener
	 */
	
	public int moveTo(int x, int y) {
		if (gameMap[x][y] == 'X'){
			return -1; //Returns -1 if the move is invalid (wall or door in the way).
		}
		if ( gameMap[x][y] == 'I')
			return -2; //Returns -2 to open the door
		else if (gameMap[x][y] == 'S'){
			return 1; //Returns 1 if the move is towards a stairs block.
		}
		return 0; //Returns 0 if the move is valid, but not towards a stairs block.
	}
	
	/**
	 * Opens the map's doors
	 */
	
	public void openDoors() {
		for (int i = 0; i < gameMap.length; i++){
			for (int j = 0; j < gameMap[i].length; j++){
				if (gameMap[i][j] == 'I') {
					gameMap[i][j] = 'S';
				}
			}
		}
	}
	
	/**
	 * Returns the current map
	 */
	
	public char[][] getMap() {
		return gameMap;
	}
	
	/**
	 * Returns the map's name
	 */
	
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the current map
	 */
	
	public void setMap(char[][] map) {
		this.gameMap = map;
	}
}
