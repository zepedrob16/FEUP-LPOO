package dkeep.logic;

import java.util.Random;

public class Ogre {
	private int x, y, clubX, clubY;
	private char symbol, clubSymbol;
	
	public Ogre(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'O';
	}
	public boolean heroAdjacent(Hero hero){
		if (hero.getX() == this.x && hero.getY() == this.y){	//Caso estejam na mesma célula.
			return true;
		}
		else if ((hero.getX() == this.x - 1 && hero.getY() == this.y) || (hero.getX() == this.x + 1 && hero.getY() == this.y) || (hero.getY() == this.y - 1 && hero.getX() == this.x) || (hero.getY() == this.y + 1 && hero.getX() == this.x)){
			return true;
		}
		else if ((hero.getX() == this.clubX - 1 && hero.getY() == this.clubY) || (hero.getX() == this.clubX + 1 && hero.getY() == this.clubY) || (hero.getY() == this.clubY - 1 && hero.getX() == this.clubX) || (hero.getY() == this.clubY + 1 && hero.getX() == this.clubX)){
			return true;
		}
		else{
			return false;
		}
	}
	public void move(GameState gameState){
		char[][] gameMap = gameState.getGameMap().getMap();
		Random rnd = new Random();

		while(true){
			int genMove = rnd.nextInt(4);
			boolean validMove = false;
			
			if (genMove == 0 && gameMap[x-1][y] != 'X' && gameMap[x-1][y] != 'I'){
				x--;
				validMove = true;
			}
			else if (genMove == 1 && gameMap[x][y-1] != 'X' && gameMap[x][y-1] != 'I'){
				y--;
				validMove = true;
			}
			else if (genMove == 2 && gameMap[x+1][y] != 'X' && gameMap[x+1][y] != 'I'){
				x++;
				validMove = true;
			}
			else if (genMove == 3 && gameMap[x][y+1] != 'X' && gameMap[x][y+1] != 'I'){
				y++;
				validMove = true;
			}
			
			if (validMove){
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
	public void moveClub(GameState gameState){
		char[][] gameMap = gameState.getGameMap().getMap();
		Random rnd = new Random();

		while(true){
			int genMove = rnd.nextInt(4);
			boolean validMove = false;
			
			if (genMove == 0 && gameMap[x-1][y] != 'X' && gameMap[x-1][y] != 'I'){
				this.clubX = this.x - 1;
				this.clubY = this.y;
				validMove = true;
			}
			else if (genMove == 1 && gameMap[x][y-1] != 'X' && gameMap[x][y-1] != 'I'){
				this.clubX = this.x;
				this.clubY = this.y - 1;
				validMove = true;
			}
			else if (genMove == 2 && gameMap[x+1][y] != 'X' && gameMap[x+1][y] != 'I'){
				this.clubX = this.x + 1;
				this.clubY = this.y;
				validMove = true;
			}
			else if (genMove == 3 && gameMap[x][y+1] != 'X' && gameMap[x][y+1] != 'I'){
				this.clubX = this.x;
				this.clubY = this.y + 1;
				validMove = true;
			}
			
			if (validMove){
				if (this.clubX == gameState.keyX && this.clubY == gameState.keyY){
					this.clubSymbol = '$';
				}
				else if (this.clubSymbol != '*'){
					this.clubSymbol = '*';
				}
				break;
			}
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
}
