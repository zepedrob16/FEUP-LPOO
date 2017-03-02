package dkeep.logic;
import java.util.Random;

public class GuardDrunk extends Guard {
	
	private boolean sleeping, inversePath;
	
	public GuardDrunk(int x, int y){
		this.x = x;
		this.y = y;
		this.symbol = 'G';
		this.sleeping = false;
		this.inversePath = false;
	}
	public void moveGuard(){
		if (this.sleeping){
			if (!wakeUp()){
				return;
			}
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
		
		if (!sleeping){
			if (!changeDirection()){
				if (!this.inversePath && !this.sleeping){
					movementIterator++;			
				}
				else if (this.inversePath && !this.sleeping){
					movementIterator--;
				}
			}
		}
		if (movementIterator == patrolRoute.length && !this.inversePath && !this.sleeping){
			movementIterator = 0;	
		}
		else if (movementIterator == -1 && this.inversePath && !this.sleeping){
			movementIterator = (patrolRoute.length - 1);
		}
		fallAsleep();
	}
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
