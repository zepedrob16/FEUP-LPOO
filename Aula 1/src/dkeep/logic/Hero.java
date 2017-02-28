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
	
	public int moveHero(GameMap map, String move){
		if (move.equals("w")) {
			if (map.moveTo(x-1, y) == 0 || map.moveTo(x-1, y) == 1){ //Valid moves, hero position updated.
				this.x--;
				return map.moveTo(x, y);
			}
			return map.moveTo(x-1, y);	//In case of invalid movement, hero stays put.
		}
		else if (move.equals("a")){
			if (map.moveTo(x, y-1) == 0 || map.moveTo(x, y-1) == 1){
				this.y--;
				return map.moveTo(x, y);
			}
			return map.moveTo(x, y-1);
		}
		else if (move.equals("s")){
			if (map.moveTo(x+1, y) == 0 || map.moveTo(x+1, y) == 1){
				this.x++;
				return map.moveTo(x, y);
			}
			return map.moveTo(x+1, y);
		}
		else if (move.equals("d")){
			if (map.moveTo(x, y+1) == 0 || map.moveTo(x, y+1) == 1){
				this.y++;
				return map.moveTo(x, y);
			}
			return map.moveTo(x,y+1);
		}
		return -1;	//In case of invalid input, it's discarded.
	}
	
	public boolean heroSpotted(Guard guard){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				if ((x == guard.getX() + 1 && y == guard.getY()) || (x == guard.getX() - 1 && y == guard.getY()) || (y == guard.getY() + 1 && x == guard.getX()) || (y == guard.getY() - 1 && x == guard.getX())){
					return true;
				}
			}
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