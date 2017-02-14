import java.util.Scanner;

public class DungeonKeep {
	
	static int MAP_ROWS = 10, MAP_COLS = 10;
	static char gameMap[][] = new char[MAP_ROWS][MAP_COLS];
	
	public static void drawMap() {
		//Mostra o game map no ecrã.
		for (int i = 0; i < MAP_ROWS; i++){
			for (int j = 0; j < MAP_COLS; j++){
				if (gameMap[i][j] == 0){
					gameMap[i][j] = ' ';
				}
				System.out.print(gameMap[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	public static void main(String[] args) {

		//Preenche rapidamente as borders superiores e inferiores.
		for (int i = 0; i < MAP_COLS; i++){
			gameMap[0][i] = 'X';
			gameMap[MAP_ROWS - 1][i] = 'X';
		}
		
		//Preenche o game map.
		gameMap[1][0] = 'X'; gameMap[1][1] = 'H'; gameMap[1][4] = 'I'; gameMap[1][6] = 'X'; gameMap[1][8] = 'G'; gameMap[1][9] = 'X';
		gameMap[2][0] = 'X'; gameMap[2][1] = 'X'; gameMap[2][2] = 'X'; gameMap[2][4] = 'X'; gameMap[2][5] = 'X'; gameMap[2][6] = 'X';
		gameMap[2][9] = 'X'; gameMap[3][0] = 'X'; gameMap[3][2] = 'I'; gameMap[3][4] = 'I'; gameMap[3][6] = 'X'; gameMap[3][9] = 'X';
		gameMap[4][0] = 'X'; gameMap[4][1] = 'X'; gameMap[4][2] = 'X'; gameMap[4][4] = 'X'; gameMap[4][5] = 'X'; gameMap[4][6] = 'X';
		gameMap[4][9] = 'X'; gameMap[5][0] = 'I'; gameMap[5][9] = 'X'; gameMap[6][0] = 'I'; gameMap[6][9] = 'X'; gameMap[7][0] = 'X'; 
		gameMap[7][1] = 'X'; gameMap[7][2] = 'X'; gameMap[7][4] = 'X'; gameMap[7][5] = 'X'; gameMap[7][6] = 'X'; gameMap[7][7] = 'X'; 
		gameMap[7][9] = 'X'; gameMap[8][0] = 'X'; gameMap[8][2] = 'I'; gameMap[8][4] = 'I'; gameMap[8][6] = 'X'; gameMap[8][7] = 'k'; 
		gameMap[8][9] = 'X';
		
		int heroPosition[] = {1,1};
		
		drawMap();
		
		Scanner s = new Scanner(System.in);
		for (;;){
			String move = s.nextLine();
			
			if (move == "w" || move == "W"){
				if (gameMap[heroPosition[0] - 1][heroPosition[1]] != 'X'){
					gameMap[heroPosition[0]][heroPosition[1]] = ' ';
					heroPosition[0]--;
					gameMap[heroPosition[0]][heroPosition[1]] = 'H';
					
					drawMap();
				}
			}
			else if (move == "a" || move == "A"){
				if (gameMap[heroPosition[0]][heroPosition[1] - 1] != 'X'){
					gameMap[heroPosition[0]][heroPosition[1]] = ' ';
					heroPosition[1]--;
					gameMap[heroPosition[0]][heroPosition[1]] = 'H';
					
					drawMap();
				}
			}
			else if (move == "s" || move == "S"){
				if (gameMap[heroPosition[0] + 1][heroPosition[1]] != 'X'){
					gameMap[heroPosition[0]][heroPosition[1]] = ' ';
					heroPosition[0]++;
					gameMap[heroPosition[0]][heroPosition[1]] = 'H';
					
					drawMap();
				}
			}
			else if (move == "d" || move == "D"){
				if (gameMap[heroPosition[0]][heroPosition[1] + 1] != 'X'){
					gameMap[heroPosition[0]][heroPosition[1]] = ' ';
					heroPosition[1]++;
					gameMap[heroPosition[0]][heroPosition[1]] = 'H';
					
					drawMap();
				}
			}
			else if (move == "f" || move == "F"){
				break;
			}
		}
		
	}
}
