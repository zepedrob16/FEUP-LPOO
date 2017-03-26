package dkeep.logic;

import java.io.Serializable;

/**
 * Class that contains all the guard information
 * 
 * @author zé borges
 *
 */

public class Guard implements Serializable{
	protected int x, y, movementIterator;
	protected char symbol, patrolRoute[] = {'A','S','S','S','S','A','A','A','A','A','A','S','D','D','D','D','D','D','D','W','W','W','W','W'};
	protected boolean patrolling;
	protected boolean sleeping;
	protected boolean inversePath;
	
	/**
	 * Empty constructor
	 */
	
	public Guard(){}
	
	/**
	 * Guard constructor with a specific position
	 * 
	 * @param x
	 * 		x coordinate
	 * @param y
	 * 		y coordinate
	 */
	
	public Guard(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.symbol = 'G';
		this.patrolling = true;
		this.sleeping = false;
		this.inversePath = false;
		this.movementIterator = 0;
	}
	
	/**
	 * Function to test the guards. It enables if they are patrolling or not
	 * 
	 * @param enable
	 * 		True or false
	 */
	
	public void testMode(boolean enable){
		this.patrolling = (enable) ? false : true;
	}
	
	/**
	 * Function used to move the guards. Each personality has a different way of moving
	 */
	
	public void moveGuard(){};
	
	
	/* Get & Set Methods */
	
	/**
	 * Returns x position of the guard
	 * 
	 * @return
	 */
	
	public int getX(){
		return x;
	}
	
	/**
	 * Returns y position of the guard 
	 * @return
	 */
	
	public int getY(){
		return y;
	}
	
	/**
	 * Returns the guard symbol 
	 * @return
	 */
	
	public char getSymbol(){
		return symbol;
	}
	
	/**
	 * Sets the x position of the guard
	 * 
	 * @param x
	 */
	
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Sets the y position of the guard
	 * 
	 * @param y
	 */
	
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * Sets the symbol of the guard
	 * 
	 * @param symbol
	 */
	
	public void setSymbol(char symbol){
		this.symbol = symbol;
	}
	
	/**
	 * Returns a boolean that says if the guard is sleeping or not
	 * 
	 * @return
	 */
	
	public boolean getSleeping() {
		return this.sleeping;
	}
	
	/**
	 * Returns a boolean saying if the guard is taking the path backwards
	 * @return
	 */
	
	public boolean getInversePath(){
		return this.inversePath;
	}
	
}
