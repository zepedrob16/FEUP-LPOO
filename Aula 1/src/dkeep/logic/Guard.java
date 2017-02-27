package dkeep.logic;

public class Guard {
	private int x, y;
	char personality;
	private char symbol;
	
	public Guard(int x, int y, char personality){
		this.x = x;
		this.y = y;
		this.personality = personality;
		this.symbol = 'G';
	}
	
	public void moveGuard(GameMap map){
		if (this.personality == 'R'){  //Guard has Rookie personality.

		}
		else if (this.personality == 'D'){  //Guard has Drunken personality.
			
		}
		else if (this.personality == 'S'){  //Guard has Suspicious personality.
			
		}
	}
	
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public char getSymbol(){
		return symbol;
	}
	public char getPersonality(){
		return personality;
	}
}
