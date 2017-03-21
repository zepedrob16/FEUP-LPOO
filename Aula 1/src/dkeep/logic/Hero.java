package dkeep.logic;

import java.util.Random;

public class Hero {
	private int x, y;
	private char symbol, clubSymbol;
	
	public Hero(){}
	
	public Hero(int x, int y, String name){
		this.x = x;
		this.y = y;
		
		if (name == "DUNGEON"){
			this.symbol = 'H';			
		}
		else if (name == "CRAZY OGRE"){
			this.symbol = 'A';
		}
	}
	
	public int moveHero(GameMap map, String move){
		if (move.equals("w")) {
			if (map.moveTo(x-1, y) == -1 && symbol == 'K') {
				map.openDoors();
				return 0;
			}
			
			if (map.moveTo(x-1, y) == 0 || map.moveTo(x-1, y) == 1 || map.moveTo(x-1, y) == 2){ //Valid moves, hero position updated.
				this.x--;
				return map.moveTo(x, y);
			}
			return map.moveTo(x-1, y);	//In case of invalid movement, hero stays put.
		}
		else if (move.equals("a")){
			if (map.moveTo(x, y-1) == -1 && symbol == 'K') {
				map.openDoors();
				return 0;
			}
		
			if (map.moveTo(x, y-1) == 0 || map.moveTo(x, y-1) == 1 || map.moveTo(x, y-1) == 2){
				this.y--;
				return map.moveTo(x, y);
			}
			return map.moveTo(x, y-1);
		}
		else if (move.equals("s")){
			if (map.moveTo(x+1, y) == -1 && symbol == 'K') {
				map.openDoors();
				return 0;
			}
			
			if (map.moveTo(x+1, y) == 0 || map.moveTo(x+1, y) == 1 || map.moveTo(x+1, y) == 2){
				this.x++;
				return map.moveTo(x, y);
			}
			return map.moveTo(x+1, y);
		}
		else if (move.equals("d")){
			if (map.moveTo(x, y+1) == -1 && symbol == 'K') {
				map.openDoors();
				return 0;
			}
			
			if (map.moveTo(x, y+1) == 0 || map.moveTo(x, y+1) == 1 || map.moveTo(x, y+1) == 2){
				this.y++;
				return map.moveTo(x, y);
			}
			return map.moveTo(x,y+1);
		}
		return -1;	//In case of invalid input, it's discarded.
	}
	
	public boolean heroSpotted(Guard guard){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if (((x == guard.getX() + 1 && y == guard.getY()) || (x == guard.getX() - 1 && y == guard.getY()) || (y == guard.getY() + 1 && x == guard.getX()) || (y == guard.getY() - 1 && x == guard.getX())) && guard.getSymbol() == 'G'){
					return true;
				}
			}
		}
		return false;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public boolean stunOgre(Ogre ogre) {
		if ((x == ogre.getX() - 1 && y == ogre.getY()) || (x == ogre.getX() + 1 && y == ogre.getY()) || (x == ogre.getX() && y == ogre.getY() - 1) || (x == ogre.getX() && y == ogre.getY() + 1)) {
			ogre.stun();
		}
		
		return false;
	}

	//Get methods.
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public char getSymbol(){
		return symbol;
	}
	public char getClubSymbol(){
		return clubSymbol;
	}
	
}