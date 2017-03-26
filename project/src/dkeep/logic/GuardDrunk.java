package dkeep.logic;
import java.io.Serializable;



import java.util.Random;

/**
 * Class that saves all the information regarding the drunken guard
 * 
 * @author José Borges and Miguel Mano Fernandes
 * @version 1.0
 */

public class GuardDrunk extends Guard implements Serializable{
	
	/**
	 * Creates a drunk guard in a specific position
	 * 
	 * @param x
	 * 		x position
	 * @param y
	 * 		y position
	 */
	
	public GuardDrunk(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'G';
		this.patrolling = true;
		this.sleeping = false;
		this.inversePath = false;
	}
	
	/**
	 * Checks the movement of the guard and dictates its next position
	 */
	
	public void checkMovement() {
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
	}
	
	/**
	 * Moves the guard
	 */
	
	public void moveGuard(){
		if (!this.patrolling){
			return;
		}
		
		if (this.sleeping)
			if (!wakeUp())
				return;
		
		checkMovement();
		if (!sleeping)
			if (!changeDirection()){
				if (!this.inversePath && !this.sleeping)
					movementIterator++;			
				else if (this.inversePath && !this.sleeping)
					movementIterator--;
			}
		
		if (movementIterator == patrolRoute.length && !this.inversePath && !this.sleeping)
			movementIterator = 0;	
		else if (movementIterator == -1 && this.inversePath && !this.sleeping)
			movementIterator = (patrolRoute.length - 1);
		
		fallAsleep();
	}
	
	/**
	 * Gives the guard a 33% chance to fall asleep
	 * 
	 * @return
	 * 		True or false depending whether the guard fell asleep or not
	 */
	public boolean fallAsleep(){
		Random rnd = new Random();
		int chance = rnd.nextInt(3);
		
		if (chance == 0){ //33% chance to fall asleep.
			this.sleeping = true;
			this.symbol = 'g';
			return true;
		}
		return false;
	}
	
	/**
	 * Gives the guard a 75% chance to wake up
	 * 
	 * @return
	 * 		True or false depending whether the guard woke up or not
	 */
	
	public boolean wakeUp(){
		Random rnd = new Random();
		int chance = rnd.nextInt(4);
		
		if (chance != 0){ //75% chance to wake up.
			this.sleeping = false;
			this.symbol = 'G';
			return true;
		}
		return false;
	}
	
	/**
	 * Gives the guard a 50% chance to change direction
	 * 
	 * @return
	 * 		True or false depending whether the guard changed direction or not
	 */
	
	public boolean changeDirection(){
		Random rnd = new Random();
		int chance = rnd.nextInt(2);
		
		if (chance == 0 && this.inversePath){  //50% chance to change direction.
			this.inversePath = false;
			return true;
		}
		else if (chance == 0 && !this.inversePath){  //50% chance to change direction.
			this.inversePath = true;
			return true;
		}
		return false;
	}

}
