import java.util.Scanner;

public class DungeonKeep {
	
	static int MAP_ROWS = 10, MAP_COLS = 10;
	static int heroPosition[] = new int[2];
	static int guardPosition[] = new int[2];
	static int leverPosition[] = new int[2];
	static char guardMovement[] = {'A','S','S','S','S','A','A','A','A','A','A','S','D','D','D','D','D','D','D','W','W','W','W','W'};
	static int movementIterator = 0;
	
	static char gameMap[][] = new char[MAP_ROWS][MAP_COLS];
	
	//Initially loads every element of the first map.
	public static void loadMap1(){
		for (int i = 0; i < MAP_COLS; i++){
			gameMap[0][i] = 'X';
			gameMap[MAP_ROWS - 1][i] = 'X';
		}
		gameMap[1][0] = 'X'; gameMap[1][1] = 'H'; gameMap[1][4] = 'I'; gameMap[1][6] = 'X'; gameMap[1][8] = 'G'; gameMap[1][9] = 'X';
		gameMap[2][0] = 'X'; gameMap[2][1] = 'X'; gameMap[2][2] = 'X'; gameMap[2][4] = 'X'; gameMap[2][5] = 'X'; gameMap[2][6] = 'X';
		gameMap[2][9] = 'X'; gameMap[3][0] = 'X'; gameMap[3][2] = 'I'; gameMap[3][4] = 'I'; gameMap[3][6] = 'X'; gameMap[3][9] = 'X';
		gameMap[4][0] = 'X'; gameMap[4][1] = 'X'; gameMap[4][2] = 'X'; gameMap[4][4] = 'X'; gameMap[4][5] = 'X'; gameMap[4][6] = 'X';
		gameMap[4][9] = 'X'; gameMap[5][0] = 'I'; gameMap[5][9] = 'X'; gameMap[6][0] = 'I'; gameMap[6][9] = 'X'; gameMap[7][0] = 'X'; 
		gameMap[7][1] = 'X'; gameMap[7][2] = 'X'; gameMap[7][4] = 'X'; gameMap[7][5] = 'X'; gameMap[7][6] = 'X'; gameMap[7][7] = 'X'; 
		gameMap[7][9] = 'X'; gameMap[8][0] = 'X'; gameMap[8][2] = 'I'; gameMap[8][4] = 'I'; gameMap[8][6] = 'X'; gameMap[8][7] = 'k'; 
		gameMap[8][9] = 'X';
		
		for (int i = 0; i < MAP_ROWS; i++){
			for (int j = 0; j < MAP_COLS; j++){
				if (gameMap[i][j] == 0)
					gameMap[i][j] = ' ';
			}
		}
		return;
	}
	
	//Loads every element of the second map.
	public static void loadMap2(){
		//TODO: Complete.
	}
	
	//Loads the initial hero, guard, lever and exit positions.
	public static void loadPositions(){
		for (int i = 0; i < MAP_ROWS; i++){
			for (int j = 0; j < MAP_COLS; j++){
				if (gameMap[i][j] == 'H'){
					heroPosition[0] = i;
					heroPosition[1] = j;
				}
				else if (gameMap[i][j] == 'G'){
					guardPosition[0] = i;
					guardPosition[1] = j;
				}
				else if (gameMap[i][j] == 'k'){
					leverPosition[0] = i;
					leverPosition[1] = j;
				}
			}
		}
	}
	
