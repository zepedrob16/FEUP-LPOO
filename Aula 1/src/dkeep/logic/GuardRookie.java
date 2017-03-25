package dkeep.logic;

import java.io.Serializable;

public class GuardRookie extends Guard implements Serializable {
	
	public GuardRookie(int x, int y){
		this.x = x;
		this.y = y;
		this.patrolling = true;
		this.symbol = 'G';
	}
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
