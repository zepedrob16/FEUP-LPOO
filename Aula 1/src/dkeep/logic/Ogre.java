package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

public class Ogre implements Serializable{
	private int x, y, clubX, clubY, stunCounter = 0;
	private char symbol, clubSymbol;
	private boolean stunned;
	private boolean testMode;
	private boolean validMove;
	
	public Ogre(int x, int y){
		this.x = x;
		this.y = y;
		this.testMode = false;
		this.symbol = 'O';
	}
	
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
	
	public void stun() {
		stunned = true;
		symbol = '8';
	}
	
	public void testMode(boolean enable){
		testMode = (enable) ? true : false;
	}
	
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
	
	public void moveClubU() {
		this.clubX = this.x - 1;
		this.clubY = this.y;
		this.validMove = true;
	}
	
	public void moveClubL() {
		this.clubX = this.x;
		this.clubY = this.y - 1;
		this.validMove = true;
	}
	
	public void moveClubD() {
		this.clubX = this.x + 1;
		this.clubY = this.y;
		this.validMove = true;
	}
	
	public void moveClubR(){
		this.clubX = this.x;
		this.clubY = this.y + 1;
		this.validMove = true;
	}
	
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
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getClubX(){
		return clubX;
	}
	public int getClubY(){
		return clubY;
	}
	public char getSymbol(){
		return symbol;
	}
	public char getClubSymbol(){
		return clubSymbol;
	}
	public boolean getStunned(){
		return stunned;
	}
	public boolean getTestMode(){
		return testMode;
	}
}
