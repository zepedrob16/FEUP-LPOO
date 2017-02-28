package dkeep.logic;


public class Guard {
	private int x, y;
	char personality;
	private char symbol;
	static int movementIterator = 0;

	public Guard(int x, int y, char personality){
		this.x = x;
		this.y = y;
		this.personality = personality;
		this.symbol = 'G';
	}
	
	public void moveGuard(GameMap map){
		if (this.personality == 'R'){  //Guard has Rookie personality.
			char guardMovement[] = {'A','S','S','S','S','A','A','A','A','A','A','S','D','D','D','D','D','D','D','W','W','W','W','W'};
			if (guardMovement[movementIterator] == 'W'){
				this.x--;
			}
			else if (guardMovement[movementIterator] == 'A'){
				this.y--;
			}
			else if (guardMovement[movementIterator] == 'S'){
				this.x++;
			}
			else if (guardMovement[movementIterator] == 'D'){
				this.y++;
			}
			movementIterator++;
			
			if (movementIterator == guardMovement.length)
				movementIterator = 0;
		
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
