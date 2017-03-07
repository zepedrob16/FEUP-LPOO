package dkeep.logic;

public class OgreMap implements GameMap{
	
	private String name = "CRAZY OGRE";
	private char[][] gameMap;
	
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
	
	public OgreMap(char[][] map){
		this.gameMap = map;
	}
	
	public int moveTo(int x, int y) {
		if (gameMap[x][y] == 'X' || gameMap[x][y] == 'I'){
			return -1; //Returns -1 if the move is invalid (wall or door in the way).
		}
		else if (gameMap[x][y] == 'k') {
			//gameMap[x][y] = ' ';
			return 2;
		}
		else if (gameMap[x][y] == 'S'){
			return 1; //Returns 1 if the move is towards a stairs block.
		}
		return 0; //Returns 0 if the move is valid, but not towards a stairs block.
	}
	
	public void openDoors() {
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
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

	public void setMap(char[][] map) {
		this.gameMap = map;
	}
}
