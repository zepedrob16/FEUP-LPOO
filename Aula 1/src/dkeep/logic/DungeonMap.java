package dkeep.logic;

public class DungeonMap implements GameMap{

	char[][] gameMap = new char[10][10];
	Hero hero = new Hero(1,1);
	Guard guard = new Guard(1,8,'R');
	
	public void drawMap() {
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				System.out.print(gameMap[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public void changeCell(int x, int y){
		if (gameMap[x][y] == 'I'){
			gameMap[x][y] = 'S';
		}
	}
	
	public boolean moveTo(int x, int y) {
		if (gameMap[x][y] == 'X'){
			return false;
		}else{
			return true;
		}
	}
	public DungeonMap() {
		for (int i = 0; i < 10; i++){
			gameMap[0][i] = 'X';
			gameMap[9][i] = 'X';
		}
		gameMap[1][0] = 'X'; gameMap[1][4] = 'I'; gameMap[1][6] = 'X'; gameMap[1][9] = 'X';
		gameMap[2][0] = 'X'; gameMap[2][1] = 'X'; gameMap[2][2] = 'X'; gameMap[2][4] = 'X'; gameMap[2][5] = 'X'; gameMap[2][6] = 'X';
		gameMap[2][9] = 'X'; gameMap[3][0] = 'X'; gameMap[3][2] = 'I'; gameMap[3][4] = 'I'; gameMap[3][6] = 'X'; gameMap[3][9] = 'X';
		gameMap[4][0] = 'X'; gameMap[4][1] = 'X'; gameMap[4][2] = 'X'; gameMap[4][4] = 'X'; gameMap[4][5] = 'X'; gameMap[4][6] = 'X';
		gameMap[4][9] = 'X'; gameMap[5][0] = 'I'; gameMap[5][9] = 'X'; gameMap[6][0] = 'I'; gameMap[6][9] = 'X'; gameMap[7][0] = 'X'; 
		gameMap[7][1] = 'X'; gameMap[7][2] = 'X'; gameMap[7][4] = 'X'; gameMap[7][5] = 'X'; gameMap[7][6] = 'X'; gameMap[7][7] = 'X'; 
		gameMap[7][9] = 'X'; gameMap[8][0] = 'X'; gameMap[8][2] = 'I'; gameMap[8][4] = 'I'; gameMap[8][6] = 'X'; 
		gameMap[8][9] = 'X';
		
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if (gameMap[i][j] == 0)
					gameMap[i][j] = ' ';
			}
		}
	}
	public boolean heroSpotted(){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if ((hero.getX() == guard.getX() + 1 && hero.getY() == guard.getY()) ||
						(hero.getX() == guard.getX() - 1 && hero.getY() == guard.getY())){
					return true;
				}
				else if ((hero.getY() == guard.getY() + 1 && hero.getX() == guard.getX()) ||
						(hero.getY() == guard.getY() - 1 && hero.getX() == guard.getX())){
					return true;
				}
			}
		}
		return false;
	}

	
}
