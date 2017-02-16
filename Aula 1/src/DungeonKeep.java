import java.util.Random;
import java.util.Scanner;

public class DungeonKeep {
	
	static int MAP_ROWS = 10, MAP_COLS = 10;
	static int heroPosition[] = new int[2];
	static int guardPosition[] = new int[2];
	static int leverPosition[] = new int[2];
	static int ogrePosition[] = new int[2];
	static int clubPosition[] = new int[2];
	static char guardMovement[] = {'A','S','S','S','S','A','A','A','A','A','A','S','D','D','D','D','D','D','D','W','W','W','W','W'};
	static int movementIterator = 0;
	static int currentMap = 0;
	static boolean keyStolen = false;
	
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
		currentMap = 1;
		return;
	}
	
	//Loads every element of the second map.
	public static void loadMap2(){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				gameMap[i][j] = ' ';
			}
		}
		
		for (int i = 0; i < 9; i++){
			gameMap[0][i] = 'X';
			gameMap[8][i] = 'X';
			gameMap[i][0] = 'X';
			gameMap[i][8] = 'X';
		}
		gameMap[1][0] = 'I';
		gameMap[1][4] = 'O';
		gameMap[1][7] = 'k';
		gameMap[2][4] = '*';
		gameMap[7][1] = 'H';
		
		currentMap = 2;
		return;
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
				else if (gameMap[i][j] == 'O'){
					ogrePosition[0] = i;
					ogrePosition[1] = j;
				}
				else if (gameMap[i][j] == '*') {
					clubPosition[0] = i;
					ogrePosition[1] = j;
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
				if (currentMap == 1){
					if ((heroPosition[0] == guardPosition[0] + 1 && heroPosition[1] == guardPosition[1]) ||
							(heroPosition[0] == guardPosition[0] - 1 && heroPosition[1] == guardPosition[1])){
						return true;
					}
					else if ((heroPosition[1] == guardPosition[1] + 1 && heroPosition[0] == guardPosition[0]) ||
							(heroPosition[1] == guardPosition[1] - 1 && heroPosition[0] == guardPosition[0])){
						return true;
					}					
				}else{
					if ((heroPosition[0] == ogrePosition[0] + 1 && heroPosition[1] == ogrePosition[1]) ||
							(heroPosition[0] == ogrePosition[0] - 1 && heroPosition[1] == ogrePosition[1])){
						return true;
					}
					else if ((heroPosition[1] == ogrePosition[1] + 1 && heroPosition[0] == ogrePosition[0]) ||
							(heroPosition[1] == ogrePosition[1] - 1 && heroPosition[0] == ogrePosition[0])){
						return true;
					}	
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
			if (gameMap[heroPosition[0] - 1][heroPosition[1]] != 'X'){
				
				if (currentMap == 1 && gameMap[heroPosition[0] - 1][heroPosition[1]] == 'I'){
					return false;
				}
				
				//Checks whether the next cell is an exit cell.
				if (gameMap[heroPosition[0] - 1][heroPosition[1]] == 'S'){
					levelComplete = true;
				}
				if (currentMap == 1){
					moveGuard();					
					leverStepped();
				}else{
					moveOgre();
					keyPicked();
				}
				gameMap[heroPosition[0]][heroPosition[1]] = ' ';
				heroPosition[0]--;
				
				if (!keyStolen){
					gameMap[heroPosition[0]][heroPosition[1]] = 'H';					
				}else{
					gameMap[heroPosition[0]][heroPosition[1]] = 'K';
				}
				
				drawMap();
				
				if (levelComplete){
					return true;
				}
			}
		}
		else if (move.equals("a") || move.equals("A")){
			if (gameMap[heroPosition[0]][heroPosition[1] - 1] != 'X'){
				
				if (currentMap == 1 && gameMap[heroPosition[0]][heroPosition[1] - 1] == 'I'){
					return false;
				}

				//Checks whether the next cell is an exit cell.
				if (gameMap[heroPosition[0]][heroPosition[1] - 1] == 'S'){
					levelComplete = true;
				}
				if (currentMap == 2 && keyStolen) {
					if (gameMap[heroPosition[0]][heroPosition[1] - 1] == 'I') {
						openDoors();
						drawMap();
						return false;
					}
				}
				if (currentMap == 1){
					moveGuard();					
					leverStepped();
				}else{
					moveOgre();
					keyPicked();
				}
				
				gameMap[heroPosition[0]][heroPosition[1]] = ' ';
				heroPosition[1]--;
				
				if (!keyStolen){
					gameMap[heroPosition[0]][heroPosition[1]] = 'H';					
				}else{
					gameMap[heroPosition[0]][heroPosition[1]] = 'K';
				}
				
				
				drawMap();
				
				if (levelComplete){
					return true;
				}
			}
		}
		else if (move.equals("s") || move.equals("S")){
			if (gameMap[heroPosition[0] + 1][heroPosition[1]] != 'X'){
				
				if (currentMap == 1 && gameMap[heroPosition[0] + 1][heroPosition[1]] == 'I'){
					return false;
				}
				
				//Checks whether the next cell is an exit cell.
				if (gameMap[heroPosition[0] + 1][heroPosition[1]] == 'S'){
					levelComplete = true;
				}
				if (currentMap == 1){
					moveGuard();					
					leverStepped();
				}else{
					moveOgre();
					keyPicked();
				}
				
				gameMap[heroPosition[0]][heroPosition[1]] = ' ';
				heroPosition[0]++;
				
				if (!keyStolen){
					gameMap[heroPosition[0]][heroPosition[1]] = 'H';					
				}else{
					gameMap[heroPosition[0]][heroPosition[1]] = 'K';
				}
				
				drawMap();
				
				if (levelComplete){
					return true;
				}
			}
		}
		else if (move.equals("d") || move.equals("D")){
			if (gameMap[heroPosition[0]][heroPosition[1] + 1] != 'X'){

				if (currentMap == 1 && gameMap[heroPosition[0]][heroPosition[1] + 1] == 'I'){
					return false;
				}
				
				//Checks whether the next cell is an exit cell.
				if (gameMap[heroPosition[0]][heroPosition[1] + 1] == 'S'){
					levelComplete = true;
				}
				if (currentMap == 1){
					moveGuard();					
					leverStepped();
				}else{
					moveOgre();
					keyPicked();
				}
				gameMap[heroPosition[0]][heroPosition[1]] = ' ';
				heroPosition[1]++;
				
				
				if (!keyStolen){
					gameMap[heroPosition[0]][heroPosition[1]] = 'H';					
				}else{
					gameMap[heroPosition[0]][heroPosition[1]] = 'K';
				}
				
				drawMap();
				
				if (levelComplete){
					return true;
				}
			}
		}
		return false;
	}
	
	private static void keyPicked() {
		if (heroPosition[0] == leverPosition[0] && heroPosition[1] == leverPosition[1]){
			keyStolen = true;
		}
	}

	private static void moveOgre() {
		Random rnd = new Random();
		
		for (;;){
			int genMove = rnd.nextInt(4);
			
			if (genMove == 0){
				if (gameMap[ogrePosition[0] - 1][ogrePosition[1]] != 'X' && gameMap[ogrePosition[0] - 1][ogrePosition[1]] != 'I'){
					gameMap[ogrePosition[0]][ogrePosition[1]] = ' ';
					ogrePosition[0]--;
					
					if (ogrePosition[0] == leverPosition[0] && ogrePosition[1] == leverPosition[1] && !keyStolen){
						gameMap[ogrePosition[0]][ogrePosition[1]] = '$';
					}else{
						gameMap[ogrePosition[0]][ogrePosition[1]] = 'O';
						if (!keyStolen){
							gameMap[leverPosition[0]][leverPosition[1]] = 'k';							
						}
					}
					moveClub();
					break;
				}
			}
			else if (genMove == 1){
				if (gameMap[ogrePosition[0]][ogrePosition[1] - 1] != 'X' && gameMap[ogrePosition[0]][ogrePosition[1] - 1] != 'I'){
					gameMap[ogrePosition[0]][ogrePosition[1]] = ' ';
					ogrePosition[1]--;
					if (ogrePosition[0] == leverPosition[0] && ogrePosition[1] == leverPosition[1] && !keyStolen){
						gameMap[ogrePosition[0]][ogrePosition[1]] = '$';
					}else{
						gameMap[ogrePosition[0]][ogrePosition[1]] = 'O';
						if (!keyStolen){
							gameMap[leverPosition[0]][leverPosition[1]] = 'k';							
						}
					}
					moveClub();
					break;
				}
			}
			else if (genMove == 2){
				if (gameMap[ogrePosition[0] + 1][ogrePosition[1]] != 'X' && gameMap[ogrePosition[0] + 1][ogrePosition[1]] != 'I'){
					gameMap[ogrePosition[0]][ogrePosition[1]] = ' ';
					ogrePosition[0]++;
					if (ogrePosition[0] == leverPosition[0] && ogrePosition[1] == leverPosition[1] && !keyStolen){
						gameMap[ogrePosition[0]][ogrePosition[1]] = '$';
					}else{
						gameMap[ogrePosition[0]][ogrePosition[1]] = 'O';
						if (!keyStolen){
							gameMap[leverPosition[0]][leverPosition[1]] = 'k';							
						}
					}
					moveClub();
					break;
				}
			}
			else if (genMove == 3){
				if (gameMap[ogrePosition[0]][ogrePosition[1] + 1] != 'X' && gameMap[ogrePosition[0]][ogrePosition[1] + 1] != 'I'){
					gameMap[ogrePosition[0]][ogrePosition[1]] = ' ';
					ogrePosition[1]++;
					if (ogrePosition[0] == leverPosition[0] && ogrePosition[1] == leverPosition[1] && !keyStolen){
						gameMap[ogrePosition[0]][ogrePosition[1]] = '$';
					}else{
						gameMap[ogrePosition[0]][ogrePosition[1]] = 'O';
						if (!keyStolen){
							gameMap[leverPosition[0]][leverPosition[1]] = 'k';							
						}
					}
					moveClub();
					break;
				}
			}
		}
		return;
	}
	
	private static void moveClub() {
		Random rnd = new Random();
		
		for (;;){
			int genMove = rnd.nextInt(4);
			
			if (genMove == 0){
				if (gameMap[ogrePosition[0] - 1][ogrePosition[1]] != 'X' && gameMap[ogrePosition[0] - 1][ogrePosition[1]] != 'I'){
					gameMap[clubPosition[0]][clubPosition[1]] = ' ';
					clubPosition[0] = ogrePosition[0]-1;
					clubPosition[1] = ogrePosition[1];
					
					if (clubPosition[0] == leverPosition[0] && clubPosition[1] == leverPosition[1] && !keyStolen){
						gameMap[clubPosition[0]][clubPosition[1]] = '$';
					}else{
						gameMap[clubPosition[0]][clubPosition[1]] = '*';
						if (!keyStolen){
							gameMap[leverPosition[0]][leverPosition[1]] = 'k';							
						}
					}
					break;
				}
			}
			else if (genMove == 1){
				if (gameMap[ogrePosition[0]][ogrePosition[1] - 1] != 'X' && gameMap[ogrePosition[0]][ogrePosition[1] - 1] != 'I'){
					gameMap[clubPosition[0]][clubPosition[1]] = ' ';
					clubPosition[1] = ogrePosition[1] - 1;
					clubPosition[0] = ogrePosition[0];
					if (clubPosition[0] == leverPosition[0] && clubPosition[1] == leverPosition[1] && !keyStolen){
						gameMap[clubPosition[0]][clubPosition[1]] = '$';
					}else{
						gameMap[clubPosition[0]][clubPosition[1]] = '*';
						if (!keyStolen){
							gameMap[leverPosition[0]][leverPosition[1]] = 'k';							
						}
					}
					break;
				}
			}
			else if (genMove == 2){
				if (gameMap[ogrePosition[0] + 1][ogrePosition[1]] != 'X' && gameMap[ogrePosition[0] + 1][ogrePosition[1]] != 'I'){
					gameMap[clubPosition[0]][clubPosition[1]] = ' ';
					clubPosition[0] = ogrePosition[0]+1;
					clubPosition[1] = ogrePosition[1];
					if (clubPosition[0] == leverPosition[0] && clubPosition[1] == leverPosition[1] && !keyStolen){
						gameMap[clubPosition[0]][clubPosition[1]] = '$';
					}else{
						gameMap[clubPosition[0]][clubPosition[1]] = '*';
						if (!keyStolen){
							gameMap[leverPosition[0]][leverPosition[1]] = 'k';							
						}
					}
					break;
				}
			}
			else if (genMove == 3){
				if (gameMap[ogrePosition[0]][ogrePosition[1] + 1] != 'X' && gameMap[ogrePosition[0]][ogrePosition[1] + 1] != 'I'){
					gameMap[clubPosition[0]][clubPosition[1]] = ' ';
					clubPosition[1] = ogrePosition[1] + 1;
					clubPosition[0] = ogrePosition[0];
					if (clubPosition[0] == leverPosition[0] && clubPosition[1] == leverPosition[1] && !keyStolen){
						gameMap[clubPosition[0]][clubPosition[1]] = '$';
					}else{
						gameMap[clubPosition[0]][clubPosition[1]] = '*';
						if (!keyStolen){
							gameMap[leverPosition[0]][leverPosition[1]] = 'k';							
						}
					}
					break;
				}
			}
		}
		return;
	}

	public static void main(String[] args) throws InterruptedException {

		loadMap1(); //Loads the game's first map.
		loadPositions(); //Stores every game entity's initial coordinates.
		drawMap(); //Initial map display.
		
		Scanner s = new Scanner(System.in);
		
		//First level input loop.
	/*	for (;;){
			String move = s.nextLine();
			
			if (moveHero(move)){
				System.out.println("Level complete!\n");
				Thread.sleep(1500);
				break;
			}
			
			if (heroSpotted()){
				System.out.println("You got caught, doofus!\n");
				return;
			}
		}
		*/
		//Keep's Crazy Ogre input loop.
		loadMap2();
		loadPositions();
		drawMap();
		
		
		for (;;){
			String move = s.nextLine();
			
			if (moveHero(move)){
				System.out.println("Game complete!\n");
				break;
			}
			if (heroSpotted()){
				System.out.println("You were slain.\n");
				return;
			}			
		}
		
	}
}
