package dkeep.logic;

public class GuardRookie extends Guard {
	
	public GuardRookie(int x, int y){
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
		movementIterator++;
		
		if (movementIterator == patrolRoute.length){
			movementIterator = 0;				
		}
	}
}
