package dkeep.logic;

public class OgreMap implements GameMap{
	
	private String name = "CRAZY OGRE";  //Nome do mapa.
	char[][] gameMap = new char[9][9];
	
	public OgreMap(){
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				gameMap[i][j] = ' ';
			}
		}
		for (int i = 0; i < 9; i++){
			gameMap[0][i] = 'X';
			gameMap[8][i] = 'X';
			gameMap[i][0] = 'X';
			gameMap[i][8] = 'X';
		}
	}
	
	public int moveTo(int x, int y) {
		if (gameMap[x][y] == 'X' || gameMap[x][y] == 'I'){
			return -1; //Returns -1 if the move is invalid (wall or door in the way).
		}
		else if (gameMap[x][y] == 'k') {
			openDoors();
		}
		else if (gameMap[x][y] == 'S'){
			return 1; //Returns 1 if the move is towards a stairs block.
		}
		return 0; //Returns 0 if the move is valid, but not towards a stairs block.
	}
	
	public void openDoors() {
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if (gameMap[i][j] == 'I') {
					gameMap[i][j] = 'S';
				}
			}
		}
	}
	
	public char[][] getMap() {
		return gameMap;
	}
	public String getName(){
		return name;
	}
}
