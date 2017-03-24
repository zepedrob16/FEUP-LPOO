package dkeep.logic;

/**
 * Map general interface
 * 
 * @author José Borges and Miguel Mano Fernandes
 * @version 1.0
 *
 */

public interface GameMap {
	public int moveTo(int x, int y);
	public char[][] getMap();
	public void setMap(char[][] map);
	public void openDoors();
	public String getName();
}
