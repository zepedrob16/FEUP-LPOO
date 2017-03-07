package dkeep.logic;

public class DungeonMap implements GameMap{

	private String name = "DUNGEON";
	private char[][] gameMap;
	
	// Inicializa o mapa por defeito.
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
	}
	
	//Inicializa um mapa custom.
	public DungeonMap(char[][] map){
		this.gameMap = map;
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

	public char[][] getMap() {
		return gameMap;
	}
	public void setMap(char[][] map){
		this.gameMap = map;
	}
	
	public String getName(){
		return name;
	}
	
}
