package dkeep.logic;

import java.io.Serializable;

/**
 * Class that holds all the dungeon map information
 * 
 * @author José Borges and Miguel Mano Fernandes
 * @version 1.0
 *
 */

public class DungeonMap implements GameMap, Serializable{

	private String name = "DUNGEON";
	private char[][] gameMap;
	private boolean defaultMap; // Apenas usado para abrir gate principal.
	
	
	/**
	 * Dungeon map constructor with a default map
	 */
	public DungeonMap() {
		System.out.println("\nLoading DUNGEON level...\n");
		
		this.gameMap = new char[][] {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ','I',' ','X',' ','G','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
		};
		this.defaultMap = true;
	}
	
	/**
	 * Class that holds all the dungeon map information
	 * 
	 * @author José Borges and Miguel Mano Fernandes
	 * @version 1.0
	 *
	 */
	
	public DungeonMap(char[][] map){
		this.gameMap = map;
		this.defaultMap = false;
	}
	
	/**
	 * Function that opens the dungeon doors. If the map is the default one it only opens the contiguous doors to the left
	 */
	
	public void openDoors() {
		if (this.defaultMap){
			this.gameMap[5][0] = 'S';
			this.gameMap[6][0] = 'S';
			return;
		}
		for (int i = 0; i < gameMap.length; i++){
			for (int j = 0; j < gameMap[i].length; j++){
				if (gameMap[i][j] == 'I') {
					gameMap[i][j] = 'S';
				}
			}
		}
		return;
	}
	
	/**
	 * Checks whether the position to move to is valid or not
	 * @param x
	 *		x position
	 * @param y
	 *		y position
	 */
	
	public int moveTo(int x, int y) {
		if (gameMap[x][y] == 'X' || gameMap[x][y] == 'I'){
			return -1; //Returns -1 if the move is invalid (wall or door in the way).
		}
		else if (gameMap[x][y] == 'S'){
			return 1; //Returns 1 if the move is towards a stairs block.
		}
		return 0; //Returns 0 if the move is valid, but not towards a stairs block.
	}
	
	/**
	 * Returns the current map
	 */

	public char[][] getMap() {
		return gameMap;
	}
	
	/**
	 * Sets the current map
	 */
	public void setMap(char[][] map){
		this.gameMap = map;
	}
	
	/**
	 * Retrieves the level name
	 */
	public String getName(){
		return name;
	}
		
}
