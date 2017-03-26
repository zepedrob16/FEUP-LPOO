package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * Class that contains all the information about the suspicious guard
 * 
 * @author José Borges and Miguel Mano Fernandes
 * @version 1.0
 */
public class GuardSuspicious extends Guard implements Serializable{
	
	/**
	 * Constructor that creates a guard in a specific location
	 * 
	 * @param x
	 * 		x coordinate
	 * @param y
	 * 		y coordinate
	 */
	
	public GuardSuspicious(int x, int y){
		this.x = x;
		this.y = y;
		this.patrolling = true;
		this.symbol = 'G';
		this.inversePath = false;
	}
	
	/**
	 * Moves the guard
	 */
	
	public void moveGuard(){
		if (!this.patrolling){
			return;
		}
		if (patrolRoute[movementIterator] == 'W'){
			x = (!inversePath) ? x-1 : x+1;
		}
		else if (patrolRoute[movementIterator] == 'A'){
			y = (!inversePath) ? y-1 : y+1;
		}
		else if (patrolRoute[movementIterator] == 'S'){
			x = (!inversePath) ? x+1 : x-1;
		}
		else if (patrolRoute[movementIterator] == 'D'){
			y = (!inversePath) ? y+1 : y-1;
		}
		System.out.println(patrolRoute[movementIterator]);
		
		if (!changeDirection()){
			movementIterator = (!inversePath) ? movementIterator+1 : movementIterator-1;
		}
		if (movementIterator == patrolRoute.length && !this.inversePath){
			movementIterator = 0;	
		}
		else if (movementIterator == -1 && this.inversePath){
			movementIterator = (patrolRoute.length - 1);
		}
	}
	
	/**
	 * Gives the guard a 50% chance to change direction
	 * 
	 * @return
	 * 		True or false depending whether the guard changed direction or not
	 */
	
	public boolean changeDirection(){
		Random rnd = new Random();
		
		if (rnd.nextInt(2) == 0){
			inversePath = (inversePath) ? false : true;
			return true;
		}
		return false;
	}
	
}
