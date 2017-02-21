package dkeep.logic;

public class GameState {
	private GameMap map;
	
	public GameState() {
	}
	public GameState(GameMap map) {
		super();
		this.map = map;
	}

	public void setGameMap(GameMap map){
		this.map = map;
	}
}
