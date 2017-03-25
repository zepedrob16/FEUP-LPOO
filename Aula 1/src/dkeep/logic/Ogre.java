package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * Class that contains all information regarding the ogre class
 * 
 * @author José Borges and Miguel Mano Fernandes
 * @version 1.0
 */
public class Ogre implements Serializable{
	private int x, y, clubX, clubY, stunCounter = 0;
	private char symbol, clubSymbol;
	private boolean stunned;
	private boolean testMode;
	private boolean validMove;
	
	/**
	 * Creates an ogre in a specific location
	 * 
	 * @param x
	 * 		x coordinate
	 * @param y
	 * 		y coordinate
	 */
	
	public Ogre(int x, int y){
		this.x = x;
		this.y = y;
		this.testMode = false;
		this.symbol = 'O';
		this.clubX = -1;
		this.clubY = -1;
	}
	
	/**
	 * Checks whether the hero is adjacent to the ogres club or not
	 * 
	 * @param hero
	 * 		Current hero
	 * @return
	 * 		True or false depending on whether the hero is adjacent or not
	 */
	
	public boolean heroAdjacent(Hero hero){
		if (hero.getX() == this.x && hero.getY() == this.y){	//Caso estejam na mesma célula.
			return true;
		}
		else if(hero.getX() == this.clubX && hero.getY() == this.clubY)
			return true;
		else if ((hero.getX() == this.clubX - 1 && hero.getY() == this.clubY) || (hero.getX() == this.clubX + 1 && hero.getY() == this.clubY) || (hero.getY() == this.clubY - 1 && hero.getX() == this.clubX) || (hero.getY() == this.clubY + 1 && hero.getX() == this.clubX)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Stuns the ogre
	 */
	
	public void stun() {
		stunned = true;
		symbol = '8';
	}
	
	/**
	 * Function to test the ogre
	 * @param enable
	 * 		Enables or disables test mode
	 */
	
	public void testMode(boolean enable){
		testMode = (enable) ? true : false;
	}
	
	/**
	 * Moves the ogre
	 * 
	 * @param gameState
	 * 		Current gamestate
	 */
	public void move(GameState gameState){

		if (this.testMode){
			return;
		}
		Random rnd = new Random();

		while(true){
			int genMove = rnd.nextInt(4);
			
			if (stunned && stunCounter < 2){
				stunCounter++;
				moveClub(gameState);
				break;
			}
			if (stunCounter == 2) {
				stunned = false;
				stunCounter = 0;
			}

			if (checkValidMove(genMove, gameState.getGameMap().getMap())){
				if (x == gameState.keyX && y == gameState.keyY){
					this.symbol = '$';
				}
				else if (this.symbol != 'O'){
					this.symbol = 'O';
				}
				moveClub(gameState);
				break;
			}
		}
		return;
	}
	
	/**
	 * Checks to see if the position the ogre intends to move to is valid or not
	 * 
	 * @param genMove
	 * 		Random number that determines the ogres movement
	 * @param map
	 * 		Current map
	 * @return
	 * 		True or false depending on whether the move is valid or not
	 */
	
	public boolean checkValidMove(int genMove, char[][] map){
		
		if (genMove == 0 && map[x-1][y] != 'X' && map[x-1][y] != 'I' && map[x-1][y] != 'S'){
			x--;
			return true;
		}
		else if (genMove == 1 && map[x][y-1] != 'X' && map[x][y-1] != 'I' && map[x][y-1] != 'S'){
			y--;
			return true;
		}
		else if (genMove == 2 && map[x+1][y] != 'X' && map[x+1][y] != 'I' && map[x+1][y] != 'S'){
			x++;
			return true;
		}
		else if (genMove == 3 && map[x][y+1] != 'X' && map[x][y+1] != 'I' && map[x][y+1] != 'S'){
			y++;
			return true;
		}
		return false;
	}
	
	/**
	 * Moves the club north of the ogre
	 */
	
	public void moveClubU() {
		this.clubX = this.x - 1;
		this.clubY = this.y;
		this.validMove = true;
	}
	
	/**
	 * Moves the club west of the ogre
	 */
	
	public void moveClubL() {
		this.clubX = this.x;
		this.clubY = this.y - 1;
		this.validMove = true;
	}
	
	/**
	 * Moves the club south of the ogre
	 */
	
	public void moveClubD() {
		this.clubX = this.x + 1;
		this.clubY = this.y;
		this.validMove = true;
	}
	
	/**
	 * Moves the club east of the ogre
	 */
	
	public void moveClubR(){
		this.clubX = this.x;
		this.clubY = this.y + 1;
		this.validMove = true;
	}
	
	/**
	 * Checks the current ogre symbol
	 * 
	 * @param gameState
	 * 		Current gamestate
	 * @return
	 * 		True or false depending on whether it was a valid move or not
	 */
	
	public boolean checkSymbol(GameState gameState) {
		if (validMove){
			if (this.clubX == gameState.keyX && this.clubY == gameState.keyY){
				this.clubSymbol = '$';
			}
			else if (this.clubSymbol != '*'){
				this.clubSymbol = '*';
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Moves the ogres club
	 * 
	 * @param gameState
	 * 		Current gamestate
	 */
	
	public void moveClub(GameState gameState){
		char[][] gameMap = gameState.getGameMap().getMap();
		Random rnd = new Random();
		while(true){
			int genMove = rnd.nextInt(4);
			this.validMove = false;
			if (genMove == 0 && gameMap[x-1][y] != 'X' && gameMap[x-1][y] != 'I' && gameMap[x-1][y] != 'S')
				moveClubU();
			else if (genMove == 1 && gameMap[x][y-1] != 'X' && gameMap[x][y-1] != 'I' && gameMap[x][y-1] != 'S')
				moveClubL();
			else if (genMove == 2 && gameMap[x+1][y] != 'X' && gameMap[x+1][y] != 'I' && gameMap[x+1][y] != 'S')
				moveClubD();
			else if (genMove == 3 && gameMap[x][y+1] != 'X' && gameMap[x][y+1] != 'I' && gameMap[x][y+1] != 'S')
				moveClubR();
			if (checkSymbol(gameState))
				break;
		}
		return;
	}
	
	/**
	 * Returns the ogres x position
	 * @return
	 * 		x coordinate
	 */
	
	public int getX(){
		return x;
	}
	
	/**
	 * Returns the ogres y position
	 * @return
	 * 		y coordinate
	 */
	
	public int getY(){
		return y;
	}
	
	/**
	 * Returns the ogre's club x position
	 * 
	 * @return
	 * 		x coordinate
	 */
	
	public int getClubX(){
		return clubX;
	}
	
	/**
	 * Returns the ogre's club y position
	 * @return
	 * 		y coordinate
	 */
	
	public int getClubY(){
		return clubY;
	}
	
	/**
	 * Returns the ogres symbol
	 * @return
	 * 		Symbol
	 */
	
	public char getSymbol(){
		return symbol;
	}
	
	/**
	 * Returns the ogre's club symbol
	 * @return
	 * 		Club symbol
	 */
	
	public char getClubSymbol(){
		return clubSymbol;
	}
	
	/**
	 * Returns a boolean depending on whether the ogre is stunned or not
	 * @return
	 * 		True or false
	 */
	
	public boolean getStunned(){
		return stunned;
	}
	
	/**
	 * Returns a boolean telling if the ogres are in test mode or not
	 * 
	 * @return
	 * 		True or false
	 */
	public boolean getTestMode(){
		return testMode;
	}
}
