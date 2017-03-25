package dkeep.logic;

public interface GameMap {
	public int moveTo(int x, int y);
	public char[][] getMap();
	public void setMap(char[][] map);
	public void openDoors();
	public String getName();
}
