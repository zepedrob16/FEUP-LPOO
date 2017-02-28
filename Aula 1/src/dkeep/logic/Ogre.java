package dkeep.logic;

public class Ogre {
	private int x, y;
	private char symbol;
	
	public Ogre(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'O';
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public char getSymbol(){
		return symbol;
	}
}
