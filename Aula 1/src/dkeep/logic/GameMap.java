package dkeep.logic;

public interface GameMap {
	public void drawMap(Hero hero, Guard guard);
	public int moveTo(int x, int y);
	public void openDoors();
}
