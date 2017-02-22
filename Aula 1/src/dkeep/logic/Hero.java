package dkeep.logic;

public class Hero {
	private int x;
	private int y;
	private char symbol;

	public Hero(int x, int y){
		this.x = x;
		this.y = y;
		symbol = 'H';
	}

	public boolean moveHero(DungeonMap map, String move){
		if (move.equals("w")) {
			map.moveTo(x-1,y);
		}
		else if (move.equals("a")){
			map.moveTo(x,y-1);
		}
		else if (move.equals("s")){
			map.moveTo(x+1,y);
		}
		else if (move.equals("d")){
			map.moveTo(x,y+1);
		}
		return false;
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