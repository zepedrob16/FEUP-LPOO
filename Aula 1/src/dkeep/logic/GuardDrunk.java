package dkeep.logic;
import java.util.Random;

public class GuardDrunk extends Guard {
	
	private boolean sleeping, inversePath;
	
	public GuardDrunk(int x, int y){
		this.x = x;
		this.y = y;
		this.sleeping = false;
		this.inversePath = false;
	}
	public void moveGuard(){
		
		if (sleeping){
			wakeUp();
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
		
		fallAsleep();
	}
	public boolean fallAsleep(){
		Random rnd = new Random();
		int chance = rnd.nextInt(4);
		
		if (chance == 0){ //25% chance to fall asleep.
			this.sleeping = true;
			this.symbol = 'g';
			return true;
		}
		return false;
	}
	public boolean wakeUp(){
		Random rnd = new Random();
		int chance = rnd.nextInt(4);
		
		if (chance != 0){ //75% chance to wake up.
			this.sleeping = false;
			this.symbol = 'G';
			changeDirection();
			return true;
		}
		return false;
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
