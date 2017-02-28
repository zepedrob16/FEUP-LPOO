package dkeep.logic;

import java.util.Random;

public class GuardSuspicious extends Guard {

	private boolean inversePath;
	
	public GuardSuspicious(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void moveGuard(){
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
		
		if (!this.inversePath){
			movementIterator++;			
		}else{
			movementIterator--;
		}
		
		if (movementIterator == patrolRoute.length && !this.inversePath){
			movementIterator = 0;	
		}
		else if (movementIterator == -1 && this.inversePath){
			movementIterator = (patrolRoute.length - 1);
		}
		changeDirection();
	}
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
