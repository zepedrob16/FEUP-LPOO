package dkeep.logic;

public interface GameMap {
	public int moveTo(int x, int y);
	public char[][] getMap();
	public void openDoors();
}
