package dkeep.logic;

public class Guard {
	protected int x, y, movementIterator;
	protected char symbol, patrolRoute[] = {'A','S','S','S','S','A','A','A','A','A','A','S','D','D','D','D','D','D','D','W','W','W','W','W'};
	protected boolean patrolling;
	protected boolean sleeping;
	
	public Guard(){}
	
	public Guard(int x, int y){
		this.x = x;
		this.y = y;
		
		this.symbol = 'G';
		this.patrolling = true;
		this.sleeping = false;
		this.movementIterator = 0;
	}
	
	public void testMode(boolean enable){
		this.patrolling = (enable) ? false : true;
	}
	
	public void moveGuard(){};
	
	
	/* Get & Set Methods */
	
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
	public boolean getSleeping() {
		return this.sleeping;
	}
	
}
