package dkeep.logic;

public class Guard {
	protected int x, y, movementIterator;
	protected char symbol;
	protected char patrolRoute[] = {'A','S','S','S','S','A','A','A','A','A','A','S','D','D','D','D','D','D','D','W','W','W','W','W'};
	
	public Guard(){}
	
	public Guard(int x, int y){
		this.x = x;
		this.y = y;
		this.movementIterator = 0;
		this.symbol = 'G';
	}
	
	public void moveGuard(){};
	
	//Get and set methods.
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public char getSymbol(){
		return symbol;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setSymbol(char symbol){
		this.symbol = symbol;
	}
}