	//Displays the game map on the console.
	public static void drawMap() {
		for (int i = 0; i < MAP_ROWS; i++){
			for (int j = 0; j < MAP_COLS; j++){
				System.out.print(gameMap[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	//Opens every door.
	public static void openDoors(){
		for (int i = 0; i < MAP_ROWS; i++){
			for (int j = 0; j < MAP_COLS; j++){
				if (gameMap[i][j] == 'I')
					gameMap[i][j] = 'S';
			}
		}
	}
	
	//Returns true/false, given the hero's proximity to the guard.
	public static boolean heroSpotted(){
		for (int i = 0; i < MAP_ROWS; i++){
			for (int j = 0; j < MAP_COLS; j++){
				if ((heroPosition[0] == guardPosition[0] + 1 && heroPosition[1] == guardPosition[1]) ||
					(heroPosition[0] == guardPosition[0] - 1 && heroPosition[1] == guardPosition[1])){
					return true;
				}
				else if ((heroPosition[1] == guardPosition[1] + 1 && heroPosition[0] == guardPosition[0]) ||
						  (heroPosition[1] == guardPosition[1] - 1 && heroPosition[0] == guardPosition[0])){
					return true;
				}
			}
		}
		return false;
	}
	
	//Detects whether the lever has been stepped on.
	public static void leverStepped(){
		if (heroPosition[0] == leverPosition[0] && heroPosition[1] == leverPosition[1])
			openDoors();
		return;
	}
	
	//Moves the guard on a predetermined path.
	public static void moveGuard(){
		if (guardMovement[movementIterator] == 'W'){
			gameMap[guardPosition[0]][guardPosition[1]] = ' ';
			guardPosition[0]--;
			gameMap[guardPosition[0]][guardPosition[1]] = 'G';
		}
		else if (guardMovement[movementIterator] == 'A'){
			gameMap[guardPosition[0]][guardPosition[1]] = ' ';
			guardPosition[1]--;
			gameMap[guardPosition[0]][guardPosition[1]] = 'G';		
		}
		else if (guardMovement[movementIterator] == 'S'){
			gameMap[guardPosition[0]][guardPosition[1]] = ' ';
			guardPosition[0]++;
			gameMap[guardPosition[0]][guardPosition[1]] = 'G';	
		}
		else if (guardMovement[movementIterator] == 'D'){
			gameMap[guardPosition[0]][guardPosition[1]] = ' ';
			guardPosition[1]++;
			gameMap[guardPosition[0]][guardPosition[1]] = 'G';
		}
		movementIterator++;
		
		if (movementIterator == guardMovement.length)
			movementIterator = 0;
		
		return;
	}
	
	//Moves the hero according to the keypress.
	public static boolean moveHero(String move){
		
		boolean levelComplete = false;
		
		if (move.equals("w") || move.equals("W")){
			if (gameMap[heroPosition[0] - 1][heroPosition[1]] != 'X' && gameMap[heroPosition[0] - 1][heroPosition[1]] != 'I'){
				
				//Checks whether the next cell is an exit cell.
				if (gameMap[heroPosition[0] - 1][heroPosition[1]] == 'S'){
					levelComplete = true;
				}
				gameMap[heroPosition[0]][heroPosition[1]] = ' ';
				heroPosition[0]--;
				gameMap[heroPosition[0]][heroPosition[1]] = 'H';
				
				moveGuard();
				leverStepped();
				drawMap();
				
				if (levelComplete){
					return true;
				}
			}
		}
		else if (move.equals("a") || move.equals("A")){
			if (gameMap[heroPosition[0]][heroPosition[1] - 1] != 'X' && gameMap[heroPosition[0]][heroPosition[1] - 1] != 'I'){
				
				//Checks whether the next cell is an exit cell.
				if (gameMap[heroPosition[0]][heroPosition[1] - 1] == 'S'){
					levelComplete = true;
				}
				
				gameMap[heroPosition[0]][heroPosition[1]] = ' ';
				heroPosition[1]--;
				gameMap[heroPosition[0]][heroPosition[1]] = 'H';
				
				moveGuard();
				leverStepped();
				drawMap();
				
				if (levelComplete){
					return true;
				}
			}
		}
		else if (move.equals("s") || move.equals("S")){
			if (gameMap[heroPosition[0] + 1][heroPosition[1]] != 'X' && gameMap[heroPosition[0] + 1][heroPosition[1]] != 'I'){
				
				//Checks whether the next cell is an exit cell.
				if (gameMap[heroPosition[0] + 1][heroPosition[1]] == 'S'){
					levelComplete = true;
				}
				
				gameMap[heroPosition[0]][heroPosition[1]] = ' ';
				heroPosition[0]++;
				gameMap[heroPosition[0]][heroPosition[1]] = 'H';
				
				moveGuard();
				leverStepped();
				drawMap();
				
				if (levelComplete){
					return true;
				}
			}
		}
		else if (move.equals("d") || move.equals("D")){
			if (gameMap[heroPosition[0]][heroPosition[1] + 1] != 'X' && gameMap[heroPosition[0]][heroPosition[1] + 1] != 'I'){
				
				//Checks whether the next cell is an exit cell.
				if (gameMap[heroPosition[0]][heroPosition[1] + 1] == 'S'){
					levelComplete = true;
				}
				
				gameMap[heroPosition[0]][heroPosition[1]] = ' ';
				heroPosition[1]++;
				gameMap[heroPosition[0]][heroPosition[1]] = 'H';
				
				moveGuard();
				leverStepped();
				drawMap();
				
				if (levelComplete){
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {

		loadMap1(); //Loads the game's first map.
		loadPositions(); //Stores every game entity's initial coordinates.
		drawMap(); //Initial map display.
		
		Scanner s = new Scanner(System.in);
		
		//First level input loop.
		for (;;){
			String move = s.nextLine();
			
			if (moveHero(move)){
				System.out.println("Level complete!\n");
				break;
			}
			
			if (heroSpotted()){
				System.out.println("You got caught, doofus!\n");
				return;
			}
		}
		
		//Keep's Crazy Ogre input loop.
		for (;;){
			//TODO: Complete.
		}
		
	}
}
