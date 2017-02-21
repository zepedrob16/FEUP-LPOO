package dkeep.logic;

public class Hero {
	private int x, y;
	private char symbol;

	public Hero(int x, int y){
		this.x = x;
		this.y = y;
		symbol = 'H';
	}

	public void moveHero(char move){
		if (move == 'w'){
			moveTo(x-1,y);
		}
		else if (move == 'a'){
			moveTo(x,y-1);
		}
		else if (move == 's'){
			moveTo(x+1,y);
		}
		else if (move == 'd'){
			moveTo(x,y+1);
		}
		return;
	}


	//Get methods.
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public char getSymbol(){
		return symbol;
	}
}