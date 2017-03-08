package dkeep.logic;

import java.util.Random;

public class GuardSuspicious extends Guard {

	private boolean inversePath;
	
	public GuardSuspicious(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'G';
	}
	public void moveGuard(){
		if (!this.patrolling){
			return;
		}
		if (patrolRoute[movementIterator] == 'W'){
			if (!this.inversePath){
				this.x--;				
			}else{
				this.x++;
			}
		}
		else if (patrolRoute[movementIterator] == 'A'){
			if (!this.inversePath){
				this.y--;				
			}else{
				this.y++;
			}
		}
		else if (patrolRoute[movementIterator] == 'S'){
			if (!this.inversePath){
				this.x++;				
			}else{
				this.x--;
			}
		}
		else if (patrolRoute[movementIterator] == 'D'){
			if (!this.inversePath){
				this.y++;				
			}else{
				this.y--;
			}
		}
		System.out.println(patrolRoute[movementIterator]);
		
			if (!changeDirection()){
				if (!this.inversePath){
					movementIterator++;			
				}
				else if (this.inversePath){
					movementIterator--;
				}
			}
		if (movementIterator == patrolRoute.length && !this.inversePath){
			movementIterator = 0;	
		}
		else if (movementIterator == -1 && this.inversePath){
			movementIterator = (patrolRoute.length - 1);
		}
		//changeDirection();
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
