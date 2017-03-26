package dkeep.logic;

import java.io.Serializable;

/**
 * Class that contains all information regarding the Rookie Guard
 * 
 * @author José Borges and Miguel Mano Fernandes
 *
 */

public class GuardRookie extends Guard implements Serializable {
	/**
	 * Constructor that creates a guard in a specific position
	 * 
	 * @param x
	 * 		x coordinate
	 * @param y
	 * 		y coordinate
	 */
	
	public GuardRookie(int x, int y){
		this.x = x;
		this.y = y;
		this.patrolling = true;
		this.symbol = 'G';
	}
	
	/**
	 * Moves the guard
	 */
	public void moveGuard(){
		if (!this.patrolling){
			return;
		}
		if (patrolRoute[movementIterator] == 'W'){
			this.x--;
		}
		else if (patrolRoute[movementIterator] == 'A'){
			this.y--;
		}
		else if (patrolRoute[movementIterator] == 'S'){
			this.x++;
		}
		else if (patrolRoute[movementIterator] == 'D'){
			this.y++;
		}
		movementIterator++;
		
		if (movementIterator == patrolRoute.length){
			movementIterator = 0;				
		}
	}
}
